package com.snake19870227.stiger.pay.channel.alipay;

import com.snake19870227.stiger.pay.channel.ChannelClientParam;

/**
 * @author BuHuaYang
 * 2020/9/28
 */
public class AlipayAppClientParam implements ChannelClientParam {

    private String paramStr;

    public String getParamStr() {
        return paramStr;
    }

    public void setParamStr(String paramStr) {
        this.paramStr = paramStr;
    }
}
