package com.snake19870227.stiger.admin.autoconfigure;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.snake19870227.stiger.admin.common.PasswordEncoderFactoryBean;
import com.snake19870227.stiger.admin.security.AuthAssert;
import com.snake19870227.stiger.admin.security.UserSecurityDetailManager;
import com.snake19870227.stiger.admin.service.ISysExtService;
import com.snake19870227.stiger.admin.service.ISysUserService;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/30
 */
@Configuration
@EnableCaching
public class StarTigerAdminAutoConfiguration {

    @Bean
    public PasswordEncoderFactoryBean passwordEncoderFactoryBean() {
        return new PasswordEncoderFactoryBean();
    }

    @Bean
    public UserSecurityDetailManager userSecurityDetailManager(ISysUserService sysUserService,
                                                               ISysExtService sysExtService) {
        return new UserSecurityDetailManager(sysUserService, sysExtService);
    }

    @Bean
    public AuthAssert authAssert() {
        return new AuthAssert();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setOverflow(false);
        paginationInterceptor.setLimit(500);
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
