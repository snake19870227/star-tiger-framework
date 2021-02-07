package com.snake19870227.stiger.sms;

import cn.hutool.json.JSONObject;

/**
 * @author BuHuaYang
 * 2021/1/5
 */
@FunctionalInterface
public interface SmsRespnoseHandler<T> {

    T handler(JSONObject r);
}
