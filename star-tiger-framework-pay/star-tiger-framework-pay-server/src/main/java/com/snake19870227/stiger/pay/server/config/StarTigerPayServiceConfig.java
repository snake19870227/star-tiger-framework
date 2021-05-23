package com.snake19870227.stiger.pay.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(StarTigerPayServiceConfig.class);

    public StarTigerPayServiceConfig() {
        logger.info("实例化配置类：" + this.getClass().getName());
    }

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
