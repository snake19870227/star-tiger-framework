package com.snake19870227.stiger.pay.channel;

import cn.hutool.core.util.StrUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.core.utils.JsonUtil;
import com.snake19870227.stiger.pay.enums.PayChannelEnum;
import com.snake19870227.stiger.pay.exception.MerchantNotFoundException;
import com.snake19870227.stiger.pay.properties.AlipayProperties;
import com.snake19870227.stiger.pay.properties.StarTigerPayProperties;
import com.snake19870227.stiger.pay.properties.WxpayProperties;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/28
 */
public abstract class BasePayChannelHandler<T> implements PayChannelHandler<T> {

    protected final Object TRADE_NO_LOCK;

    protected final Object REFUND_NO_LOCK;

    protected final StarTigerPayProperties starTigerPayProperties;

    protected final ObjectMapper objectMapper;

    protected final IPayStorage payStorage;

    protected BasePayChannelHandler(StarTigerPayProperties starTigerPayProperties, IPayStorage payStorage) {
        this.starTigerPayProperties = starTigerPayProperties;
        this.payStorage = payStorage;
        TRADE_NO_LOCK = new Object();
        REFUND_NO_LOCK = new Object();
        objectMapper = JsonUtil.buildJacksonObjectMapper();
    }

    protected String getPayWayByBizType(String bizType, PayChannelEnum payChannelEnum) {
        String payWay = starTigerPayProperties.getBizMerchants().get(bizType + payChannelEnum.getId());
        if (StrUtil.isBlank(payWay)) {
            throw new MerchantNotFoundException("bizType:" + bizType);
        }
        return payWay;
    }

    protected String getAppIdByBizType(String bizType, PayChannelEnum payChannelEnum) {
        return getAppIdByPayWay(getPayWayByBizType(bizType, payChannelEnum), payChannelEnum);
    }

    protected String getBizName(String bizType) {
        String bizName = starTigerPayProperties.getBizInfos().get(bizType);
        return StrUtil.isBlank(bizName) ? bizType : bizName;
    }

    protected <T> T getProperties(String payWay, PayChannelEnum payChannelEnum) {
        if (payChannelEnum == PayChannelEnum.Wxpay) {
            WxpayProperties wxpayProperties = starTigerPayProperties.getWxpayMerchants().get(payWay);
            if (wxpayProperties == null) {
                throw new MerchantNotFoundException("payWay:" + payWay);
            }
            return (T) wxpayProperties;
        }
        if (payChannelEnum == PayChannelEnum.Alipay) {
            AlipayProperties alipayProperties = starTigerPayProperties.getAlipayMerchants().get(payWay);
            if (alipayProperties == null) {
                throw new MerchantNotFoundException("payWay:" + payWay);
            }
            return (T) alipayProperties;
        }
        return null;
    }

    protected String getAppIdByPayWay(String payWay, PayChannelEnum payChannelEnum) {
        String appId = null;
        if (payChannelEnum == PayChannelEnum.Wxpay) {
            WxpayProperties wxpayProperties = getProperties(payWay, payChannelEnum);
            appId = wxpayProperties.getAppId();
        }
        if (payChannelEnum == PayChannelEnum.Alipay) {
            AlipayProperties alipayProperties = getProperties(payWay, payChannelEnum);
            appId = alipayProperties.getAppId();
        }
        return appId;
    }
}
