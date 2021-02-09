package com.snake19870227.stiger.aliyun.dypls.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.snake19870227.stiger.aliyun.dypls.service.IAliDyplsBindService;
import com.snake19870227.stiger.aliyun.dypls.service.IAliDyplsCallService;
import com.snake19870227.stiger.aliyun.dypls.service.impl.AliDyplsBindServiceImpl;
import com.snake19870227.stiger.aliyun.dypls.service.impl.AliDyplsCallServiceImpl;

/**
 * @author BuHuaYang
 * 2021/2/4
 */
@Configuration
@AutoConfigureAfter(AliyunDyplsDatabaseConfig.class)
public class AliyunDyplsServiceConfig {

    @Bean
    @ConditionalOnMissingBean
    public IAliDyplsBindService aliDyplsBindService() {
        return new AliDyplsBindServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IAliDyplsCallService aliDyplsCallService() {
        return new AliDyplsCallServiceImpl();
    }
}
