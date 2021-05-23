package com.snake19870227.stiger.pay.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.snake19870227.stiger.pay.properties.StarTigerPayProperties;

/**
 * @author BuHuaYang
 * 2021/2/17
 */
@Configuration
@EnableConfigurationProperties(StarTigerPayProperties.class)
@Import({
        AlipayConfig.class,
        WxpayConfig.class
})
public class StarTigerPayAutoConfig {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerPayAutoConfig.class);

    public StarTigerPayAutoConfig() {
        logger.info("实例化配置类：" + this.getClass().getName());
    }
}
