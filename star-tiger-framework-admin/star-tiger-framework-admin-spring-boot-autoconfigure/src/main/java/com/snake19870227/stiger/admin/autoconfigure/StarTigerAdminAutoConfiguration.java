package com.snake19870227.stiger.admin.autoconfigure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.snake19870227.stiger.admin.common.PasswordEncoderFactoryBean;
import com.snake19870227.stiger.admin.properties.StarTigerAdminRabcProperties;
import com.snake19870227.stiger.admin.security.AuthAssert;
import com.snake19870227.stiger.admin.security.UserSecurityDetailManager;
import com.snake19870227.stiger.admin.service.ISysCfgService;
import com.snake19870227.stiger.admin.service.ISysDeptService;
import com.snake19870227.stiger.admin.service.ISysDictItemService;
import com.snake19870227.stiger.admin.service.ISysDictService;
import com.snake19870227.stiger.admin.service.ISysExtService;
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
import com.snake19870227.stiger.admin.service.impl.SysExtServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysMenuServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysModuleServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysOrgServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysResourceServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysRoleResourceServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysRoleServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysUserRoleServiceImpl;
import com.snake19870227.stiger.admin.service.impl.SysUserServiceImpl;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/30
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(StarTigerAdminRabcProperties.class)
@AutoConfigureAfter(MybatisPlusAutoConfiguration.class)
@MapperScan(basePackages = "com.snake19870227.stiger.admin.dao")
@EnableTransactionManagement(proxyTargetClass = true)
public class StarTigerAdminAutoConfiguration {

    private final StarTigerAdminRabcProperties starTigerAdminRabcProperties;

    public StarTigerAdminAutoConfiguration(StarTigerAdminRabcProperties starTigerAdminRabcProperties) {
        this.starTigerAdminRabcProperties = starTigerAdminRabcProperties;
    }

    @Bean
    public PasswordEncoderFactoryBean passwordEncoderFactoryBean() {
        return new PasswordEncoderFactoryBean();
    }

    @Bean
    public UserSecurityDetailManager userSecurityDetailManager(ISysUserService sysUserService,
                                                               ISysExtService sysExtService) {
        return new UserSecurityDetailManager(starTigerAdminRabcProperties, sysUserService, sysExtService);
    }

    @Bean
    public AuthAssert authAssert() {
        return new AuthAssert();
    }

    @Bean
    @ConditionalOnMissingBean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setOverflow(false);
        paginationInterceptor.setLimit(500);
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }

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
    public ISysExtService sysExtService(PasswordEncoder passwordEncoder) {
        return new SysExtServiceImpl(passwordEncoder);
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
