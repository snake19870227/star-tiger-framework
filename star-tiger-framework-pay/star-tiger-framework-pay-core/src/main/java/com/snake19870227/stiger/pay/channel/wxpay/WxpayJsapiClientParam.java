package com.snake19870227.stiger.pay.channel.wxpay;

import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snake19870227.stiger.pay.channel.ChannelClientParam;

/**
 * @author BuHuaYang
 * 2020/9/27
 */
public class WxpayJsapiClientParam implements ChannelClientParam {

    @ApiModelProperty("appId")
    private String appId;
    @ApiModelProperty("时间戳")
    private String timeStamp;
    @ApiModelProperty("随机字符串")
    private String nonceStr;
    @ApiModelProperty("统一下单接口返回的prepay_id,格式:perpay_id=***")
    @JsonProperty("package")
    private String packageValue;
    @ApiModelProperty("签名算法")
    private String signType;
    @ApiModelProperty("签名")
    private String paySign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}
