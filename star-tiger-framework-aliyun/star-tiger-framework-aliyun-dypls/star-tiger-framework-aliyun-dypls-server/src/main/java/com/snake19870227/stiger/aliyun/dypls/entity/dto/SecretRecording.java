package com.snake19870227.stiger.aliyun.dypls.entity.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author BuHuaYang
 * 2021/1/25
 */
public class SecretRecording {

    @JsonProperty("pool_key")
    private String poolKey;
    @JsonProperty("subs_id")
    private String subsId;
    @JsonProperty("call_id")
    private String callId;
    @JsonProperty("call_time")
    private Date callTime;

    public String getPoolKey() {
        return poolKey;
    }

    public void setPoolKey(String poolKey) {
        this.poolKey = poolKey;
    }

    public String getSubsId() {
        return subsId;
    }

    public void setSubsId(String subsId) {
        this.subsId = subsId;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }
}
