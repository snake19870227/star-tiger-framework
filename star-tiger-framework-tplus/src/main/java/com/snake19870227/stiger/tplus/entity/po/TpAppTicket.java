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
 * T+应用APP_TICKET记录
 * </p>
 *
 * @author buhuayang
 * @since 2021-05-25
 */
@TableName("TP_APP_TICKET")
@ApiModel(value="TpAppTicket对象", description="T+应用APP_TICKET记录")
public class TpAppTicket implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "FLOW", type = IdType.ASSIGN_UUID)
    private String flow;

    @ApiModelProperty(value = "APP_TICKET")
    @TableField("APP_TICKET")
    private String appTicket;

    @TableField("CREATE_TIME")
    private String createTime;

    @TableField("MODIFY_TIME")
    private String modifyTime;


    public String getFlow() {
        return flow;
    }

    public TpAppTicket setFlow(String flow) {
        this.flow = flow;
        return this;
    }

    public String getAppTicket() {
        return appTicket;
    }

    public TpAppTicket setAppTicket(String appTicket) {
        this.appTicket = appTicket;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public TpAppTicket setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public TpAppTicket setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    @Override
    public String toString() {
        return "TpAppTicket{" +
        "flow=" + flow +
        ", appTicket=" + appTicket +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
