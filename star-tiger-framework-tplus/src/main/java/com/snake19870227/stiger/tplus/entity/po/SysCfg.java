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
 * 系统配置表
 * </p>
 *
 * @author buhuayang
 * @since 2021-05-25
 */
@TableName("SYS_CFG")
@ApiModel(value="SysCfg对象", description="系统配置表")
public class SysCfg implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "配置代码")
    @TableId(value = "CFG_CODE", type = IdType.ASSIGN_UUID)
    private String cfgCode;

    @ApiModelProperty(value = "配置内容")
    @TableField("CFG_VALUE")
    private String cfgValue;

    @ApiModelProperty(value = "机构流水号")
    @TableField("ORG_FLOW")
    private String orgFlow;

    @ApiModelProperty(value = "记录状态")
    @TableField("RECORD_STATUS")
    private String recordStatus;

    @ApiModelProperty(value = "最后更新时间")
    @TableField("LAST_MODIFY_TIME")
    private String lastModifyTime;


    public String getCfgCode() {
        return cfgCode;
    }

    public SysCfg setCfgCode(String cfgCode) {
        this.cfgCode = cfgCode;
        return this;
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public SysCfg setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue;
        return this;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public SysCfg setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
        return this;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public SysCfg setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public SysCfg setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
        return this;
    }

    @Override
    public String toString() {
        return "SysCfg{" +
        "cfgCode=" + cfgCode +
        ", cfgValue=" + cfgValue +
        ", orgFlow=" + orgFlow +
        ", recordStatus=" + recordStatus +
        ", lastModifyTime=" + lastModifyTime +
        "}";
    }
}
