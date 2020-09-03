package com.snake19870227.stiger.sms.aliyun.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author BuHuaYang
 * 2020/9/3
 */
@ConfigurationProperties(prefix = "stiger.sms.aliyun")
public class StarTigerSmsAliyunProperties {

    public static final String SMS_REGION_ID = "cn-hangzhou";

    public static final String SMS_SEND_ENDPOINT = "dysmsapi.aliyuncs.com";

    public static final String SMS_SYS_VERSION = "2017-05-25";

    private String sendEndpoint = SMS_SEND_ENDPOINT;

    private String regionId = SMS_REGION_ID;

    private String sysVersion = SMS_SYS_VERSION;

    private String accessKeyId;

    private String accessSecret;

    public String getSendEndpoint() {
        return sendEndpoint;
    }

    public void setSendEndpoint(String sendEndpoint) {
        this.sendEndpoint = sendEndpoint;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }
}
