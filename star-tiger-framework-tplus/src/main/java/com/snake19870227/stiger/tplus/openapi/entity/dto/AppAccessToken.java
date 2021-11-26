package com.snake19870227.stiger.tplus.openapi.entity.dto;

/**
 * @author BuHuaYang
 * 8/6 006
 */
public class AppAccessToken {

    private String appAccessToken;

    private Long expireTime;

    public String getAppAccessToken() {
        return appAccessToken;
    }

    public void setAppAccessToken(String appAccessToken) {
        this.appAccessToken = appAccessToken;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
