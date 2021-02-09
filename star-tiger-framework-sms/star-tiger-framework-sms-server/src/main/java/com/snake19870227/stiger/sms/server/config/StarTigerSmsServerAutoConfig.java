package com.snake19870227.stiger.sms.server.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.snake19870227.stiger.sms.server.controller.SmsApiController;
import com.snake19870227.stiger.sms.service.ISmsLogService;
import com.snake19870227.stiger.sms.service.ISmsTemplateService;

/**
 * @author BuHuaYang
 * 2021/2/9
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(StarTigerSmsServerMarkerConfig.Marker.class)
@Import({
        StarTigerSmsDatabaseConfig.class,
        StarTigerSmsServiceConfig.class
})
public class StarTigerSmsServerAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public SmsApiController smsApiController(ISmsLogService smsLogService,
                                             ISmsTemplateService smsTemplateService) {
        return new SmsApiController(smsLogService, smsTemplateService);
    }
}
