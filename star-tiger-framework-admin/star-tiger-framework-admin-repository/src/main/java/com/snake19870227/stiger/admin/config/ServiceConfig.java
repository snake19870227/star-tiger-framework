package com.snake19870227.stiger.admin.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.snake19870227.stiger.admin.service.ISysCfgService;
import com.snake19870227.stiger.admin.service.ISysDeptService;
import com.snake19870227.stiger.admin.service.ISysDictItemService;
import com.snake19870227.stiger.admin.service.ISysDictService;
import com.snake19870227.stiger.admin.service.ISysMenuService;
import com.snake19870227.stiger.admin.service.ISysModuleService;
import com.snake19870227.stiger.admin.service.ISysOrgService;
import com.snake19870227.stiger.admin.service.ISysResourceService;
import com.snake19870227.stiger.admin.service.ISysRoleResourceService;
import com.snake19870227.stiger.admin.service.ISysRoleService;
import com.snake19870227.stiger.admin.service.ISysUserRoleService;
import com.snake19870227.stiger.admin.service.ISysUserService;
import com.snake19870227.stiger.admin.service.impl.SysCfgServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysDeptServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysDictItemServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysDictServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysMenuServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysModuleServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysOrgServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysResourceServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysRoleResourceServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysRoleServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysUserRoleServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysUserServiceImpl;

/**
 * @author BuHuaYang
 * 2020/9/20
 */
@Configuration
@AutoConfigureAfter(DaoConfig.class)
public class ServiceConfig {

    @Bean
    @ConditionalOnMissingBean
    public ISysCfgService sysCfgService() {
        return new SysCfgServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public ISysDeptService sysDeptService() {
        return new SysDeptServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public ISysDictItemService sysDictItemService() {
        return new SysDictItemServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public ISysDictService sysDictService() {
        return new SysDictServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public ISysMenuService sysMenuService() {
        return new SysMenuServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public ISysModuleService sysModuleService() {
        return new SysModuleServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public ISysOrgService sysOrgService() {
        return new SysOrgServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public ISysResourceService sysResourceService() {
        return new SysResourceServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public ISysRoleResourceService sysRoleResourceService() {
        return new SysRoleResourceServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public ISysRoleService sysRoleService() {
        return new SysRoleServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public ISysUserRoleService sysUserRoleService() {
        return new SysUserRoleServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public ISysUserService sysUserService() {
        return new SysUserServiceImpl();
    }
}
