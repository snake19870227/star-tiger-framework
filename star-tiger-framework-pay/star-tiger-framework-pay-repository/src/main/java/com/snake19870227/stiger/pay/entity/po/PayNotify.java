package com.snake19870227.stiger.pay.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 异步通知
 * </p>
 *
 * @author buhuayang
 * @since 2021-02-10
 */
@ApiModel(value="PayNotify对象", description="异步通知")
public class PayNotify implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "通知流水号")
    @TableId(value = "notify_flow", type = IdType.ASSIGN_UUID)
    private String notifyFlow;

    @ApiModelProperty(value = "通知时间")
    private String notifyTime;

    @ApiModelProperty(value = "通知类型")
    private String notifyTypeId;

    @ApiModelProperty(value = "通知类型")
    private String notifyTypeName;

    @ApiModelProperty(value = "支付途径")
    private String payWay;

    @ApiModelProperty(value = "支付渠道类型ID")
    private String payChannelId;

    @ApiModelProperty(value = "支付渠道类型名称")
    private String payChannelName;

    @ApiModelProperty(value = "通知标识")
    private String notifyId;

    @ApiModelProperty(value = "本地商户订单号")
    private String outTradeNo;

    @ApiModelProperty(value = "支付渠道订单号")
    private String tradeNo;

    @ApiModelProperty(value = "通知内容")
    private String notifyContent;

    @ApiModelProperty(value = "记录状态")
    @TableLogic
    private String recordStatus;


    public String getNotifyFlow() {
        return notifyFlow;
    }

    public PayNotify setNotifyFlow(String notifyFlow) {
        this.notifyFlow = notifyFlow;
        return this;
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public PayNotify setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
        return this;
    }

    public String getNotifyTypeId() {
        return notifyTypeId;
    }

    public PayNotify setNotifyTypeId(String notifyTypeId) {
        this.notifyTypeId = notifyTypeId;
        return this;
    }

    public String getNotifyTypeName() {
        return notifyTypeName;
    }

    public PayNotify setNotifyTypeName(String notifyTypeName) {
        this.notifyTypeName = notifyTypeName;
        return this;
    }

    public String getPayWay() {
        return payWay;
    }

    public PayNotify setPayWay(String payWay) {
        this.payWay = payWay;
        return this;
    }

    public String getPayChannelId() {
        return payChannelId;
    }

    public PayNotify setPayChannelId(String payChannelId) {
        this.payChannelId = payChannelId;
        return this;
    }

    public String getPayChannelName() {
        return payChannelName;
    }

    public PayNotify setPayChannelName(String payChannelName) {
        this.payChannelName = payChannelName;
        return this;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public PayNotify setNotifyId(String notifyId) {
        this.notifyId = notifyId;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public PayNotify setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public PayNotify setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
        return this;
    }

    public String getNotifyContent() {
        return notifyContent;
    }

    public PayNotify setNotifyContent(String notifyContent) {
        this.notifyContent = notifyContent;
        return this;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public PayNotify setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    @Override
    public String toString() {
        return "PayNotify{" +
        "notifyFlow=" + notifyFlow +
        ", notifyTime=" + notifyTime +
        ", notifyTypeId=" + notifyTypeId +
        ", notifyTypeName=" + notifyTypeName +
        ", payWay=" + payWay +
        ", payChannelId=" + payChannelId +
        ", payChannelName=" + payChannelName +
        ", notifyId=" + notifyId +
        ", outTradeNo=" + outTradeNo +
        ", tradeNo=" + tradeNo +
        ", notifyContent=" + notifyContent +
        ", recordStatus=" + recordStatus +
        "}";
    }
}
