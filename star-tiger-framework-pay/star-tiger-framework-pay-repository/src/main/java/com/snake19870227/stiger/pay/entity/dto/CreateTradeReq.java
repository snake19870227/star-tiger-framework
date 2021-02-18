package com.snake19870227.stiger.pay.entity.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/26
 */
public class CreateTradeReq {

    @ApiModelProperty("业务类型[BuySms:购买短信;BuyVip:购买会员;BuyBeans:购买苗豆]")
    private String bizType;
    @ApiModelProperty("业务数据流水号(可选)")
    private String bizFlow;
    @ApiModelProperty("支付渠道[Alipay:支付宝;WxPay:微信]")
    private String payChannelId;
    @ApiModelProperty("支付方式(具体参考渠道文档)")
    private String paymentMethod;
    @ApiModelProperty("支付金额(单位:元;保留小数点后两位)")
    private BigDecimal tradePrice;

    @ApiModelProperty("扩展信息map")
    private Map<String, Object> metadataMap;

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

    public String getPayChannelId() {
        return payChannelId;
    }

    public void setPayChannelId(String payChannelId) {
        this.payChannelId = payChannelId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Map<String, Object> getMetadataMap() {
        return metadataMap;
    }

    public void setMetadataMap(Map<String, Object> metadataMap) {
        this.metadataMap = metadataMap;
    }
}
