package com.snake19870227.stiger.aliyun.dypls.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.snake19870227.stiger.aliyun.dypls.controller.DyplsController;
import com.snake19870227.stiger.aliyun.dypls.controller.DyplsReportController;
import com.snake19870227.stiger.aliyun.dypls.service.IAliDyplsBindService;
import com.snake19870227.stiger.aliyun.dypls.service.IAliDyplsCallService;

/**
 * @author BuHuaYang
 * 2021/2/4
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(AliyunDyplsServerMarkerConfig.Marker.class)
@Import({
        AliyunDyplsDatabaseConfig.class,
        AliyunDyplsServiceConfig.class
})
public class AliyunDyplsServerAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public DyplsController dyplsController(IAliDyplsBindService aliDyplsBindService) {
        return new DyplsController(aliDyplsBindService);
    }

    @Bean
    @ConditionalOnMissingBean
    public DyplsReportController dyplsReportController(IAliDyplsBindService aliDyplsBindService,
                                                       IAliDyplsCallService aliDyplsCallService) {
        return new DyplsReportController(aliDyplsBindService, aliDyplsCallService);
    }
}
