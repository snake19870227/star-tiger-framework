package com.snake19870227.stiger.aliyun.sms;

import cn.hutool.json.JSONObject;

/**
 * @author BuHuaYang
 * 2021/1/5
 */
public class AliyunSmsTemplate {

    private Integer templateType;
    private String templateContent;
    private String templateName;
    private String templateCode;
    private String createDate;
    private Integer templateStatus;
    private String reason;

    public AliyunSmsTemplate(JSONObject r) {
        this.templateType = r.getInt("TemplateType");
        this.templateContent = r.getStr("TemplateContent");
        this.templateName = r.getStr("TemplateName");
        this.templateCode = r.getStr("TemplateCode");
        this.createDate = r.getStr("CreateDate");
        this.templateStatus = r.getInt("TemplateStatus");
        this.reason = r.getStr("Reason");
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getTemplateStatus() {
        return templateStatus;
    }

    public void setTemplateStatus(Integer templateStatus) {
        this.templateStatus = templateStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
