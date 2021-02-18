package com.snake19870227.stiger.pay.util;

import com.snake19870227.stiger.pay.entity.dto.RefundInfo;
import com.snake19870227.stiger.pay.entity.dto.TradeInfo;
import com.snake19870227.stiger.pay.entity.po.PayRefund;
import com.snake19870227.stiger.pay.entity.po.PayTrade;

/**
 * @author BuHuaYang
 * 2020/9/27
 */
public class PayDtoUtil {

    public static <T> TradeInfo<T> createTradeInfo(PayTrade payTrade, T clientReqParam) {
        TradeInfo<T> tradeInfo = new TradeInfo();
        tradeInfo.setBizType(payTrade.getBizType());
        tradeInfo.setBizFlow(payTrade.getBizFlow());
        tradeInfo.setStatusId(payTrade.getStatusId());
        tradeInfo.setPayChannelId(payTrade.getPayChannelId());
        tradeInfo.setOutTradeNo(payTrade.getOutTradeNo());
        tradeInfo.setTradeNo(payTrade.getTradeNo());
        tradeInfo.setTradePrice(payTrade.getTradePrice().toString());
        tradeInfo.setTradeCreateTime(payTrade.getTradeCreateTime());
        tradeInfo.setTradeSuccessTime(payTrade.getTradeSuccessTime());
        tradeInfo.setTradeFlow(payTrade.getTradeFlow());
        tradeInfo.setClientReqParam(clientReqParam);
        return tradeInfo;
    }

    public static TradeInfo<?> createTradeInfo(PayTrade payTrade) {
        return createTradeInfo(payTrade, null);
    }

    public static RefundInfo createRefundInfo(PayRefund payRefund) {
        RefundInfo refundInfo = new RefundInfo();
        refundInfo.setRefundFlow(payRefund.getRefundFlow());
        refundInfo.setTradeFlow(payRefund.getTradeFlow());
        refundInfo.setBizFlow(payRefund.getBizFlow());
        refundInfo.setStatusId(payRefund.getStatusId());
        refundInfo.setStatusName(payRefund.getStatusName());
        refundInfo.setPayChannelId(payRefund.getPayChannelId());
        refundInfo.setPayChannelName(payRefund.getPayChannelName());
        refundInfo.setOutTradeNo(payRefund.getOutTradeNo());
        refundInfo.setOutRefundNo(payRefund.getOutRefundNo());
        refundInfo.setTradePrice(payRefund.getTradePrice());
        refundInfo.setRefundPrice(payRefund.getRefundPrice());
        refundInfo.setRefundCreateTime(payRefund.getRefundCreateTime());
        return refundInfo;
    }
}
