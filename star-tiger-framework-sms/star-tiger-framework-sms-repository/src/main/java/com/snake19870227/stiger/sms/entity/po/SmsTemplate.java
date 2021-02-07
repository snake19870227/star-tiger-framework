package com.snake19870227.stiger.sms.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 短信模板表
 * </p>
 *
 * @author buhuayang
 * @since 2021-02-07
 */
@ApiModel(value="SmsTemplate对象", description="短信模板表")
public class SmsTemplate implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "发送渠道")
    private String channel;

    @ApiModelProperty(value = "短信模板code")
    private String templateCode;

    @ApiModelProperty(value = "短信模板创建时间")
    private String commitDatetime;

    @ApiModelProperty(value = "短信模板内容")
    private String templateContent;

    @ApiModelProperty(value = "短信模板name")
    private String templateName;

    @ApiModelProperty(value = "模板状态")
    private String templateStatus;

    @ApiModelProperty(value = "模板类型")
    private String templateType;

    @ApiModelProperty(value = "备注")
    private String remark;


    public String getId() {
        return id;
    }

    public SmsTemplate setId(String id) {
        this.id = id;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    public SmsTemplate setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public SmsTemplate setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
        return this;
    }

    public String getCommitDatetime() {
        return commitDatetime;
    }

    public SmsTemplate setCommitDatetime(String commitDatetime) {
        this.commitDatetime = commitDatetime;
        return this;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public SmsTemplate setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
        return this;
    }

    public String getTemplateName() {
        return templateName;
    }

    public SmsTemplate setTemplateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public String getTemplateStatus() {
        return templateStatus;
    }

    public SmsTemplate setTemplateStatus(String templateStatus) {
        this.templateStatus = templateStatus;
        return this;
    }

    public String getTemplateType() {
        return templateType;
    }

    public SmsTemplate setTemplateType(String templateType) {
        this.templateType = templateType;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public SmsTemplate setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    @Override
    public String toString() {
        return "SmsTemplate{" +
        "id=" + id +
        ", channel=" + channel +
        ", templateCode=" + templateCode +
        ", commitDatetime=" + commitDatetime +
        ", templateContent=" + templateContent +
        ", templateName=" + templateName +
        ", templateStatus=" + templateStatus +
        ", templateType=" + templateType +
        ", remark=" + remark +
        "}";
    }
}
