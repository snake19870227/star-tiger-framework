package com.snake19870227.stiger.aliyun.dypls;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author BuHuaYang
 * 2021/2/4
 */
@ConfigurationProperties(prefix = "stiger.aliyun.dypls")
public class StarTigerAliyunDyplsProperties {

    private String accesskeyId;
    private String accesskeySecret;
    private String poolKey;

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

    public String getPoolKey() {
        return poolKey;
    }

    public void setPoolKey(String poolKey) {
        this.poolKey = poolKey;
    }
}
