package com.snake19870227.stiger.tplus.server.server.controller;

import cn.hutool.core.date.LocalDateTimeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.tplus.entity.po.TpToken;
import com.snake19870227.stiger.tplus.entity.po.ViewUserMenu;
import com.snake19870227.stiger.tplus.server.server.Constant;
import com.snake19870227.stiger.tplus.service.ITpTokenService;
import com.snake19870227.stiger.tplus.service.IViewUserMenuService;

/**
 * @author BuHuaYang
 * 8/22 022
 */
@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final IViewUserMenuService viewUserMenuService;

    private final ITpTokenService tpTokenService;

    public MainController(IViewUserMenuService viewUserMenuService, ITpTokenService tpTokenService) {
        this.viewUserMenuService = viewUserMenuService;
        this.tpTokenService = tpTokenService;
    }

    @GetMapping(path = "/main")
    public String mainPage() {
        return "main";
    }

    @GetMapping(path = "/authInfo")
    public String authInfo(HttpServletRequest request,
                           Model model) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute(Constant.USER_ID_SESSION_KEY);
        TpToken token = (TpToken) session.getAttribute(Constant.TPLUS_TOKEN_SESSION_KEY);
        String flow = token.getFlow();
        String userName = token.getUserName();
        String datetime = token.getDatetime();
        datetime = LocalDateTimeUtil.format(LocalDateTimeUtil.parse(datetime, "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss");
        String userAuthPermanentCode = token.getUserAuthPermanentCode();
        String accessToken = token.getAccessToken();
        model.addAttribute("tokenFlow", flow);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        model.addAttribute("datetime", datetime);
        model.addAttribute("userAuthPermanentCode", userAuthPermanentCode);
        model.addAttribute("accessToken", accessToken);

        QueryWrapper<ViewUserMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("USER_ID", userId);
        List<ViewUserMenu> userMenus = viewUserMenuService.list(wrapper);
        model.addAttribute("userMenus", userMenus);

        return "authInfo";
    }

    @PostMapping(path = "/userName/{tokenFlow}")
    public String saveUserName(@PathVariable(name = "tokenFlow") String tokenFlow,
                               @RequestParam(name = "userName") String userName,
                               HttpServletRequest request) {
        HttpSession session = request.getSession();
        TpToken token = tpTokenService.getById(tokenFlow);
        if (token != null) {
            token.setUserName(userName);
            tpTokenService.updateById(token);
            session.setAttribute(Constant.TPLUS_TOKEN_SESSION_KEY, token);
        }
        return "redirect:/authInfo";
    }

    @GetMapping(path = "/update")
    public String update() {
        return "update";
    }
}
