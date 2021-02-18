package com.snake19870227.stiger.pay.exception;

import com.snake19870227.stiger.core.exception.BusinessException;
import com.snake19870227.stiger.pay.enums.PayChannelEnum;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/27
 */
public class PayChannelCreateException extends BusinessException {

    public PayChannelCreateException(PayChannelEnum payChannelEnum, Exception e) {
        super("创建支付渠道处理类失败.[" + payChannelEnum.getId() + " - " + payChannelEnum.getName() + "]", e);
    }
}
