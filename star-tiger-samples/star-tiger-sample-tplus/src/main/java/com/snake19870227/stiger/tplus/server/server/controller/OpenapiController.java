package com.snake19870227.stiger.tplus.server.server.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.tplus.ChanjetConstant;
import com.snake19870227.stiger.tplus.entity.po.SysCfg;
import com.snake19870227.stiger.tplus.entity.po.TpToken;
import com.snake19870227.stiger.tplus.openapi.ChanjetOpenApiService;
import com.snake19870227.stiger.tplus.openapi.entity.dto.AccessToken;
import com.snake19870227.stiger.tplus.server.server.Constant;
import com.snake19870227.stiger.tplus.service.ISysCfgService;
import com.snake19870227.stiger.tplus.service.ITpTokenService;
import com.snake19870227.stiger.tplus.util.EntityUtil;
import com.snake19870227.stiger.web.restful.RestResp;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2021/04/30
 */
@Controller
@RequestMapping(path = "/openapi")
public class OpenapiController {

    private static final Logger logger = LoggerFactory.getLogger(OpenapiController.class);

    @Value("${hpry.tplus.redirect-uri}")
    private String redirectUri;
    @Value("${hpry.tplus.org-id}")
    private String orgId;

    @Autowired
    private ChanjetOpenApiService openApiService;

    private final ISysCfgService sysCfgService;
    private final ITpTokenService tpTokenService;

    public OpenapiController(ISysCfgService sysCfgService, ITpTokenService tpTokenService) {
        this.sysCfgService = sysCfgService;
        this.tpTokenService = tpTokenService;
    }

    @GetMapping(path = "/oauth")
    public String oauth(@RequestParam(name = "code") String code,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model) {
        logger.info("收到授权code:{}", code);
        AccessToken accessToken = openApiService.getAccessToken(redirectUri, code);
        logger.info("获取用户[{}]token", accessToken.getUserId());
        TpToken token = EntityUtil.accessTokenToPo(accessToken);
        QueryWrapper<TpToken> wrapper = new QueryWrapper<>();
        wrapper.eq("USER_ID", accessToken.getUserId());
        TpToken tpToken = tpTokenService.getOne(wrapper);
        if (tpToken != null) {
            logger.info("查询到已有token[{}]", tpToken.getFlow());
            token.setFlow(tpToken.getFlow());
            token.setUserName(tpToken.getUserName());
        }
        tpTokenService.saveOrUpdate(token);
        HttpSession session = request.getSession();
        session.setAttribute(Constant.USER_ID_SESSION_KEY, token.getUserId());
        session.setAttribute(Constant.TPLUS_TOKEN_SESSION_KEY, token);
        ServletUtil.addCookie(response, Constant.USER_ID_COOKIE_KEY, token.getUserId(), 60 * 60 * 24 * 7);
        return "redirect:/authInfo";
    }

    @GetMapping(path = "/getPermanentAuthCode")
    @ResponseBody
    public RestResp<?> getPermanentAuthCode(@RequestParam(name = "forceUpdate", defaultValue = "0") Integer forceUpdate) {

        SysCfg permanentAuthCodeCfg = sysCfgService.getById(ChanjetConstant.CFG_KEY_PERMANENT_AUTH_CODE);

        if (permanentAuthCodeCfg != null && forceUpdate == 0) {
            return RestResp.failure("PERMANENT_AUTH_CODE已获取");
        }

        SysCfg appTicketCfg = sysCfgService.getById(ChanjetConstant.CFG_KEY_APP_TICKET);

        if (appTicketCfg == null) {
            return RestResp.failure("还未接收到APP_TICKET");
        }

        SysCfg tempAuthCodeCfg = sysCfgService.getById(ChanjetConstant.CFG_KEY_TEMP_AUTH_CODE);

        if (tempAuthCodeCfg == null) {
            return RestResp.failure("未找到TEMP_AUTH_CODE");
        }

        String appAccessToken = openApiService.getAppAccessToken(appTicketCfg.getCfgValue()).getAppAccessToken();

        String permanentAuthCode = openApiService.getPermanentAuthCode(appAccessToken, tempAuthCodeCfg.getCfgValue()).getPermanentAuthCode();

        SysCfg sysCfg = new SysCfg();
        sysCfg.setCfgCode(ChanjetConstant.CFG_KEY_PERMANENT_AUTH_CODE);
        sysCfg.setCfgValue(permanentAuthCode);
        sysCfg.setOrgFlow(orgId);
        sysCfg.setRecordStatus(StarTigerConstant.FLAG_Y);
        sysCfg.setLastModifyTime(LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));
        sysCfgService.saveOrUpdate(sysCfg);

        if (StrUtil.isBlank(permanentAuthCode)) {
            return RestResp.failure("获取PERMANENT_AUTH_CODE失败");
        }

        return RestResp.ok();
    }

    @GetMapping(path = "/refreshToken")
    @ResponseBody
    public RestResp<?> refreshToken(@RequestParam(name = "userId", required = false) String userId,
                                    @RequestParam(name = "tokenFlow", required = false) String tokenFlow,
                                    HttpServletRequest request) {

        if (StrUtil.isBlank(userId) && StrUtil.isBlank(tokenFlow)) {
            return RestResp.failure("必须通过userId或tokenFlow进行刷新");
        }

        TpToken token;

        if (StrUtil.isNotBlank(tokenFlow)) {
            token = tpTokenService.getById(tokenFlow);
        } else {
            QueryWrapper<TpToken> wrapper = new QueryWrapper<>();
            wrapper.eq("USER_ID", userId);
            token = tpTokenService.getOne(wrapper);
        }

        if (token != null) {

            AccessToken accessToken = openApiService.refreshToken(token.getRefreshToken());

            TpToken tpToken = EntityUtil.accessTokenToPo(accessToken);
            tpToken.setFlow(token.getFlow());
            tpToken.setUserName(token.getUserName());
            tpTokenService.saveOrUpdate(tpToken);
            logger.info("手动刷新AccessToken成功!");

            HttpSession session = request.getSession();
            session.setAttribute(Constant.TPLUS_TOKEN_SESSION_KEY, tpToken);

            return RestResp.ok();
        } else {
            return RestResp.failure("未找到AccessToken");
        }
    }
}
