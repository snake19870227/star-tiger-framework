package com.snake19870227.stiger.pay.channel;

import com.snake19870227.stiger.pay.entity.po.PayNotify;
import com.snake19870227.stiger.pay.entity.po.PayRefund;
import com.snake19870227.stiger.pay.entity.po.PayTrade;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/28
 */
public interface IPayStorage {

    /**
     * 创建商户订单号
     * @return 未被使用的商户订单号
     */
    String createOutTradeNo();

    String createOutRefundNo();

    PayTrade getTradeByOutTradeNo(String outTradeNo);

    void saveTrade(PayTrade trade);

    void updateTrade(PayTrade trade);

    void saveRefund(PayRefund refund);

    void saveNotify(PayNotify notify);
}
