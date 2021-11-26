package com.snake19870227.stiger.tplus.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * T+消息推送日志
 * </p>
 *
 * @author buhuayang
 * @since 2021-05-25
 */
@TableName("TP_MESSAGE")
@ApiModel(value="TpMessage对象", description="T+消息推送日志")
public class TpMessage implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "FLOW", type = IdType.ASSIGN_UUID)
    private String flow;

    @ApiModelProperty(value = "消息ID")
    @TableField("MSG_ID")
    private String msgId;

    @ApiModelProperty(value = "开放平台appKey")
    @TableField("APP_KEY")
    private String appKey;

    @ApiModelProperty(value = "消息类型")
    @TableField("MSG_TYPE")
    private String msgType;

    @ApiModelProperty(value = "时间戳")
    @TableField("MSG_TIME")
    private String msgTime;

    @ApiModelProperty(value = "消息体")
    @TableField("BIZ_CONTENT")
    private String bizContent;

    @TableField("CREATE_TIME")
    private String createTime;

    @TableField("MODIFY_TIME")
    private String modifyTime;


    public String getFlow() {
        return flow;
    }

    public TpMessage setFlow(String flow) {
        this.flow = flow;
        return this;
    }

    public String getMsgId() {
        return msgId;
    }

    public TpMessage setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public String getAppKey() {
        return appKey;
    }

    public TpMessage setAppKey(String appKey) {
        this.appKey = appKey;
        return this;
    }

    public String getMsgType() {
        return msgType;
    }

    public TpMessage setMsgType(String msgType) {
        this.msgType = msgType;
        return this;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public TpMessage setMsgTime(String msgTime) {
        this.msgTime = msgTime;
        return this;
    }

    public String getBizContent() {
        return bizContent;
    }

    public TpMessage setBizContent(String bizContent) {
        this.bizContent = bizContent;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public TpMessage setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public TpMessage setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    @Override
    public String toString() {
        return "TpMessage{" +
        "flow=" + flow +
        ", msgId=" + msgId +
        ", appKey=" + appKey +
        ", msgType=" + msgType +
        ", msgTime=" + msgTime +
        ", bizContent=" + bizContent +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
