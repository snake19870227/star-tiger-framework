package com.snake19870227.stiger.pay.entity.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/27
 */
public class RefundTradeReq {

    @ApiModelProperty("商户订单号")
    private String outTradeNo;

    @ApiModelProperty("退款业务数据流水号(可选)")
    private String bizFlow;

    @ApiModelProperty("退款金额(单位:元)")
    private BigDecimal refundPrice;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getBizFlow() {
        return bizFlow;
    }

    public void setBizFlow(String bizFlow) {
        this.bizFlow = bizFlow;
    }

    public BigDecimal getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(BigDecimal refundPrice) {
        this.refundPrice = refundPrice;
    }
}
