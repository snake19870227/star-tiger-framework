package com.snake19870227.stiger.aliyun.dypls.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.aliyuncs.profile.DefaultProfile;
import com.snake19870227.stiger.aliyun.dypls.AliyunDyplsClient;
import com.snake19870227.stiger.aliyun.dypls.StarTigerAliyunDyplsProperties;

/**
 * @author BuHuaYang
 * 2021/2/4
 */
@Configuration
@ConditionalOnProperty(prefix = "stiger.aliyun.dypls", name = "accesskey-id", havingValue = "")
@EnableConfigurationProperties(StarTigerAliyunDyplsProperties.class)
public class AliyunDyplsAutoConfig {

    private final StarTigerAliyunDyplsProperties aliyunDyplsProperties;

    public AliyunDyplsAutoConfig(StarTigerAliyunDyplsProperties aliyunDyplsProperties) {
        this.aliyunDyplsProperties = aliyunDyplsProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public AliyunDyplsClient aliyunDyplsClient() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliyunDyplsProperties.getAccesskeyId(), aliyunDyplsProperties.getAccesskeySecret());
        return new AliyunDyplsClient(aliyunDyplsProperties.getPoolKey(), profile);
    }
}
