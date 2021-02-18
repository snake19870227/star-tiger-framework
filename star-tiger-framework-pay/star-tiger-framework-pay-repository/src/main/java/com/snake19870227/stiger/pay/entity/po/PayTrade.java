package com.snake19870227.stiger.pay.entity.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 支付订单
 * </p>
 *
 * @author buhuayang
 * @since 2021-02-10
 */
@ApiModel(value="PayTrade对象", description="支付订单")
public class PayTrade implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订单流水号")
    @TableId(value = "trade_flow", type = IdType.ASSIGN_UUID)
    private String tradeFlow;

    @ApiModelProperty(value = "业务类型")
    private String bizType;

    @ApiModelProperty(value = "业务记录流水号")
    private String bizFlow;

    @ApiModelProperty(value = "订单状态ID")
    private String statusId;

    @ApiModelProperty(value = "订单状态名称")
    private String statusName;

    @ApiModelProperty(value = "支付渠道类型ID")
    private String payChannelId;

    @ApiModelProperty(value = "支付渠道类型名称")
    private String payChannelName;

    @ApiModelProperty(value = "支付渠道应用标识")
    private String payAppId;

    @ApiModelProperty(value = "本地商户订单号")
    private String outTradeNo;

    @ApiModelProperty(value = "订单支付类型")
    private String paymentMethod;

    @ApiModelProperty(value = "支付渠道订单号")
    private String tradeNo;

    @ApiModelProperty(value = "购买用户支付渠道账户")
    private String tradeBuyerAccount;

    @ApiModelProperty(value = "购买用户支付渠道内ID")
    private String tradeBuyerId;

    @ApiModelProperty(value = "订单金额(单位:元)")
    private BigDecimal tradePrice;

    @ApiModelProperty(value = "订单创建时间")
    private String tradeCreateTime;

    @ApiModelProperty(value = "订单失效时间")
    private String tradeExpireTime;

    @ApiModelProperty(value = "订单支付成功时间")
    private String tradeSuccessTime;

    @ApiModelProperty(value = "渠道订单状态")
    private String tradeStatus;

    @ApiModelProperty(value = "渠道订单状态描述")
    private String tradeStatusDes;

    @ApiModelProperty(value = "是退款")
    private String isRefund;

    @ApiModelProperty(value = "支付渠道创建订单请求内容")
    private String reqContent;

    @ApiModelProperty(value = "支付渠道创建订单响应内容")
    private String respContent;

    @ApiModelProperty(value = "记录状态")
    @TableLogic
    private String recordStatus;


    public String getTradeFlow() {
        return tradeFlow;
    }

    public PayTrade setTradeFlow(String tradeFlow) {
        this.tradeFlow = tradeFlow;
        return this;
    }

    public String getBizType() {
        return bizType;
    }

    public PayTrade setBizType(String bizType) {
        this.bizType = bizType;
        return this;
    }

    public String getBizFlow() {
        return bizFlow;
    }

    public PayTrade setBizFlow(String bizFlow) {
        this.bizFlow = bizFlow;
        return this;
    }

    public String getStatusId() {
        return statusId;
    }

    public PayTrade setStatusId(String statusId) {
        this.statusId = statusId;
        return this;
    }

    public String getStatusName() {
        return statusName;
    }

    public PayTrade setStatusName(String statusName) {
        this.statusName = statusName;
        return this;
    }

    public String getPayChannelId() {
        return payChannelId;
    }

    public PayTrade setPayChannelId(String payChannelId) {
        this.payChannelId = payChannelId;
        return this;
    }

    public String getPayChannelName() {
        return payChannelName;
    }

    public PayTrade setPayChannelName(String payChannelName) {
        this.payChannelName = payChannelName;
        return this;
    }

    public String getPayAppId() {
        return payAppId;
    }

    public PayTrade setPayAppId(String payAppId) {
        this.payAppId = payAppId;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public PayTrade setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public PayTrade setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public PayTrade setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
        return this;
    }

    public String getTradeBuyerAccount() {
        return tradeBuyerAccount;
    }

    public PayTrade setTradeBuyerAccount(String tradeBuyerAccount) {
        this.tradeBuyerAccount = tradeBuyerAccount;
        return this;
    }

    public String getTradeBuyerId() {
        return tradeBuyerId;
    }

    public PayTrade setTradeBuyerId(String tradeBuyerId) {
        this.tradeBuyerId = tradeBuyerId;
        return this;
    }

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public PayTrade setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
        return this;
    }

    public String getTradeCreateTime() {
        return tradeCreateTime;
    }

    public PayTrade setTradeCreateTime(String tradeCreateTime) {
        this.tradeCreateTime = tradeCreateTime;
        return this;
    }

    public String getTradeExpireTime() {
        return tradeExpireTime;
    }

    public PayTrade setTradeExpireTime(String tradeExpireTime) {
        this.tradeExpireTime = tradeExpireTime;
        return this;
    }

    public String getTradeSuccessTime() {
        return tradeSuccessTime;
    }

    public PayTrade setTradeSuccessTime(String tradeSuccessTime) {
        this.tradeSuccessTime = tradeSuccessTime;
        return this;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public PayTrade setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
        return this;
    }

    public String getTradeStatusDes() {
        return tradeStatusDes;
    }

    public PayTrade setTradeStatusDes(String tradeStatusDes) {
        this.tradeStatusDes = tradeStatusDes;
        return this;
    }

    public String getIsRefund() {
        return isRefund;
    }

    public PayTrade setIsRefund(String isRefund) {
        this.isRefund = isRefund;
        return this;
    }

    public String getReqContent() {
        return reqContent;
    }

    public PayTrade setReqContent(String reqContent) {
        this.reqContent = reqContent;
        return this;
    }

    public String getRespContent() {
        return respContent;
    }

    public PayTrade setRespContent(String respContent) {
        this.respContent = respContent;
        return this;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public PayTrade setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    @Override
    public String toString() {
        return "PayTrade{" +
        "tradeFlow=" + tradeFlow +
        ", bizType=" + bizType +
        ", bizFlow=" + bizFlow +
        ", statusId=" + statusId +
        ", statusName=" + statusName +
        ", payChannelId=" + payChannelId +
        ", payChannelName=" + payChannelName +
        ", payAppId=" + payAppId +
        ", outTradeNo=" + outTradeNo +
        ", paymentMethod=" + paymentMethod +
        ", tradeNo=" + tradeNo +
        ", tradeBuyerAccount=" + tradeBuyerAccount +
        ", tradeBuyerId=" + tradeBuyerId +
        ", tradePrice=" + tradePrice +
        ", tradeCreateTime=" + tradeCreateTime +
        ", tradeExpireTime=" + tradeExpireTime +
        ", tradeSuccessTime=" + tradeSuccessTime +
        ", tradeStatus=" + tradeStatus +
        ", tradeStatusDes=" + tradeStatusDes +
        ", isRefund=" + isRefund +
        ", reqContent=" + reqContent +
        ", respContent=" + respContent +
        ", recordStatus=" + recordStatus +
        "}";
    }
}
