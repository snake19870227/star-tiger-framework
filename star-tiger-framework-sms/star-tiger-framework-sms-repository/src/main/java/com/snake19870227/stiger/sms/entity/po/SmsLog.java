package com.snake19870227.stiger.sms.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author buhuayang
 * @since 2021-02-07
 */
@ApiModel(value="SmsLog对象", description="")
public class SmsLog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "发送渠道")
    private String channel;

    @ApiModelProperty(value = "签名")
    private String signName;

    @ApiModelProperty(value = "短信模板id")
    private String templateId;

    @ApiModelProperty(value = "短信模板code")
    private String templateCode;

    @ApiModelProperty(value = "短信模板name")
    private String templateName;

    @ApiModelProperty(value = "发送参数")
    private String sendParam;

    @ApiModelProperty(value = "状态(0:未发送;1:已发送;2:发送失败;3:已接收;4:接收失败)")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private String createDatetime;

    @ApiModelProperty(value = "发送时间(yyyyMMddHHmmss)")
    private String sendDatetime;

    @ApiModelProperty(value = "短信平台发送id")
    private String sendId;

    @ApiModelProperty(value = "短信发送状态编码")
    private String sendCode;

    @ApiModelProperty(value = "短信发送状态说明")
    private String sendMsg;

    @ApiModelProperty(value = "短信发送回执时间(yyyyMMddHHmmss)")
    private String reportDatetime;

    @ApiModelProperty(value = "是否接受成功(0:否;1:是)")
    private Integer reportSuccess;

    @ApiModelProperty(value = "短信发送结果状态编码")
    private String reportCode;

    @ApiModelProperty(value = "短信发送结果状态说明")
    private String reportMsg;

    @ApiModelProperty(value = "短信个数")
    private Integer smsSize;

    @ApiModelProperty(value = "重试次数")
    private Integer retryCount;


    public Integer getId() {
        return id;
    }

    public SmsLog setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public SmsLog setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    public SmsLog setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public String getSignName() {
        return signName;
    }

    public SmsLog setSignName(String signName) {
        this.signName = signName;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public SmsLog setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public SmsLog setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
        return this;
    }

    public String getTemplateName() {
        return templateName;
    }

    public SmsLog setTemplateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public String getSendParam() {
        return sendParam;
    }

    public SmsLog setSendParam(String sendParam) {
        this.sendParam = sendParam;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public SmsLog setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public SmsLog setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
        return this;
    }

    public String getSendDatetime() {
        return sendDatetime;
    }

    public SmsLog setSendDatetime(String sendDatetime) {
        this.sendDatetime = sendDatetime;
        return this;
    }

    public String getSendId() {
        return sendId;
    }

    public SmsLog setSendId(String sendId) {
        this.sendId = sendId;
        return this;
    }

    public String getSendCode() {
        return sendCode;
    }

    public SmsLog setSendCode(String sendCode) {
        this.sendCode = sendCode;
        return this;
    }

    public String getSendMsg() {
        return sendMsg;
    }

    public SmsLog setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
        return this;
    }

    public String getReportDatetime() {
        return reportDatetime;
    }

    public SmsLog setReportDatetime(String reportDatetime) {
        this.reportDatetime = reportDatetime;
        return this;
    }

    public Integer getReportSuccess() {
        return reportSuccess;
    }

    public SmsLog setReportSuccess(Integer reportSuccess) {
        this.reportSuccess = reportSuccess;
        return this;
    }

    public String getReportCode() {
        return reportCode;
    }

    public SmsLog setReportCode(String reportCode) {
        this.reportCode = reportCode;
        return this;
    }

    public String getReportMsg() {
        return reportMsg;
    }

    public SmsLog setReportMsg(String reportMsg) {
        this.reportMsg = reportMsg;
        return this;
    }

    public Integer getSmsSize() {
        return smsSize;
    }

    public SmsLog setSmsSize(Integer smsSize) {
        this.smsSize = smsSize;
        return this;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public SmsLog setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    @Override
    public String toString() {
        return "SmsLog{" +
        "id=" + id +
        ", phone=" + phone +
        ", channel=" + channel +
        ", signName=" + signName +
        ", templateId=" + templateId +
        ", templateCode=" + templateCode +
        ", templateName=" + templateName +
        ", sendParam=" + sendParam +
        ", status=" + status +
        ", createDatetime=" + createDatetime +
        ", sendDatetime=" + sendDatetime +
        ", sendId=" + sendId +
        ", sendCode=" + sendCode +
        ", sendMsg=" + sendMsg +
        ", reportDatetime=" + reportDatetime +
        ", reportSuccess=" + reportSuccess +
        ", reportCode=" + reportCode +
        ", reportMsg=" + reportMsg +
        ", smsSize=" + smsSize +
        ", retryCount=" + retryCount +
        "}";
    }
}
