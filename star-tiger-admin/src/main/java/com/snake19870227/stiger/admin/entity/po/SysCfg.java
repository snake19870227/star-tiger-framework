package com.snake19870227.stiger.admin.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author buhuayang
 * @since 2020-08-17
 */
@ApiModel(value="SysCfg对象", description="")
public class SysCfg implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "配置代码")
    @TableId(value = "cfg_code", type = IdType.ASSIGN_UUID)
    private String cfgCode;

    @ApiModelProperty(value = "配置内容")
    private String cfgValue;

    @ApiModelProperty(value = "机构流水号")
    private String orgFlow;

    @ApiModelProperty(value = "记录状态")
    @TableLogic
    private String recordStatus;


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

    @Override
    public String toString() {
        return "SysCfg{" +
        "cfgCode=" + cfgCode +
        ", cfgValue=" + cfgValue +
        ", orgFlow=" + orgFlow +
        ", recordStatus=" + recordStatus +
        "}";
    }
}
