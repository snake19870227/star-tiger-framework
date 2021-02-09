package com.snake19870227.stiger.aliyun.sms;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author BuHuaYang
 * 2021/2/8
 */
@ConfigurationProperties(prefix = "stiger.aliyun.sms")
public class StarTigerAliyunSmsProperties {

    private String accesskeyId;
    private String accesskeySecret;
    private String signName;

    public String getAccesskeyId() {
        return accesskeyId;
    }

    public void setAccesskeyId(String accesskeyId) {
        this.accesskeyId = accesskeyId;
    }

    public String getAccesskeySecret() {
        return accesskeySecret;
    }

    public void setAccesskeySecret(String accesskeySecret) {
        this.accesskeySecret = accesskeySecret;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }
}
