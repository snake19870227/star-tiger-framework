package com.snake19870227.stiger.pay.enums;

import com.snake19870227.stiger.core.StarTigerEnum;

/**
 * @author BuHuaYang
 * 2020/9/25
 */
public enum NotifyTypeEnum implements StarTigerEnum {

    PayNotify("PayNotify", "支付通知"),
    RefundNotify("RefundNotify", "退款通知");

    private final String id;
    private final String name;

    NotifyTypeEnum(String id, String name) {
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
