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
 * @since 2020-08-30
 */
@ApiModel(value="SysModule对象", description="")
public class SysModule implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "模块流水号")
    @TableId(value = "module_flow", type = IdType.ASSIGN_UUID)
    private String moduleFlow;

    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    @ApiModelProperty(value = "排序码")
    private Integer moduleOrder;

    @ApiModelProperty(value = "启用标记")
    private String enableFlag;

    @ApiModelProperty(value = "记录状态")
    @TableLogic
    private String recordStatus;


    public String getModuleFlow() {
        return moduleFlow;
    }

    public SysModule setModuleFlow(String moduleFlow) {
        this.moduleFlow = moduleFlow;
        return this;
    }

    public String getModuleName() {
        return moduleName;
    }

    public SysModule setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public Integer getModuleOrder() {
        return moduleOrder;
    }

    public SysModule setModuleOrder(Integer moduleOrder) {
        this.moduleOrder = moduleOrder;
        return this;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public SysModule setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
        return this;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public SysModule setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    @Override
    public String toString() {
        return "SysModule{" +
        "moduleFlow=" + moduleFlow +
        ", moduleName=" + moduleName +
        ", moduleOrder=" + moduleOrder +
        ", enableFlag=" + enableFlag +
        ", recordStatus=" + recordStatus +
        "}";
    }
}
