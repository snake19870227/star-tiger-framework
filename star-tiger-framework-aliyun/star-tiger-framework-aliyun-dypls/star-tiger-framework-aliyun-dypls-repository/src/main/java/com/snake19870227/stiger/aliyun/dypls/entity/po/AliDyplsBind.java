package com.snake19870227.stiger.aliyun.dypls.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author buhuayang
 * @since 2021-02-04
 */
@ApiModel(value="AliDyplsBind对象", description="")
public class AliDyplsBind implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "绑定关系id")
    private String subId;

    @ApiModelProperty(value = "绑定类型")
    private String subType;

    @ApiModelProperty(value = "A号码")
    private String phonea;

    @ApiModelProperty(value = "B号码")
    private String phoneb;

    @ApiModelProperty(value = "X号码")
    private String phonex;

    @ApiModelProperty(value = "X号码分机号")
    private String phonexExt;

    @ApiModelProperty(value = "号码池")
    private String poolKey;

    @ApiModelProperty(value = "过期时间(yyyyMMddHHmmss)")
    private String expireTime;

    @ApiModelProperty(value = "是否录音(0:否;1:是)")
    private Integer isRecording;

    @ApiModelProperty(value = "绑定城市")
    private String city;

    @ApiModelProperty(value = "号码显示逻辑(见阿里云文档)")
    private Integer callDisplayType;

    @ApiModelProperty(value = "状态(0:未绑定;1:已绑定;2:已解绑)")
    private Integer status;

    @ApiModelProperty(value = "扩展")
    private String outId;


    public String getId() {
        return id;
    }

    public AliDyplsBind setId(String id) {
        this.id = id;
        return this;
    }

    public String getSubId() {
        return subId;
    }

    public AliDyplsBind setSubId(String subId) {
        this.subId = subId;
        return this;
    }

    public String getSubType() {
        return subType;
    }

    public AliDyplsBind setSubType(String subType) {
        this.subType = subType;
        return this;
    }

    public String getPhonea() {
        return phonea;
    }

    public AliDyplsBind setPhonea(String phonea) {
        this.phonea = phonea;
        return this;
    }

    public String getPhoneb() {
        return phoneb;
    }

    public AliDyplsBind setPhoneb(String phoneb) {
        this.phoneb = phoneb;
        return this;
    }

    public String getPhonex() {
        return phonex;
    }

    public AliDyplsBind setPhonex(String phonex) {
        this.phonex = phonex;
        return this;
    }

    public String getPhonexExt() {
        return phonexExt;
    }

    public AliDyplsBind setPhonexExt(String phonexExt) {
        this.phonexExt = phonexExt;
        return this;
    }

    public String getPoolKey() {
        return poolKey;
    }

    public AliDyplsBind setPoolKey(String poolKey) {
        this.poolKey = poolKey;
        return this;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public AliDyplsBind setExpireTime(String expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    public Integer getIsRecording() {
        return isRecording;
    }

    public AliDyplsBind setIsRecording(Integer isRecording) {
        this.isRecording = isRecording;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AliDyplsBind setCity(String city) {
        this.city = city;
        return this;
    }

    public Integer getCallDisplayType() {
        return callDisplayType;
    }

    public AliDyplsBind setCallDisplayType(Integer callDisplayType) {
        this.callDisplayType = callDisplayType;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public AliDyplsBind setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getOutId() {
        return outId;
    }

    public AliDyplsBind setOutId(String outId) {
        this.outId = outId;
        return this;
    }

    @Override
    public String toString() {
        return "AliDyplsBind{" +
        "id=" + id +
        ", subId=" + subId +
        ", subType=" + subType +
        ", phonea=" + phonea +
        ", phoneb=" + phoneb +
        ", phonex=" + phonex +
        ", phonexExt=" + phonexExt +
        ", poolKey=" + poolKey +
        ", expireTime=" + expireTime +
        ", isRecording=" + isRecording +
        ", city=" + city +
        ", callDisplayType=" + callDisplayType +
        ", status=" + status +
        ", outId=" + outId +
        "}";
    }
}
