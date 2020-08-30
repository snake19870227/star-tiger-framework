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
@ApiModel(value="SysDictItem对象", description="")
public class SysDictItem implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "字典值流水号")
    @TableId(value = "dict_item_flow", type = IdType.ASSIGN_UUID)
    private String dictItemFlow;

    @ApiModelProperty(value = "字典项目流水号")
    private String dictFlow;

    @ApiModelProperty(value = "字典值编码")
    private String dictItemCode;

    @ApiModelProperty(value = "字典值名称")
    private String dictItemName;

    @ApiModelProperty(value = "选项排序码")
    private Integer dictItemOrder;

    @ApiModelProperty(value = "记录状态")
    @TableLogic
    private String recordStatus;


    public String getDictItemFlow() {
        return dictItemFlow;
    }

    public SysDictItem setDictItemFlow(String dictItemFlow) {
        this.dictItemFlow = dictItemFlow;
        return this;
    }

    public String getDictFlow() {
        return dictFlow;
    }

    public SysDictItem setDictFlow(String dictFlow) {
        this.dictFlow = dictFlow;
        return this;
    }

    public String getDictItemCode() {
        return dictItemCode;
    }

    public SysDictItem setDictItemCode(String dictItemCode) {
        this.dictItemCode = dictItemCode;
        return this;
    }

    public String getDictItemName() {
        return dictItemName;
    }

    public SysDictItem setDictItemName(String dictItemName) {
        this.dictItemName = dictItemName;
        return this;
    }

    public Integer getDictItemOrder() {
        return dictItemOrder;
    }

    public SysDictItem setDictItemOrder(Integer dictItemOrder) {
        this.dictItemOrder = dictItemOrder;
        return this;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public SysDictItem setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    @Override
    public String toString() {
        return "SysDictItem{" +
        "dictItemFlow=" + dictItemFlow +
        ", dictFlow=" + dictFlow +
        ", dictItemCode=" + dictItemCode +
        ", dictItemName=" + dictItemName +
        ", dictItemOrder=" + dictItemOrder +
        ", recordStatus=" + recordStatus +
        "}";
    }
}
