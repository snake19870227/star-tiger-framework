package com.snake19870227.stiger.pay.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author BuHuaYang
 * 2021/2/4
 */
@Configuration(proxyBeanMethods = false)
public class StarTigerPayServerMarkerConfig {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerPayServerMarkerConfig.class);

    public StarTigerPayServerMarkerConfig() {
        logger.info("实例化配置类：" + this.getClass().getName());
    }

    @Bean
    public Marker payServerMarker() {
        return new Marker();
    }

    public static class Marker {

    }
}
