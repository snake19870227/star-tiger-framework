package com.snake19870227.stiger.pay.entity.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/26
 */
public class TradeInfo<T> {

    @ApiModelProperty("业务类型[BuySms:购买短信;BuyVip:购买会员;BuyBeans:购买苗豆]")
    private String bizType;
    @ApiModelProperty("业务数据流水号(可选)")
    private String bizFlow;
    @ApiModelProperty("订单状态[Create:创建;Success:成功;Closed:关闭]")
    private String statusId;
    @ApiModelProperty("支付渠道[Alipay:支付宝;WxPay:微信]")
    private String payChannelId;
    @ApiModelProperty("商户订单号")
    private String outTradeNo;
    @ApiModelProperty("支付渠道交易号")
    private String tradeNo;
    @ApiModelProperty("支付金额(单位:元;保留小数点后两位)")
    private String tradePrice;
    @ApiModelProperty("订单创建时间(格式:yyyyMMddHHmmss)")
    private String tradeCreateTime;
    @ApiModelProperty("订单成功时间(格式:yyyyMMddHHmmss)")
    private String tradeSuccessTime;
    @ApiModelProperty("订单流水号")
    private String tradeFlow;

    @ApiModelProperty("客户端请求支付SDK参数(具体参考渠道文档)")
    private T clientReqParam;

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
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

    public String getPayChannelId() {
        return payChannelId;
    }

    public void setPayChannelId(String payChannelId) {
        this.payChannelId = payChannelId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(String tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getTradeCreateTime() {
        return tradeCreateTime;
    }

    public void setTradeCreateTime(String tradeCreateTime) {
        this.tradeCreateTime = tradeCreateTime;
    }

    public String getTradeSuccessTime() {
        return tradeSuccessTime;
    }

    public void setTradeSuccessTime(String tradeSuccessTime) {
        this.tradeSuccessTime = tradeSuccessTime;
    }

    public String getTradeFlow() {
        return tradeFlow;
    }

    public void setTradeFlow(String tradeFlow) {
        this.tradeFlow = tradeFlow;
    }

    public T getClientReqParam() {
        return clientReqParam;
    }

    public void setClientReqParam(T clientReqParam) {
        this.clientReqParam = clientReqParam;
    }
}
