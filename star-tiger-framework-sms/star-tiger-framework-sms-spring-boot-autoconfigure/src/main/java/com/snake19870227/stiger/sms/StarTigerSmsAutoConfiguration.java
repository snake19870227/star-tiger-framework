package com.snake19870227.stiger.sms;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.snake19870227.stiger.sms.aliyun.AliyunSmsConfiguration;

/**
 * @author BuHuaYang
 * 2020/9/3
 */
@Configuration(proxyBeanMethods = false)
public class StarTigerSmsAutoConfiguration {

    @Configuration
    @ConditionalOnClass(AliyunSmsConfiguration.class)
    @Import(AliyunSmsConfiguration.class)
    public static class AliyunSmsAutoConfiguration {

    }
}
