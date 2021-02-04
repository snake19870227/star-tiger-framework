package com.snake19870227.stiger.aliyun.dypls.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author BuHuaYang
 * 2021/1/25
 */
public class SecretStartReport {

    @JsonProperty("pool_key")
    private String poolKey;
    @JsonProperty("sub_id")
    private String subId;
    @JsonProperty("call_id")
    private String callId;
    @JsonProperty("phone_no")
    private String phoneNo;
    @JsonProperty("secret_no")
    private String secretNo;
    @JsonProperty("peer_no")
    private String peerNo;
    @JsonProperty("called_display_no")
    private String calledDisplayNo;
    @JsonProperty("call_type")
    private Integer callType;
    @JsonProperty("call_time")
    private String callTime;
    @JsonProperty("out_id")
    private String outId;

    public String getPoolKey() {
        return poolKey;
    }

    public void setPoolKey(String poolKey) {
        this.poolKey = poolKey;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSecretNo() {
        return secretNo;
    }

    public void setSecretNo(String secretNo) {
        this.secretNo = secretNo;
    }

    public String getPeerNo() {
        return peerNo;
    }

    public void setPeerNo(String peerNo) {
        this.peerNo = peerNo;
    }

    public String getCalledDisplayNo() {
        return calledDisplayNo;
    }

    public void setCalledDisplayNo(String calledDisplayNo) {
        this.calledDisplayNo = calledDisplayNo;
    }

    public Integer getCallType() {
        return callType;
    }

    public void setCallType(Integer callType) {
        this.callType = callType;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }
}
