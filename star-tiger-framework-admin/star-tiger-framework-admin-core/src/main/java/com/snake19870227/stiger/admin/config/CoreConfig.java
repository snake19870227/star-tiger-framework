package com.snake19870227.stiger.admin.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.snake19870227.stiger.admin.cache.SysCfgCache;
import com.snake19870227.stiger.admin.common.PasswordEncoderFactoryBean;
import com.snake19870227.stiger.admin.service.ISysExtService;
import com.snake19870227.stiger.admin.service.impl.SysExtServiceImpl;

/**
 * @author BuHuaYang
 * 2020/9/21
 */
@Configuration
@EnableCaching
public class CoreConfig {

    @Bean
    public SysCfgCache sysCfgCache() {
        return new SysCfgCache();
    }

    @Bean
    public PasswordEncoderFactoryBean passwordEncoderFactoryBean() {
        return new PasswordEncoderFactoryBean();
    }

    @Bean
    @ConditionalOnMissingBean
    public ISysExtService sysExtService(SysCfgCache sysCfgCache,
                                        PasswordEncoder passwordEncoder) {
        return new SysExtServiceImpl(sysCfgCache, passwordEncoder);
    }
}
