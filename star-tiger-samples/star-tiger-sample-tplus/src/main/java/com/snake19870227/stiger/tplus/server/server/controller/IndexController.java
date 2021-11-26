package com.snake19870227.stiger.tplus.server.server.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.tplus.entity.po.TpToken;
import com.snake19870227.stiger.tplus.openapi.ChanjetOpenApiService;
import com.snake19870227.stiger.tplus.server.server.Constant;
import com.snake19870227.stiger.tplus.service.ITpTokenService;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Value("${hpry.tplus.org-id}")
    private String orgId;
    @Value("${hpry.tplus.redirect-uri}")
    private String tplusRedirectUrl;

    @Autowired
    private ChanjetOpenApiService openApiService;

    private final ITpTokenService tpTokenService;

    public IndexController(ITpTokenService tpTokenService) {
        this.tpTokenService = tpTokenService;
    }

    @GetMapping(path = "/")
    public String root(HttpServletRequest request,
                       Model model) {
        HttpSession session = request.getSession();
        Cookie cookie = ServletUtil.getCookie(request, Constant.USER_ID_COOKIE_KEY);
        if (cookie != null) {
            QueryWrapper<TpToken> wrapper = new QueryWrapper<>();
            wrapper.eq("USER_ID", cookie.getValue());
            TpToken token = tpTokenService.getOne(wrapper);
            if (token != null) {
                logger.info("通过cookie查询到已有token[{}]", token.getFlow());
                session.setAttribute(Constant.USER_ID_SESSION_KEY, token.getUserId());
                session.setAttribute(Constant.TPLUS_TOKEN_SESSION_KEY, token);
            }
        }
        String userId = (String) session.getAttribute(Constant.USER_ID_SESSION_KEY);
        if (StrUtil.isNotBlank(userId)) {
            return "redirect:/authInfo";
        }
        String authUrl = openApiService.buildAuthUrl(orgId, tplusRedirectUrl);
        model.addAttribute("authUrl", authUrl);
        return "index";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constant.USER_ID_SESSION_KEY);
        session.removeAttribute(Constant.TPLUS_TOKEN_SESSION_KEY);
        ServletUtil.addCookie(response, Constant.USER_ID_COOKIE_KEY, "", 60 * 60 * 24 * 7);
        return "redirect:/";
    }
}
