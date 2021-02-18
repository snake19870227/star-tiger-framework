package com.snake19870227.stiger.pay.exception;

import com.snake19870227.stiger.core.exception.BusinessException;
import com.snake19870227.stiger.pay.enums.PayChannelEnum;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/27
 */
public class PayChannelNotFoundException extends BusinessException {

    public PayChannelNotFoundException(PayChannelEnum payChannelEnum) {
        super("未找到支付渠道处理类.[" + payChannelEnum.getId() + " - " + payChannelEnum.getName() + "]");
    }
}
