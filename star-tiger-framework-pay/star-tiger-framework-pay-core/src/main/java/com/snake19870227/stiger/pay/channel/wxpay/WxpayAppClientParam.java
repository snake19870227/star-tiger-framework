package com.snake19870227.stiger.pay.channel.wxpay;

import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snake19870227.stiger.pay.channel.ChannelClientParam;

/**
 * @author BuHuaYang
 * 2021/4/13
 */
public class WxpayAppClientParam implements ChannelClientParam {

    @ApiModelProperty("签名")
    private String sign;
    @ApiModelProperty("prepayId")
    private String prepayId;
    @ApiModelProperty("partnerId")
    private String partnerId;
    @ApiModelProperty("appId")
    private String appId;
    @ApiModelProperty("格式:Sign=WXPay")
    @JsonProperty("package")
    private String packageValue;
    @ApiModelProperty("时间戳")
    private String timeStamp;
    @ApiModelProperty("随机字符串")
    private String nonceStr;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
}
