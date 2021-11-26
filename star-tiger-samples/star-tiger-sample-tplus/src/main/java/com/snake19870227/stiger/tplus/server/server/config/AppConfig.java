package com.snake19870227.stiger.tplus.server.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.snake19870227.stiger.tplus.openapi.ChanjetClient;
import com.snake19870227.stiger.tplus.openapi.ChanjetOpenApiService;
import com.snake19870227.stiger.tplus.openapi.ChanjetTplusOpenApiService;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2021/05/08
 */
@Configuration
@EnableScheduling
public class AppConfig {

    @Value("${hpry.tplus.org-id}")
    private String orgId;
    @Value("${hpry.tplus.redirect-uri}")
    private String redirectUri;
    @Value("${hpry.tplus.app-key}")
    private String appKey;
    @Value("${hpry.tplus.app-secret}")
    private String appSecret;
    @Value("${hpry.tplus.iface-secret}")
    private String ifaceSecret;

    @Bean
    public ChanjetClient chanjetClient() {
        return new ChanjetClient(appKey, appSecret);
    }

    @Bean
    public ChanjetOpenApiService chanjetOpenApiService(ChanjetClient chanjetClient) {
        return new ChanjetOpenApiService(chanjetClient);
    }

    @Bean
    public ChanjetTplusOpenApiService chanjetTplusOpenApiService(ChanjetClient chanjetClient) {
        return new ChanjetTplusOpenApiService(chanjetClient);
    }
}
