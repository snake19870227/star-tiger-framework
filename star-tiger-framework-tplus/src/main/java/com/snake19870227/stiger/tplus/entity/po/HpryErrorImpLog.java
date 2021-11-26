package com.snake19870227.stiger.tplus.entity.po;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author buhuayang
 * @since 2021-09-03
 */
@TableName("HPRY_ERROR_IMP_LOG")
@ApiModel(value="HpryErrorImpLog对象", description="")
public class HpryErrorImpLog implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "FLOW", type = IdType.ASSIGN_UUID)
    private String flow;

    @TableField("TYPE")
    private BigDecimal type;

    @TableField("IMP_CONTENT")
    private String impContent;

    @TableField("ERROR_STACK")
    private String errorStack;


    public String getFlow() {
        return flow;
    }

    public HpryErrorImpLog setFlow(String flow) {
        this.flow = flow;
        return this;
    }

    public BigDecimal getType() {
        return type;
    }

    public HpryErrorImpLog setType(BigDecimal type) {
        this.type = type;
        return this;
    }

    public String getImpContent() {
        return impContent;
    }

    public HpryErrorImpLog setImpContent(String impContent) {
        this.impContent = impContent;
        return this;
    }

    public String getErrorStack() {
        return errorStack;
    }

    public HpryErrorImpLog setErrorStack(String errorStack) {
        this.errorStack = errorStack;
        return this;
    }

    @Override
    public String toString() {
        return "HpryErrorImpLog{" +
        "flow=" + flow +
        ", type=" + type +
        ", impContent=" + impContent +
        ", errorStack=" + errorStack +
        "}";
    }
}
