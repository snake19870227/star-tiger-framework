package com.snake19870227.stiger.amap.webapi.entity.dto;

/**
 * @author BuHuaYang
 * 2020/9/5
 */
public abstract class BaseWebApiResp {

    private String status;
    private String info;
    private String infocode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }
}
