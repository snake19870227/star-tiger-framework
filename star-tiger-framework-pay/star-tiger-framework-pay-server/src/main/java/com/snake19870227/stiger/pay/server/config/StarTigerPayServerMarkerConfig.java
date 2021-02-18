package com.snake19870227.stiger.pay.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author BuHuaYang
 * 2021/2/4
 */
@Configuration(proxyBeanMethods = false)
public class StarTigerPayServerMarkerConfig {

    @Bean
    public Marker payServerMarker() {
        return new Marker();
    }

    public static class Marker {

    }
}
