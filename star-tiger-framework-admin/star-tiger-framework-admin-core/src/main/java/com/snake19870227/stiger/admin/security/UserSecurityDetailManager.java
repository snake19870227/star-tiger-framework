package com.snake19870227.stiger.admin.security;

import cn.hutool.core.util.StrUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.admin.common.StarTigerAdminConstant;
import com.snake19870227.stiger.admin.entity.bo.UserInfo;
import com.snake19870227.stiger.admin.entity.po.SysUser;
import com.snake19870227.stiger.admin.properties.StarTigerAdminRabcProperties;
import com.snake19870227.stiger.admin.service.ISysExtService;
import com.snake19870227.stiger.admin.service.ISysUserService;
import com.snake19870227.stiger.admin.util.RabcUtil;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/07/21
 */
public class UserSecurityDetailManager implements UserDetailsManager {

    private static final Logger logger = LoggerFactory.getLogger(UserSecurityDetailManager.class);

    private final StarTigerAdminRabcProperties starTigerAdminRabcProperties;

    private final ISysUserService sysUserService;

    private final ISysExtService sysExtService;

    public UserSecurityDetailManager(StarTigerAdminRabcProperties starTigerAdminRabcProperties,
                                     ISysUserService sysUserService,
                                     ISysExtService sysExtService) {
        this.starTigerAdminRabcProperties = starTigerAdminRabcProperties;
        this.sysUserService = sysUserService;
        this.sysExtService = sysExtService;
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return sysUserService.count(wrapper) == 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = null;

        StarTigerAdminRabcProperties.DefaultUsers defaultUsers = starTigerAdminRabcProperties.getDefaultUsers();
        if (StrUtil.equals(username, defaultUsers.getSuperAdminUsername())) {
            userInfo = RabcUtil.getRootUser(defaultUsers.getSuperAdminUsername(), defaultUsers.getSuperAdminPassword());
        } else if (StrUtil.equals(username, defaultUsers.getActuatorAdminUsername())) {
            userInfo = RabcUtil.getActuatorUser(defaultUsers.getActuatorAdminUsername(), defaultUsers.getActuatorAdminPassword());
        } else {
            userInfo = sysExtService.getUserInfo(username);
        }

        if (userInfo == null) {
            throw new UsernameNotFoundException(StrUtil.format("未找到用户名[{}]对应的账户", username));
        }

        return new UserSecurityDetail(userInfo);
    }
}
