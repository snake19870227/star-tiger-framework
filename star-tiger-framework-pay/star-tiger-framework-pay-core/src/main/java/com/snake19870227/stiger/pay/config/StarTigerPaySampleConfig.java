package com.snake19870227.stiger.pay.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.snake19870227.stiger.pay.channel.IPayStorage;
import com.snake19870227.stiger.pay.channel.SamplePayStorageImpl;

/**
 * @author BuHuaYang
 * 2021/2/17
 */
@Configuration
public class StarTigerPaySampleConfig {

    @Bean
    @ConditionalOnMissingBean(IPayStorage.class)
    public IPayStorage payStorage() {
        return new SamplePayStorageImpl();
    }
}
