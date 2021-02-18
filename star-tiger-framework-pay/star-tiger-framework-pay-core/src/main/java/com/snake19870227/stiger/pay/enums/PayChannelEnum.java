package com.snake19870227.stiger.pay.enums;

import com.snake19870227.stiger.core.StarTigerEnum;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/26
 */
public enum PayChannelEnum implements StarTigerEnum {

    // 支付宝
    Alipay("Alipay", "支付宝"),
    // 微信
    Wxpay("Wxpay", "微信")
    ;

    private final String id;
    private final String name;

    PayChannelEnum(String id, String name) {
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
