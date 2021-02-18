package com.snake19870227.stiger.pay.entity.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author BuHuaYang
 * 2020/9/25
 */
public class RefundInfo {

    @ApiModelProperty(value = "退款申请流水号")
    private String refundFlow;

    @ApiModelProperty(value = "订单流水号")
    private String tradeFlow;

    @ApiModelProperty(value = "业务记录流水号")
    private String bizFlow;

    @ApiModelProperty(value = "退款状态ID")
    private String statusId;

    @ApiModelProperty(value = "退款状态名称")
    private String statusName;

    @ApiModelProperty(value = "支付渠道类型ID")
    private String payChannelId;

    @ApiModelProperty(value = "支付渠道类型名称")
    private String payChannelName;

    @ApiModelProperty(value = "本地商户订单号")
    private String outTradeNo;

    @ApiModelProperty(value = "本地商户退款单号")
    private String outRefundNo;

    @ApiModelProperty(value = "订单金额(单位:元)")
    private BigDecimal tradePrice;

    @ApiModelProperty(value = "退款金额(单位:元)")
    private BigDecimal refundPrice;

    @ApiModelProperty(value = "退款申请创建时间")
    private String refundCreateTime;

    public String getRefundFlow() {
        return refundFlow;
    }

    public void setRefundFlow(String refundFlow) {
        this.refundFlow = refundFlow;
    }

    public String getTradeFlow() {
        return tradeFlow;
    }

    public void setTradeFlow(String tradeFlow) {
        this.tradeFlow = tradeFlow;
    }

    public String getBizFlow() {
        return bizFlow;
    }

    public void setBizFlow(String bizFlow) {
        this.bizFlow = bizFlow;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPayChannelId() {
        return payChannelId;
    }

    public void setPayChannelId(String payChannelId) {
        this.payChannelId = payChannelId;
    }

    public String getPayChannelName() {
        return payChannelName;
    }

    public void setPayChannelName(String payChannelName) {
        this.payChannelName = payChannelName;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }

    public BigDecimal getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(BigDecimal refundPrice) {
        this.refundPrice = refundPrice;
    }

    public String getRefundCreateTime() {
        return refundCreateTime;
    }

    public void setRefundCreateTime(String refundCreateTime) {
        this.refundCreateTime = refundCreateTime;
    }
}
