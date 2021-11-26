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
 * T+仓库
 * </p>
 *
 * @author buhuayang
 * @since 2021-08-11
 */
@TableName("TP_WAREHOUSE")
@ApiModel(value="TpWarehouse对象", description="T+仓库")
public class TpWarehouse implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "FLOW", type = IdType.ASSIGN_UUID)
    private String flow;

    @ApiModelProperty(value = "T+ID")
    @TableField("TP_ID")
    private String tpId;

    @ApiModelProperty(value = "仓库编码")
    @TableField("TP_CODE")
    private String tpCode;

    @ApiModelProperty(value = "仓库名称")
    @TableField("TP_NAME")
    private String tpName;


    public String getFlow() {
        return flow;
    }

    public TpWarehouse setFlow(String flow) {
        this.flow = flow;
        return this;
    }

    public String getTpId() {
        return tpId;
    }

    public TpWarehouse setTpId(String tpId) {
        this.tpId = tpId;
        return this;
    }

    public String getTpCode() {
        return tpCode;
    }

    public TpWarehouse setTpCode(String tpCode) {
        this.tpCode = tpCode;
        return this;
    }

    public String getTpName() {
        return tpName;
    }

    public TpWarehouse setTpName(String tpName) {
        this.tpName = tpName;
        return this;
    }

    @Override
    public String toString() {
        return "TpWarehouse{" +
        "flow=" + flow +
        ", tpId=" + tpId +
        ", tpCode=" + tpCode +
        ", tpName=" + tpName +
        "}";
    }
}
