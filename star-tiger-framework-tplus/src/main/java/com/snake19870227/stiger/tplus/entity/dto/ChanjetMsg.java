package com.snake19870227.stiger.tplus.entity.dto;

import cn.hutool.core.date.LocalDateTimeUtil;

import com.snake19870227.stiger.tplus.util.TimestampUtil;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2021/05/06
 */
public class ChanjetMsg {

    private String id;
    private String appKey;
    private String msgType;
    private long time;
    private String bizContent;

    public String getMsgTime() {
        return LocalDateTimeUtil.format(TimestampUtil.toLocalDateTime(this.time), "yyyyMMddHHmmss");
    }

    @Override
    public String toString() {
        return "ChanjetMsg{\n" +
                "\t\tid='" + id + "',\n" +
                "\t\tappKey='" + appKey + "',\n" +
                "\t\tmsgType='" + msgType + "', \n" +
                "\t\ttime=" + time + ",\n" +
                "\t\tbizContent='" + bizContent + "'\n" +
                "}";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getBizContent() {
        return bizContent;
    }

    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }
}
