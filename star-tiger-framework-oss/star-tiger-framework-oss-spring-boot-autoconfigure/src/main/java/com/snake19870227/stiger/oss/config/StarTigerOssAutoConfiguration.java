package com.snake19870227.stiger.oss.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.snake19870227.stiger.oss.StarTigerOssProperties;
import com.snake19870227.stiger.oss.minio.config.MinioOssConfiguration;

/**
 * @author BuHuaYang
 * 2020/9/2
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(StarTigerOssProperties.class)
@Import(MinioOssConfiguration.class)
public class StarTigerOssAutoConfiguration {
}
