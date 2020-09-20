package com.snake19870227.stiger.admin.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.snake19870227.stiger.admin.common.PasswordEncoderFactoryBean;
import com.snake19870227.stiger.admin.config.DaoConfig;
import com.snake19870227.stiger.admin.config.ServiceConfig;
import com.snake19870227.stiger.admin.properties.StarTigerAdminRabcProperties;
import com.snake19870227.stiger.admin.security.AuthAssert;
import com.snake19870227.stiger.admin.security.UserSecurityDetailManager;
import com.snake19870227.stiger.admin.service.ISysExtService;
import com.snake19870227.stiger.admin.service.ISysUserService;
import com.snake19870227.stiger.admin.service.impl.SysExtServiceImpl;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/30
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(StarTigerAdminRabcProperties.class)
@EnableTransactionManagement(proxyTargetClass = true)
@Import({DaoConfig.class, ServiceConfig.class})
public class StarTigerAdminAutoConfiguration {

    private final StarTigerAdminRabcProperties starTigerAdminRabcProperties;

    public StarTigerAdminAutoConfiguration(StarTigerAdminRabcProperties starTigerAdminRabcProperties) {
        this.starTigerAdminRabcProperties = starTigerAdminRabcProperties;
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
    public PasswordEncoderFactoryBean passwordEncoderFactoryBean() {
        return new PasswordEncoderFactoryBean();
    }

    @Bean
    @ConditionalOnMissingBean
    public ISysExtService sysExtService(PasswordEncoder passwordEncoder) {
        return new SysExtServiceImpl(passwordEncoder);
    }
}
