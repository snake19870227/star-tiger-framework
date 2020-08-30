package com.snake19870227.stiger.admin.manager.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snake19870227.stiger.admin.entity.po.SysUser;
import com.snake19870227.stiger.admin.entity.po.SysUserRole;
import com.snake19870227.stiger.admin.manager.entity.dto.UserRoleDto;
import com.snake19870227.stiger.admin.service.ISysExtService;
import com.snake19870227.stiger.admin.service.ISysUserRoleService;
import com.snake19870227.stiger.admin.service.ISysUserService;
import com.snake19870227.stiger.web.exception.BaseControllerException;
import com.snake19870227.stiger.web.exception.MvcException;
import com.snake19870227.stiger.web.restful.RestResp;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/17
 */
@Controller
@RequestMapping(path = "/sys/user")
public class SysUserController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    private final ISysUserService sysUserService;

    private final ISysUserRoleService sysUserRoleService;

    private final ISysExtService sysExtService;

    public SysUserController(ISysUserService sysUserService,
                             ISysUserRoleService sysUserRoleService,
                             ISysExtService sysExtService) {
        this.sysUserService = sysUserService;
        this.sysUserRoleService = sysUserRoleService;
        this.sysExtService = sysExtService;
    }

    @GetMapping(path = "/main")
    public String main() {
        return "sys/user/main";
    }

    @GetMapping(path = "/data")
    @ResponseBody
    public RestResp<Page<SysUser>> data(@RequestParam(name = "shortName", required = false) String shortName,
                                        @RequestParam(name = "locked", required = false) String locked,
                                        @RequestParam(name = "expired", required = false) String expired,
                                        @RequestParam(name = "page", defaultValue = "1") Long page,
                                        @RequestParam(name = "limit", defaultValue = "10") Long limit) {

        Page<SysUser> pageInfo = new Page<>(page, limit);

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();

        if (StrUtil.isNotBlank(shortName)) {
            queryWrapper.like("short_name", shortName);
        }

        if (StrUtil.isNotBlank(locked)) {
            queryWrapper.eq("locked", locked);
        }

        if (StrUtil.isNotBlank(expired)) {
            queryWrapper.eq("expired", expired);
        }

        queryWrapper.orderByDesc("create_date_time");

        pageInfo = sysUserService.page(pageInfo, queryWrapper);

        /**
         * 脱敏、日期格式转换
         */
        pageInfo.getRecords().forEach(user -> {
            user.setEncodePassword("");
            user.setCreateDateTime(DateUtil.format(DateUtil.parse(user.getCreateDateTime(), "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss"));
        });

        return RestResp.buildResp("10000", pageInfo);
    }

    @GetMapping(path = "/{userFlow}")
    @ResponseBody
    public RestResp<UserRoleDto> read(@PathVariable(name = "userFlow") String userFlow) {

        SysUser user = sysUserService.getById(userFlow);

        /**
         * 脱敏
         */
        user.setEncodePassword("");

        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_flow", userFlow);
        List<SysUserRole> userRoles = sysUserRoleService.list(queryWrapper);
        String[] roleFlows = userRoles.stream().map(SysUserRole::getRoleFlow).toArray(String[]::new);

        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setUser(user);
        userRoleDto.setRoleFlows(roleFlows);

        return RestResp.buildResp("10000", userRoleDto);
    }

    @PostMapping
    @ResponseBody
    public RestResp<?> add(@RequestBody UserRoleDto userRoleDto) {

        sysExtService.addUser(userRoleDto.getUser(), Arrays.asList(userRoleDto.getRoleFlows()));

        return RestResp.buildResp("10000");
    }

    @PutMapping
    @ResponseBody
    public RestResp<?> mod(@RequestBody UserRoleDto userRoleDto) {

        sysExtService.modUser(userRoleDto.getUser(), Arrays.asList(userRoleDto.getRoleFlows()));

        return RestResp.buildResp("10000");
    }

    @PutMapping(path = "/lock/{userFlow}/{flag}")
    @ResponseBody
    public RestResp<?> changeLockStatus(@PathVariable(name = "userFlow") String userFlow,
                                        @PathVariable(name = "flag") String flag) {
        try {
            SysUser updater = new SysUser();
            updater.setUserFlow(userFlow);
            updater.setLocked(flag);
            boolean result = sysUserService.updateById(updater);
            if (result) {
                return RestResp.buildResp("10000");
            } else {
                return RestResp.buildResp("60002");
            }
        } catch (BaseControllerException e) {
            throw e;
        } catch (Exception e) {
            throw new MvcException("50000", e);
        }
    }

    @PutMapping(path = "/resetPassword/{userFlow}")
    @ResponseBody
    public RestResp<?> resetPassword(@PathVariable(name = "userFlow") String userFlow) {

        sysExtService.resetUserPassword(userFlow);

        return RestResp.buildResp("10000");
    }
}
