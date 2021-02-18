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
 * 退款申请
 * </p>
 *
 * @author buhuayang
 * @since 2021-02-10
 */
@ApiModel(value="PayRefund对象", description="退款申请")
public class PayRefund implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "退款申请流水号")
    @TableId(value = "refund_flow", type = IdType.ASSIGN_UUID)
    private String refundFlow;

    @ApiModelProperty(value = "订单流水号")
    private String tradeFlow;

    @ApiModelProperty(value = "业务类型")
    private String bizType;

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

    @ApiModelProperty(value = "支付渠道应用标识")
    private String payAppId;

    @ApiModelProperty(value = "本地商户订单号")
    private String outTradeNo;

    @ApiModelProperty(value = "订单支付类型")
    private String paymentMethod;

    @ApiModelProperty(value = "本地商户退款单号")
    private String outRefundNo;

    @ApiModelProperty(value = "支付渠道退款单号")
    private String refundNo;

    @ApiModelProperty(value = "订单金额(单位:元)")
    private BigDecimal tradePrice;

    @ApiModelProperty(value = "退款金额(单位:元)")
    private BigDecimal refundPrice;

    @ApiModelProperty(value = "退款申请创建时间")
    private String refundCreateTime;

    @ApiModelProperty(value = "记录状态")
    @TableLogic
    private String recordStatus;


    public String getRefundFlow() {
        return refundFlow;
    }

    public PayRefund setRefundFlow(String refundFlow) {
        this.refundFlow = refundFlow;
        return this;
    }

    public String getTradeFlow() {
        return tradeFlow;
    }

    public PayRefund setTradeFlow(String tradeFlow) {
        this.tradeFlow = tradeFlow;
        return this;
    }

    public String getBizType() {
        return bizType;
    }

    public PayRefund setBizType(String bizType) {
        this.bizType = bizType;
        return this;
    }

    public String getBizFlow() {
        return bizFlow;
    }

    public PayRefund setBizFlow(String bizFlow) {
        this.bizFlow = bizFlow;
        return this;
    }

    public String getStatusId() {
        return statusId;
    }

    public PayRefund setStatusId(String statusId) {
        this.statusId = statusId;
        return this;
    }

    public String getStatusName() {
        return statusName;
    }

    public PayRefund setStatusName(String statusName) {
        this.statusName = statusName;
        return this;
    }

    public String getPayChannelId() {
        return payChannelId;
    }

    public PayRefund setPayChannelId(String payChannelId) {
        this.payChannelId = payChannelId;
        return this;
    }

    public String getPayChannelName() {
        return payChannelName;
    }

    public PayRefund setPayChannelName(String payChannelName) {
        this.payChannelName = payChannelName;
        return this;
    }

    public String getPayAppId() {
        return payAppId;
    }

    public PayRefund setPayAppId(String payAppId) {
        this.payAppId = payAppId;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public PayRefund setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public PayRefund setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public PayRefund setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
        return this;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public PayRefund setRefundNo(String refundNo) {
        this.refundNo = refundNo;
        return this;
    }

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public PayRefund setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
        return this;
    }

    public BigDecimal getRefundPrice() {
        return refundPrice;
    }

    public PayRefund setRefundPrice(BigDecimal refundPrice) {
        this.refundPrice = refundPrice;
        return this;
    }

    public String getRefundCreateTime() {
        return refundCreateTime;
    }

    public PayRefund setRefundCreateTime(String refundCreateTime) {
        this.refundCreateTime = refundCreateTime;
        return this;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public PayRefund setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    @Override
    public String toString() {
        return "PayRefund{" +
        "refundFlow=" + refundFlow +
        ", tradeFlow=" + tradeFlow +
        ", bizType=" + bizType +
        ", bizFlow=" + bizFlow +
        ", statusId=" + statusId +
        ", statusName=" + statusName +
        ", payChannelId=" + payChannelId +
        ", payChannelName=" + payChannelName +
        ", payAppId=" + payAppId +
        ", outTradeNo=" + outTradeNo +
        ", paymentMethod=" + paymentMethod +
        ", outRefundNo=" + outRefundNo +
        ", refundNo=" + refundNo +
        ", tradePrice=" + tradePrice +
        ", refundPrice=" + refundPrice +
        ", refundCreateTime=" + refundCreateTime +
        ", recordStatus=" + recordStatus +
        "}";
    }
}
