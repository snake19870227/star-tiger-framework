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
 * T+员工
 * </p>
 *
 * @author buhuayang
 * @since 2021-08-16
 */
@TableName("TP_PERSON")
@ApiModel(value="TpPerson对象", description="T+员工")
public class TpPerson implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "FLOW", type = IdType.ASSIGN_UUID)
    private String flow;

    @ApiModelProperty(value = "T+ID")
    @TableField("TP_ID")
    private String tpId;

    @ApiModelProperty(value = "员工编码")
    @TableField("TP_CODE")
    private String tpCode;

    @ApiModelProperty(value = "员工名称")
    @TableField("TP_NAME")
    private String tpName;

    @ApiModelProperty(value = "T+员工部门ID")
    @TableField("TP_DEPT_ID")
    private String tpDeptId;

    @ApiModelProperty(value = "员工部门编码")
    @TableField("TP_DEPT_CODE")
    private String tpDeptCode;

    @ApiModelProperty(value = "员工部门名称")
    @TableField("TP_DEPT_NAME")
    private String tpDeptName;


    public String getFlow() {
        return flow;
    }

    public TpPerson setFlow(String flow) {
        this.flow = flow;
        return this;
    }

    public String getTpId() {
        return tpId;
    }

    public TpPerson setTpId(String tpId) {
        this.tpId = tpId;
        return this;
    }

    public String getTpCode() {
        return tpCode;
    }

    public TpPerson setTpCode(String tpCode) {
        this.tpCode = tpCode;
        return this;
    }

    public String getTpName() {
        return tpName;
    }

    public TpPerson setTpName(String tpName) {
        this.tpName = tpName;
        return this;
    }

    public String getTpDeptId() {
        return tpDeptId;
    }

    public TpPerson setTpDeptId(String tpDeptId) {
        this.tpDeptId = tpDeptId;
        return this;
    }

    public String getTpDeptCode() {
        return tpDeptCode;
    }

    public TpPerson setTpDeptCode(String tpDeptCode) {
        this.tpDeptCode = tpDeptCode;
        return this;
    }

    public String getTpDeptName() {
        return tpDeptName;
    }

    public TpPerson setTpDeptName(String tpDeptName) {
        this.tpDeptName = tpDeptName;
        return this;
    }

    @Override
    public String toString() {
        return "TpPerson{" +
        "flow=" + flow +
        ", tpId=" + tpId +
        ", tpCode=" + tpCode +
        ", tpName=" + tpName +
        ", tpDeptId=" + tpDeptId +
        ", tpDeptCode=" + tpDeptCode +
        ", tpDeptName=" + tpDeptName +
        "}";
    }
}
