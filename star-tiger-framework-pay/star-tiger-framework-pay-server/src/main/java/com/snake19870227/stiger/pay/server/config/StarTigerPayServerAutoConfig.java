package com.snake19870227.stiger.pay.server.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.snake19870227.stiger.admin.dao.base.SysCfgMapper;
import com.snake19870227.stiger.pay.channel.IPayStorage;
import com.snake19870227.stiger.pay.config.StarTigerPaySampleConfig;
import com.snake19870227.stiger.pay.dao.base.PayTradeMapper;
import com.snake19870227.stiger.pay.server.DatabasePayStorageImpl;

/**
 * @author BuHuaYang
 * 2021/2/17
 */
@Configuration
@AutoConfigureBefore(StarTigerPaySampleConfig.class)
@Import({
        StarTigerPayDatabaseConfig.class,
        StarTigerPayServiceConfig.class
})
public class StarTigerPayServerAutoConfig {

    @Bean
    @ConditionalOnBean(StarTigerPayServerMarkerConfig.Marker.class)
    @ConditionalOnMissingBean(IPayStorage.class)
    public IPayStorage payStorage(SysCfgMapper sysCfgMapper, PayTradeMapper payTradeMapper) {
        return new DatabasePayStorageImpl(sysCfgMapper, payTradeMapper);
    }
}
