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
@ApiModel(value="SysDict对象", description="")
public class SysDict implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "字典项目流水号")
    @TableId(value = "dict_flow", type = IdType.ASSIGN_UUID)
    private String dictFlow;

    @ApiModelProperty(value = "字典项目编码")
    private String dictCode;

    @ApiModelProperty(value = "字典项目名称")
    private String dictName;

    @ApiModelProperty(value = "记录状态")
    @TableLogic
    private String recordStatus;


    public String getDictFlow() {
        return dictFlow;
    }

    public SysDict setDictFlow(String dictFlow) {
        this.dictFlow = dictFlow;
        return this;
    }

    public String getDictCode() {
        return dictCode;
    }

    public SysDict setDictCode(String dictCode) {
        this.dictCode = dictCode;
        return this;
    }

    public String getDictName() {
        return dictName;
    }

    public SysDict setDictName(String dictName) {
        this.dictName = dictName;
        return this;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public SysDict setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    @Override
    public String toString() {
        return "SysDict{" +
        "dictFlow=" + dictFlow +
        ", dictCode=" + dictCode +
        ", dictName=" + dictName +
        ", recordStatus=" + recordStatus +
        "}";
    }
}
