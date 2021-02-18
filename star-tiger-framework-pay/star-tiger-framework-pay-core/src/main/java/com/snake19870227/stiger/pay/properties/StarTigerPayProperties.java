package com.snake19870227.stiger.pay.properties;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/25
 */
@ConfigurationProperties(prefix = "stiger.pay")
public class StarTigerPayProperties {

    private Map<String, WxpayProperties> wxpayMerchants;

    private Map<String, AlipayProperties> alipayMerchants;

    private Map<String, String> bizMerchants;

    private Map<String, String> bizInfos;

    public Map<String, WxpayProperties> getWxpayMerchants() {
        return wxpayMerchants;
    }

    public void setWxpayMerchants(Map<String, WxpayProperties> wxpayMerchants) {
        this.wxpayMerchants = wxpayMerchants;
    }

    public Map<String, AlipayProperties> getAlipayMerchants() {
        return alipayMerchants;
    }

    public void setAlipayMerchants(Map<String, AlipayProperties> alipayMerchants) {
        this.alipayMerchants = alipayMerchants;
    }

    public Map<String, String> getBizMerchants() {
        return bizMerchants;
    }

    public void setBizMerchants(Map<String, String> bizMerchants) {
        this.bizMerchants = bizMerchants;
    }

    public Map<String, String> getBizInfos() {
        return bizInfos;
    }

    public void setBizInfos(Map<String, String> bizInfos) {
        this.bizInfos = bizInfos;
    }
}
