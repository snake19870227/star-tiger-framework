package com.snake19870227.stiger.tplus.message;

import com.snake19870227.stiger.core.StarTigerEnum;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2021/05/06
 */
public enum ChanjetMsgType implements StarTigerEnum {

    APP_TEST("APP_TEST", "验证消息"),
    APP_TICKET("APP_TICKET", "appTicket消息"),
    TEMP_AUTH_CODE("TEMP_AUTH_CODE", "企业临时授权码消息"),
    PAY_ORDER_SUCCESS("PAY_ORDER_SUCCESS", "订单支付成功消息");

    private final String id;

    private final String name;

    ChanjetMsgType(String id, String name) {
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
