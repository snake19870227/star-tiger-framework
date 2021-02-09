package com.snake19870227.stiger.sms.server.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.snake19870227.stiger.sms.service.ISmsLogService;
import com.snake19870227.stiger.sms.service.ISmsTemplateService;
import com.snake19870227.stiger.sms.service.impl.SmsLogServiceImpl;
import com.snake19870227.stiger.sms.service.impl.SmsTemplateServiceImpl;

/**
 * @author BuHuaYang
 * 2021/2/4
 */
@Configuration
@AutoConfigureAfter(StarTigerSmsDatabaseConfig.class)
public class StarTigerSmsServiceConfig {

    @Bean
    @ConditionalOnMissingBean
    public ISmsLogService smsLogService() {
        return new SmsLogServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public ISmsTemplateService smsTemplateService() {
        return new SmsTemplateServiceImpl();
    }
}
