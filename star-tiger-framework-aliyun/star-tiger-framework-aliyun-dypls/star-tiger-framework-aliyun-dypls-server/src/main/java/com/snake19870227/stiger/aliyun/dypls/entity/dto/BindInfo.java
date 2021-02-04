package com.snake19870227.stiger.aliyun.dypls.entity.dto;

/**
 * @author BuHuaYang
 * 2021/1/28
 */
public class BindInfo {

    private String phonea;
    private String phoneb;
    private String simpleExpireTime;
    private String poolKey;
    private boolean recordingEnabled;
    private String outId;

    public BindInfo() {
        this.recordingEnabled = false;
    }

    public String getPhonea() {
        return phonea;
    }

    public void setPhonea(String phonea) {
        this.phonea = phonea;
    }

    public String getPhoneb() {
        return phoneb;
    }

    public void setPhoneb(String phoneb) {
        this.phoneb = phoneb;
    }

    public String getSimpleExpireTime() {
        return simpleExpireTime;
    }

    public void setSimpleExpireTime(String simpleExpireTime) {
        this.simpleExpireTime = simpleExpireTime;
    }

    public String getPoolKey() {
        return poolKey;
    }

    public void setPoolKey(String poolKey) {
        this.poolKey = poolKey;
    }

    public boolean isRecordingEnabled() {
        return recordingEnabled;
    }

    public void setRecordingEnabled(boolean recordingEnabled) {
        this.recordingEnabled = recordingEnabled;
    }

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }
}
