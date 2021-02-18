package com.snake19870227.stiger.pay.channel;

import java.math.BigDecimal;
import java.util.Map;

import com.snake19870227.stiger.pay.entity.dto.RefundInfo;
import com.snake19870227.stiger.pay.entity.dto.TradeInfo;
import com.snake19870227.stiger.pay.entity.po.PayTrade;
import com.snake19870227.stiger.pay.enums.PaymentMethodEnum;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/27
 */
public interface PayChannelHandler<T> {

    /**
     * 创建订单
     * @param bizType 业务类型
     * @param bizFlow 业务记录流水号
     * @param paymentMethodEnum 支付方式枚举值
     * @param tradePrice 支付金额（单位：元）
     * @param metadataMap 扩展信息
     * @return 订单记录对象
     */
    TradeInfo<?> create(String bizType, String bizFlow, PaymentMethodEnum paymentMethodEnum, BigDecimal tradePrice, Map<String, Object> metadataMap);

    /**
     * 查询订单
     * @param payTrade 订单对象 {@link PayTrade}
     */
    void query(PayTrade payTrade);

    /**
     * 关闭订单
     * @param payTrade 订单对象 {@link PayTrade}
     * @return 是否成功
     */
    boolean close(PayTrade payTrade);

    /**
     * 处理异步通知
     * @param payWay 支付途径
     * @param notifyData 通知请求体
     */
    void doNotify(String payWay, String notifyData);

    /**
     * 退款
     * @param payTrade 支付订单对象 {@link PayTrade}
     * @param refundPrice 退款金额（单位：元）
     * @return 退款信息 {@link RefundInfo}
     */
    RefundInfo refund(PayTrade payTrade, BigDecimal refundPrice);

    /**
     * 退款查询
     * @param payTrade 支付订单对象 {@link PayTrade}
     * @return 退款信息 {@link RefundInfo}
     */
    RefundInfo queryRefund(PayTrade payTrade);

    /**
     * 处理异步退款通知
     * @param payWay 支付途径
     * @param notifyData 通知请求体
     */
    void doRefundNotify(String payWay, String notifyData);
}
