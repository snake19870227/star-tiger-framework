package com.snake19870227.stiger.admin.manager.entity.dto;

import com.snake19870227.stiger.admin.entity.po.SysUser;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/17
 */
public class UserRoleDto {

    private SysUser user;

    private String[] roleFlows;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public String[] getRoleFlows() {
        return roleFlows;
    }

    public void setRoleFlows(String[] roleFlows) {
        this.roleFlows = roleFlows;
    }
}
