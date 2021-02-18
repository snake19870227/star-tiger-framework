package com.snake19870227.stiger.pay.server.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.snake19870227.stiger.pay.service.IPayNotifyService;
import com.snake19870227.stiger.pay.service.IPayRefundService;
import com.snake19870227.stiger.pay.service.IPayTradeService;
import com.snake19870227.stiger.pay.service.impl.PayNotifyServiceImpl;
import com.snake19870227.stiger.pay.service.impl.PayRefundServiceImpl;
import com.snake19870227.stiger.pay.service.impl.PayTradeServiceImpl;

/**
 * @author BuHuaYang
 * 2021/2/17
 */
@Configuration
@AutoConfigureAfter(StarTigerPayDatabaseConfig.class)
public class StarTigerPayServiceConfig {

    @Bean
    @ConditionalOnMissingBean
    public IPayTradeService payTradeService() {
        return new PayTradeServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IPayRefundService payRefundService() {
        return new PayRefundServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IPayNotifyService payNotifyService() {
        return new PayNotifyServiceImpl();
    }
}
