package com.snake19870227.stiger.pay.enums;

import com.snake19870227.stiger.core.StarTigerEnum;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/26
 */
public enum TradeStatusEnum implements StarTigerEnum {

    // 创建
    Create("Create", "创建"),
    // 成功
    Success("Success", "成功"),
    // 关闭
    Closed("Closed", "关闭");

    private final String id;
    private final String name;

    TradeStatusEnum(String id, String name) {
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
