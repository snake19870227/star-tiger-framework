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
 * 低温往来单位
 * </p>
 *
 * @author buhuayang
 * @since 2021-08-31
 */
@TableName("HPRY_DW_PARTNER")
@ApiModel(value="HpryDwPartner对象", description="低温往来单位")
public class HpryDwPartner implements Serializable {

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

    @ApiModelProperty(value = "模板中名称")
    @TableField("TEMPLATE_NAME")
    private String templateName;


    public String getFlow() {
        return flow;
    }

    public HpryDwPartner setFlow(String flow) {
        this.flow = flow;
        return this;
    }

    public String getTpCode() {
        return tpCode;
    }

    public HpryDwPartner setTpCode(String tpCode) {
        this.tpCode = tpCode;
        return this;
    }

    public String getTpName() {
        return tpName;
    }

    public HpryDwPartner setTpName(String tpName) {
        this.tpName = tpName;
        return this;
    }

    public String getTemplateName() {
        return templateName;
    }

    public HpryDwPartner setTemplateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    @Override
    public String toString() {
        return "HpryDwPartner{" +
        "flow=" + flow +
        ", tpCode=" + tpCode +
        ", tpName=" + tpName +
        ", templateName=" + templateName +
        "}";
    }
}
