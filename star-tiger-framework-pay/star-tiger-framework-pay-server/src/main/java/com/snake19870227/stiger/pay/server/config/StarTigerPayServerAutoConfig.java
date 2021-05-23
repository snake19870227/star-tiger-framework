package com.snake19870227.stiger.pay.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.snake19870227.stiger.admin.dao.base.SysCfgMapper;
import com.snake19870227.stiger.pay.channel.IPayStorage;
import com.snake19870227.stiger.pay.channel.alipay.AlipayChannelHandler;
import com.snake19870227.stiger.pay.channel.wxpay.WxpayChannelHandler;
import com.snake19870227.stiger.pay.dao.base.PayNotifyMapper;
import com.snake19870227.stiger.pay.dao.base.PayRefundMapper;
import com.snake19870227.stiger.pay.dao.base.PayTradeMapper;
import com.snake19870227.stiger.pay.properties.StarTigerPayProperties;
import com.snake19870227.stiger.pay.server.DatabasePayStorageImpl;
import com.snake19870227.stiger.pay.server.controller.NotifyController;
import com.snake19870227.stiger.pay.server.controller.PayController;

/**
 * @author BuHuaYang
 * 2021/2/17
 */
@Configuration
@Import({
        StarTigerPayDatabaseConfig.class,
        StarTigerPayServiceConfig.class
})
public class StarTigerPayServerAutoConfig {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerPayServerAutoConfig.class);

    public StarTigerPayServerAutoConfig() {
        logger.info("实例化配置类：" + this.getClass().getName());
    }

    @Bean
    @ConditionalOnBean(StarTigerPayServerMarkerConfig.Marker.class)
    @ConditionalOnMissingBean(IPayStorage.class)
    public IPayStorage payStorage(SysCfgMapper sysCfgMapper,
                                  PayTradeMapper payTradeMapper,
                                  PayRefundMapper payRefundMapper,
                                  PayNotifyMapper payNotifyMapper) {
        return new DatabasePayStorageImpl(sysCfgMapper, payTradeMapper, payRefundMapper, payNotifyMapper);
    }

    @Bean
    public AlipayChannelHandler alipayChannelHandler(IPayStorage payStorage,
                                                     StarTigerPayProperties starTigerPayProperties) {
        return new AlipayChannelHandler(starTigerPayProperties, payStorage);
    }

    @Bean
    public WxpayChannelHandler wxpayChannelHandler(IPayStorage payStorage,
                                                   StarTigerPayProperties starTigerPayProperties) {
        return new WxpayChannelHandler(starTigerPayProperties, payStorage);
    }

    @Bean
    public PayController payController(IPayStorage payStorage) {
        return new PayController(payStorage);
    }

    @Bean
    public NotifyController notifyController(StarTigerPayProperties starTigerPayProperties) {
        return new NotifyController(starTigerPayProperties);
    }
}
