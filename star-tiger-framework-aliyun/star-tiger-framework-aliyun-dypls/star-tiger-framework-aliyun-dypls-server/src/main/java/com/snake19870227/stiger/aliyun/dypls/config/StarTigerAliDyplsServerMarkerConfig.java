package com.snake19870227.stiger.aliyun.dypls.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author BuHuaYang
 * 2021/2/4
 */
@Configuration(proxyBeanMethods = false)
public class StarTigerAliDyplsServerMarkerConfig {

    @Bean
    public Marker aliyunDyplsServerMarker() {
        return new Marker();
    }

    public static class Marker {

    }
}
