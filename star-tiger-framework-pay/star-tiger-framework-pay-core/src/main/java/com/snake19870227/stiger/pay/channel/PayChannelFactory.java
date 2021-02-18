package com.snake19870227.stiger.pay.channel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.pay.channel.alipay.AlipayChannelHandler;
import com.snake19870227.stiger.pay.channel.wxpay.WxpayChannelHandler;
import com.snake19870227.stiger.pay.enums.PayChannelEnum;
import com.snake19870227.stiger.pay.exception.PayChannelBeanNotFoundException;
import com.snake19870227.stiger.pay.exception.PayChannelNotFoundException;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/27
 */
public class PayChannelFactory {

    private static final Map<String, Class<? extends PayChannelHandler>> PAY_CHANNEL_HANDLER_MAP;

    static {
        PAY_CHANNEL_HANDLER_MAP = new HashMap<>(1);
        PAY_CHANNEL_HANDLER_MAP.put(PayChannelEnum.Alipay.getId(), AlipayChannelHandler.class);
        PAY_CHANNEL_HANDLER_MAP.put(PayChannelEnum.Wxpay.getId(), WxpayChannelHandler.class);
    }

    public static PayChannelHandler createPayChannelHandler(PayChannelEnum payChannelEnum) {
        Class<? extends PayChannelHandler> handlerClass = PAY_CHANNEL_HANDLER_MAP.get(payChannelEnum.getId());
        if (handlerClass == null) {
            throw new PayChannelNotFoundException(payChannelEnum);
        }
        PayChannelHandler payChannelHandler = null;
        try {
            payChannelHandler = StarTigerContext.getBean(handlerClass);
        } catch (BeansException e) {
            throw new PayChannelBeanNotFoundException(payChannelEnum, e);
        }
        return payChannelHandler;
    }
}
