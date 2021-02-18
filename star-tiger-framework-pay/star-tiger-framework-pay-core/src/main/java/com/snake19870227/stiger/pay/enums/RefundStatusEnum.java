package com.snake19870227.stiger.pay.enums;

import com.snake19870227.stiger.core.StarTigerEnum;

/**
 * @author BuHuaYang
 * 2020/9/25
 */
public enum RefundStatusEnum implements StarTigerEnum {

    // 成功
    Success("Success", "成功"),
    // 关闭
    Complete("Complete", "完成");

    private final String id;
    private final String name;

    RefundStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
