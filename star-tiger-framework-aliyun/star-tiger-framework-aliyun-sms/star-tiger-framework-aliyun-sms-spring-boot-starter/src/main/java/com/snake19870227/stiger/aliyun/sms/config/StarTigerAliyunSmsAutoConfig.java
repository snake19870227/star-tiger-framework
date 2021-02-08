package com.snake19870227.stiger.aliyun.sms.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.aliyuncs.profile.DefaultProfile;
import com.snake19870227.stiger.aliyun.sms.StarTigerAliyunSmsClient;
import com.snake19870227.stiger.aliyun.sms.StarTigerAliyunSmsProperties;

/**
 * @author BuHuaYang
 * 2021/2/8
 */
@Configuration
@ConditionalOnProperty(prefix = "stiger.aliyun.sms", name = "accesskey-id", havingValue = "")
@EnableConfigurationProperties(StarTigerAliyunSmsProperties.class)
public class StarTigerAliyunSmsAutoConfig {

    private final StarTigerAliyunSmsProperties aliyunSmsProperties;

    public StarTigerAliyunSmsAutoConfig(StarTigerAliyunSmsProperties aliyunSmsProperties) {
        this.aliyunSmsProperties = aliyunSmsProperties;
    }

    @ConditionalOnMissingBean
    @Bean
    public StarTigerAliyunSmsClient aliyunSmsClient() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliyunSmsProperties.getAccesskeyId(), aliyunSmsProperties.getAccesskeySecret());
        return new StarTigerAliyunSmsClient(profile);
    }
}
