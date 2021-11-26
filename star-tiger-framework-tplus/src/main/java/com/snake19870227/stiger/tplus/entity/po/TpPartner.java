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
 * T+往来单位
 * </p>
 *
 * @author buhuayang
 * @since 2021-08-12
 */
@TableName("TP_PARTNER")
@ApiModel(value="TpPartner对象", description="T+往来单位")
public class TpPartner implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "FLOW", type = IdType.ASSIGN_UUID)
    private String flow;

    @ApiModelProperty(value = "往来单位编码")
    @TableField("TP_CODE")
    private String tpCode;

    @ApiModelProperty(value = "往来单位名称")
    @TableField("TP_NAME")
    private String tpName;

    @ApiModelProperty(value = "往来单位类型")
    @TableField("PARTNER_TYPE_NAME")
    private String partnerTypeName;


    public String getFlow() {
        return flow;
    }

    public TpPartner setFlow(String flow) {
        this.flow = flow;
        return this;
    }

    public String getTpCode() {
        return tpCode;
    }

    public TpPartner setTpCode(String tpCode) {
        this.tpCode = tpCode;
        return this;
    }

    public String getTpName() {
        return tpName;
    }

    public TpPartner setTpName(String tpName) {
        this.tpName = tpName;
        return this;
    }

    public String getPartnerTypeName() {
        return partnerTypeName;
    }

    public TpPartner setPartnerTypeName(String partnerTypeName) {
        this.partnerTypeName = partnerTypeName;
        return this;
    }

    @Override
    public String toString() {
        return "TpPartner{" +
        "flow=" + flow +
        ", tpCode=" + tpCode +
        ", tpName=" + tpName +
        ", partnerTypeName=" + partnerTypeName +
        "}";
    }
}
