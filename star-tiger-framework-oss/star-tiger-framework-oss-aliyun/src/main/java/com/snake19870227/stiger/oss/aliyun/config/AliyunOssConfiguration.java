package com.snake19870227.stiger.oss.aliyun.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.aliyun.oss.OSSClient;
import com.snake19870227.stiger.oss.StarTigerOssProperties;
import com.snake19870227.stiger.oss.StarTigerOssStorage;
import com.snake19870227.stiger.oss.aliyun.AliyunStarTigerOssStorage;

/**
 * @author BuHuaYang
 * 2020/9/6
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "stiger.oss.aliyun", name = "active", havingValue = "true")
@ConditionalOnClass(OSSClient.class)
public class AliyunOssConfiguration {

    @Bean
    StarTigerOssStorage aliyunStorage(StarTigerOssProperties ossProperties) {
        StarTigerOssProperties.Aliyun aliyun = ossProperties.getAliyun();
        OSSClient ossClient = new OSSClient(aliyun.getEndpoint(), aliyun.getAccessKeyId(), aliyun.getAccessSecret());
        return new AliyunStarTigerOssStorage(ossClient);
    }
}
