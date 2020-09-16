package com.snake19870227.stiger.admin.util;

import cn.hutool.core.util.IdUtil;

import java.util.Collections;

import com.snake19870227.stiger.admin.common.StarTigerAdminConstant;
import com.snake19870227.stiger.admin.entity.bo.UserInfo;
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.entity.po.SysRole;
import com.snake19870227.stiger.admin.entity.po.SysUser;
import com.snake19870227.stiger.core.StarTigerConstant;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/04/18
 */
public class RabcUtil {

    public static SysRole getSuperRole() {
        SysRole superRole = new SysRole();
        superRole.setRoleFlow(IdUtil.fastSimpleUUID());
        superRole.setRoleCode(StarTigerAdminConstant.SUPER_ROLE_CODE);
        superRole.setRoleName("超级管理员");
        return superRole;
    }

    public static SysResource getSuperResource() {
        SysResource resource = new SysResource();
        resource.setResFlow(IdUtil.fastSimpleUUID());
        resource.setResName("所有资源");
        resource.setResPath("/**");
        resource.setResMethod("");
        return resource;
    }

    public static UserInfo getRootUser(String username, String password) {
        SysUser rootUser = new SysUser();
        rootUser.setUserFlow(IdUtil.fastSimpleUUID())
                .setUsername(username)
                .setEncodePassword("{noop}" + password)
                .setShortName("超级管理员")
                .setLocked(StarTigerConstant.FLAG_N)
                .setExpired(StarTigerConstant.FLAG_N)
        ;
        return new UserInfo(rootUser,
                Collections.singletonList(getSuperRole()),
                Collections.singletonList(getSuperResource()));
    }

    public static SysRole getActuatorRole() {
        SysRole superRole = new SysRole();
        superRole.setRoleFlow(IdUtil.fastSimpleUUID());
        superRole.setRoleCode(StarTigerAdminConstant.ACTUATOR_ROLE_CODE);
        superRole.setRoleName("监控者");
        return superRole;
    }

    public static SysResource getActuatorResource() {
        SysResource resource = new SysResource();
        resource.setResFlow(IdUtil.fastSimpleUUID());
        resource.setResName("监控端点");
        resource.setResPath(StarTigerAdminConstant.UrlPath.ACTUATOR_PATTERN);
        resource.setResMethod("");
        return resource;
    }

    public static UserInfo getActuatorUser(String username, String password) {
        SysUser actuatorUser = new SysUser();
        actuatorUser.setUserFlow(IdUtil.fastSimpleUUID())
                .setUsername(username)
                .setEncodePassword("{noop}" + password)
                .setShortName("监控者")
                .setLocked(StarTigerConstant.FLAG_N)
                .setExpired(StarTigerConstant.FLAG_N)
        ;
        return new UserInfo(actuatorUser,
                Collections.singletonList(getActuatorRole()),
                Collections.singletonList(getActuatorResource()));
    }
}
