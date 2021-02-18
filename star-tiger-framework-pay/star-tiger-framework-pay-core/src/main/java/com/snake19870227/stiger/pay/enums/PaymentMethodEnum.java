package com.snake19870227.stiger.pay.enums;

import com.snake19870227.stiger.core.StarTigerEnum;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/27
 */
public enum PaymentMethodEnum implements StarTigerEnum {

    // 支付宝APP支付
    AlipayTradeAppPay("alipay.trade.app.pay", "支付宝APP支付"),
    // 支付宝PC网页支付
    AlipayTradePagePay("alipay.trade.page.pay", "支付宝PC网页支付"),
    // 支付宝移动网页支付
    AlipayTradeWapPay("alipay.trade.wap.pay", "支付宝移动网页支付"),
    // 微信原生扫码支付
    WxNative("NATIVE", "微信原生扫码支付"),
    // 微信App支付
    WxApp("APP", "微信App支付"),
    // 微信公众号支付/小程序支付
    WxJsapi("JSAPI", "微信公众号支付/小程序支付"),
    // 微信H5支付
    WxMweb("MWEB", "微信H5支付");

    private final String id;
    private final String name;

    PaymentMethodEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
