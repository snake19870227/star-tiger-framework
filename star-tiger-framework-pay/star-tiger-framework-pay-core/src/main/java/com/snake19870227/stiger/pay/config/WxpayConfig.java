package com.snake19870227.stiger.pay.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.snake19870227.stiger.pay.PayConstant;
import com.snake19870227.stiger.pay.properties.StarTigerPayProperties;
import com.snake19870227.stiger.pay.properties.WxpayProperties;

/**
 * @author BuHuaYang
 * 2020/9/24
 */
@Configuration
public class WxpayConfig implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(WxpayConfig.class);

    private final StarTigerPayProperties starTigerPayProperties;

    public WxpayConfig(StarTigerPayProperties starTigerPayProperties) {
        logger.info("实例化配置类：" + this.getClass().getName());
        this.starTigerPayProperties = starTigerPayProperties;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        starTigerPayProperties.getWxpayMerchants().forEach((s, wxpayProperties) -> beanFactory.registerBeanDefinition(
                PayConstant.Wxpay.BEAN_PREFIX + wxpayProperties.getAppId(),
                BeanDefinitionBuilder.genericBeanDefinition(WxPayServiceImpl.class)
                        .addPropertyValue("config", wxpayConfig(wxpayProperties))
                        .getBeanDefinition()
        ));
    }

    private WxPayConfig wxpayConfig(WxpayProperties wxpayProperties) {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(StringUtils.trimToNull(wxpayProperties.getAppId()));
        payConfig.setMchId(StringUtils.trimToNull(wxpayProperties.getMchId()));
        payConfig.setMchKey(StringUtils.trimToNull(wxpayProperties.getMchKey()));
        payConfig.setKeyPath(StringUtils.trimToNull(wxpayProperties.getKeyPath()));
        payConfig.setNotifyUrl(wxpayProperties.getNotifyUrl());
        return payConfig;
    }
}
