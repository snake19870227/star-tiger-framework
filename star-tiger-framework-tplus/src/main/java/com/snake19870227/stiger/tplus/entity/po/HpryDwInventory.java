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
 * 低温产品
 * </p>
 *
 * @author buhuayang
 * @since 2021-08-31
 */
@TableName("HPRY_DW_INVENTORY")
@ApiModel(value="HpryDwInventory对象", description="低温产品")
public class HpryDwInventory implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "FLOW", type = IdType.ASSIGN_UUID)
    private String flow;

    @ApiModelProperty(value = "存货编码")
    @TableField("TP_CODE")
    private String tpCode;

    @ApiModelProperty(value = "存货名称")
    @TableField("TP_NAME")
    private String tpName;

    @ApiModelProperty(value = "模板中名称")
    @TableField("TEMPLATE_NAME")
    private String templateName;


    public String getFlow() {
        return flow;
    }

    public HpryDwInventory setFlow(String flow) {
        this.flow = flow;
        return this;
    }

    public String getTpCode() {
        return tpCode;
    }

    public HpryDwInventory setTpCode(String tpCode) {
        this.tpCode = tpCode;
        return this;
    }

    public String getTpName() {
        return tpName;
    }

    public HpryDwInventory setTpName(String tpName) {
        this.tpName = tpName;
        return this;
    }

    public String getTemplateName() {
        return templateName;
    }

    public HpryDwInventory setTemplateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    @Override
    public String toString() {
        return "HpryDwInventory{" +
        "flow=" + flow +
        ", tpCode=" + tpCode +
        ", tpName=" + tpName +
        ", templateName=" + templateName +
        "}";
    }
}
