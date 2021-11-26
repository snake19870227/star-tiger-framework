package com.snake19870227.stiger.tplus.openapi.entity.dto;

public class OrgAccessToken {

    private String orgAccessToken;

    private String orgId;

    private Long expireTime;

    public String getOrgAccessToken() {
        return orgAccessToken;
    }

    public void setOrgAccessToken(String orgAccessToken) {
        this.orgAccessToken = orgAccessToken;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
