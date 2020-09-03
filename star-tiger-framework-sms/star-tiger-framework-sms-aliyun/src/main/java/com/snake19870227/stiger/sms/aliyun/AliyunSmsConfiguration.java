package com.snake19870227.stiger.sms.aliyun;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.snake19870227.stiger.sms.aliyun.properties.StarTigerSmsAliyunProperties;

/**
 * @author BuHuaYang
 * 2020/9/3
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(StarTigerSmsAliyunProperties.class)
public class AliyunSmsConfiguration {

    private final StarTigerSmsAliyunProperties starTigerSmsAliyunProperties;

    public AliyunSmsConfiguration(StarTigerSmsAliyunProperties starTigerSmsAliyunProperties) {
        this.starTigerSmsAliyunProperties = starTigerSmsAliyunProperties;
    }

    @Bean
    public IAcsClient aliyunAcsClient() {
        DefaultProfile profile =
                DefaultProfile.getProfile(
                        starTigerSmsAliyunProperties.getRegionId(),
                        starTigerSmsAliyunProperties.getAccessKeyId(),
                        starTigerSmsAliyunProperties.getAccessSecret()
                );
        return new DefaultAcsClient(profile);
    }

    @Bean
    public AliyunSms aliyunSms(IAcsClient aliyunAcsClient) {
        return new AliyunSms(starTigerSmsAliyunProperties, aliyunAcsClient);
    }
}
