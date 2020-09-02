package com.snake19870227.stiger.oss.minio.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.snake19870227.stiger.oss.StarTigerOssProperties;
import com.snake19870227.stiger.oss.StarTigerOssStorage;
import com.snake19870227.stiger.oss.minio.MinioStarTigerOssStorage;

/**
 * @author BuHuaYang
 * 2020/9/2
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "stiger.oss.minio", name = "active", havingValue = "true")
@ConditionalOnClass(MinioClient.class)
public class MinioOssConfiguration {

    @Bean
    public StarTigerOssStorage minioStorage(StarTigerOssProperties ossProperties) throws InvalidPortException, InvalidEndpointException {
        StarTigerOssProperties.Minio minio = ossProperties.getMinio();
        MinioClient minioClient = new MinioClient(minio.getEndpoint(),
                minio.getAccessKey(),
                minio.getSecretKey());
        return new MinioStarTigerOssStorage(minioClient);
    }
}
