package com.snake19870227.stiger.pay.config;

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
        StarTigerPaySampleConfig.class,
        AlipayConfig.class,
        WxpayConfig.class
})
public class StarTigerPayAutoConfig {
}
