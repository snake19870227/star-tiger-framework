package com.snake19870227.stiger.admin.manager.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.snake19870227.stiger.admin.common.TreeNode;
import com.snake19870227.stiger.admin.entity.po.SysCfg;
import com.snake19870227.stiger.admin.manager.common.layui.HomeInfo;
import com.snake19870227.stiger.admin.manager.common.layui.InitInfo;
import com.snake19870227.stiger.admin.manager.common.layui.LogoInfo;
import com.snake19870227.stiger.admin.manager.common.layui.MenuInfo;
import com.snake19870227.stiger.admin.entity.po.SysMenu;
import com.snake19870227.stiger.admin.entity.po.SysUser;
import com.snake19870227.stiger.admin.manager.properties.StarTigerAdminLayuiProperties;
import com.snake19870227.stiger.admin.security.UserSecurityDetail;
import com.snake19870227.stiger.admin.service.ISysCfgService;
import com.snake19870227.stiger.admin.service.ISysExtService;
import com.snake19870227.stiger.admin.service.ISysUserService;
import com.snake19870227.stiger.admin.web.StarTigerAdminController;
import com.snake19870227.stiger.core.exception.OptException;
import com.snake19870227.stiger.core.exception.ServiceException;
import com.snake19870227.stiger.web.exception.MvcException;
import com.snake19870227.stiger.web.restful.RestResp;

import static com.snake19870227.stiger.admin.common.StarTigerAdminConstant.UrlPath;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/07/23
 */
@StarTigerAdminController
public class MainController {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private final StarTigerAdminLayuiProperties starTigerAdminLayuiProperties;

    private final PasswordEncoder passwordEncoder;

    private final ISysUserService sysUserService;

    private final ISysExtService sysExtService;

    private final ISysCfgService sysCfgService;

    public MainController(StarTigerAdminLayuiProperties starTigerAdminLayuiProperties,
                          PasswordEncoder passwordEncoder,
                          ISysUserService sysUserService,
                          ISysExtService sysExtService, ISysCfgService sysCfgService) {
        this.starTigerAdminLayuiProperties = starTigerAdminLayuiProperties;
        this.passwordEncoder = passwordEncoder;
        this.sysUserService = sysUserService;
        this.sysExtService = sysExtService;
        this.sysCfgService = sysCfgService;
    }

    @GetMapping(path = UrlPath.MAIN)
    public String main() {

        return "main";
    }

    @GetMapping(path = UrlPath.INIT)
    @ResponseBody
    public InitInfo init() {

        UserSecurityDetail detail =
                (UserSecurityDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        InitInfo initInfo = new InitInfo();

        LogoInfo logoInfo = new LogoInfo();
        logoInfo.setTitle(starTigerAdminLayuiProperties.getInit().getLogoInfo().getTitle());
        logoInfo.setImage(starTigerAdminLayuiProperties.getInit().getLogoInfo().getImageUrl());

        HomeInfo homeInfo = new HomeInfo();
        homeInfo.setTitle("工作台");
        homeInfo.setHref(contextPath + UrlPath.WORKBENCH);

        List<TreeNode<SysMenu>> menuTree = sysExtService.treeMenu(detail);

        for (TreeNode<SysMenu> treeNode : menuTree) {
            initInfo.getMenuInfo().add(buildMenuInfo(treeNode));
        }

        initInfo.setLogoInfo(logoInfo);
        initInfo.setHomeInfo(homeInfo);

        return initInfo;
    }

    @GetMapping(path = UrlPath.WORKBENCH)
    public String workbench() {
        SysCfg workbenchPage = sysCfgService.getById("workbench_page");
        if (workbenchPage != null && StrUtil.isNotBlank(workbenchPage.getCfgValue())) {
            return workbenchPage.getCfgValue();
        } else {
            return "default-workbench";
        }
    }

    @GetMapping(path = UrlPath.USER_PASSWORD)
    public String userPassword() {
        return "user-pwd";
    }

    @PutMapping(path = UrlPath.USER_PASSWORD)
    @ResponseBody
    public RestResp<?> changePassword(@RequestParam(name = "oldPwd") String oldPwd,
                                      @RequestParam(name = "newPwd") String newPwd) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserSecurityDetail userSecurityDetail = (UserSecurityDetail) authentication.getPrincipal();
            if (userSecurityDetail != null) {
                SysUser user = sysUserService.getById(userSecurityDetail.getUser().getUserFlow());
                if (passwordEncoder.matches(oldPwd, user.getEncodePassword())) {
                    user.setEncodePassword(passwordEncoder.encode(newPwd));
                    sysUserService.updateById(user);
                    return RestResp.buildResp("10000");
                } else {
                    return RestResp.buildResp("30001");
                }
            }
        }

        return RestResp.buildResp("30000");
    }

    @GetMapping(path = "/aaa")
    public String aaa() {
        try {
            int i = 1 / 0;

            return "main";
        } catch (Exception e) {
            throw new MvcException("50000", new ServiceException("我是service异常", new OptException("我是opt异常", e)));
        }
    }

    private MenuInfo buildMenuInfo(TreeNode<SysMenu> treeNode) {
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setTitle(treeNode.getTitle());
        if (StrUtil.isNotBlank(treeNode.getData().getMenuPath())) {
            menuInfo.setHref(contextPath + treeNode.getData().getMenuPath());
        }
        if (CollUtil.isNotEmpty(treeNode.getChildren())) {
            for (TreeNode<SysMenu> treeNodeChild : treeNode.getChildren()) {
                menuInfo.getChild().add(buildMenuInfo(treeNodeChild));
            }
        }
        return menuInfo;
    }
}
