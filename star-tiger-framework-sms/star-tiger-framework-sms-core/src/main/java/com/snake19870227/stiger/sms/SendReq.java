package com.snake19870227.stiger.sms;

import java.util.Map;

/**
 * @author BuHuaYang
 * 2021/1/5
 */
public class SendReq {

    private String phoneNumber;

    private String signName;

    private String templateCode;

    private Map<String, Object> params;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
