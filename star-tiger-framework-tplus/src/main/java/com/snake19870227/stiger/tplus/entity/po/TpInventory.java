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
 * T+存货
 * </p>
 *
 * @author buhuayang
 * @since 2021-08-11
 */
@TableName("TP_INVENTORY")
@ApiModel(value="TpInventory对象", description="T+存货")
public class TpInventory implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "FLOW", type = IdType.ASSIGN_UUID)
    private String flow;

    @ApiModelProperty(value = "T+ID")
    @TableField("TP_ID")
    private String tpId;

    @ApiModelProperty(value = "存货编码")
    @TableField("TP_CODE")
    private String tpCode;

    @ApiModelProperty(value = "存货名称")
    @TableField("TP_NAME")
    private String tpName;

    @ApiModelProperty(value = "存货分类")
    @TableField("INVENTORY_CLASS_CODE")
    private String inventoryClassCode;

    @ApiModelProperty(value = "存货分类")
    @TableField("INVENTORY_CLASS_NAME")
    private String inventoryClassName;

    @ApiModelProperty(value = "计量单位")
    @TableField("UNIT_CODE")
    private String unitCode;

    @ApiModelProperty(value = "计量单位")
    @TableField("UNIT_NAME")
    private String unitName;

    @ApiModelProperty(value = "主计量单位")
    @TableField("BASE_UNIT_CODE")
    private String baseUnitCode;

    @ApiModelProperty(value = "主计量单位")
    @TableField("BASE_UNIT_NAME")
    private String baseUnitName;

    @ApiModelProperty(value = "采购常用单位")
    @TableField("UNIT_PURCHASE_CODE")
    private String unitPurchaseCode;

    @ApiModelProperty(value = "采购常用单位")
    @TableField("UNIT_PURCHASE_NAME")
    private String unitPurchaseName;

    @ApiModelProperty(value = "销售常用单位")
    @TableField("UNIT_SALE_CODE")
    private String unitSaleCode;

    @ApiModelProperty(value = "销售常用单位")
    @TableField("UNIT_SALE_NAME")
    private String unitSaleName;

    @ApiModelProperty(value = "库存常用单位")
    @TableField("UNIT_STOCK_CODE")
    private String unitStockCode;

    @ApiModelProperty(value = "库存常用单位")
    @TableField("UNIT_STOCK_NAME")
    private String unitStockName;

    @ApiModelProperty(value = "零售常用单位")
    @TableField("UNIT_RETAIL_CODE")
    private String unitRetailCode;

    @ApiModelProperty(value = "零售常用单位")
    @TableField("UNIT_RETAIL_NAME")
    private String unitRetailName;

    @ApiModelProperty(value = "生产常用单位")
    @TableField("UNIT_MANUFACTURE_CODE")
    private String unitManufactureCode;

    @ApiModelProperty(value = "生产常用单位")
    @TableField("UNIT_MANUFACTURE_NAME")
    private String unitManufactureName;

    @TableField("TP_DISABLED")
    private String tpDisabled;


    public String getFlow() {
        return flow;
    }

    public TpInventory setFlow(String flow) {
        this.flow = flow;
        return this;
    }

    public String getTpId() {
        return tpId;
    }

    public TpInventory setTpId(String tpId) {
        this.tpId = tpId;
        return this;
    }

    public String getTpCode() {
        return tpCode;
    }

    public TpInventory setTpCode(String tpCode) {
        this.tpCode = tpCode;
        return this;
    }

    public String getTpName() {
        return tpName;
    }

    public TpInventory setTpName(String tpName) {
        this.tpName = tpName;
        return this;
    }

    public String getInventoryClassCode() {
        return inventoryClassCode;
    }

    public TpInventory setInventoryClassCode(String inventoryClassCode) {
        this.inventoryClassCode = inventoryClassCode;
        return this;
    }

    public String getInventoryClassName() {
        return inventoryClassName;
    }

    public TpInventory setInventoryClassName(String inventoryClassName) {
        this.inventoryClassName = inventoryClassName;
        return this;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public TpInventory setUnitCode(String unitCode) {
        this.unitCode = unitCode;
        return this;
    }

    public String getUnitName() {
        return unitName;
    }

    public TpInventory setUnitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public String getBaseUnitCode() {
        return baseUnitCode;
    }

    public TpInventory setBaseUnitCode(String baseUnitCode) {
        this.baseUnitCode = baseUnitCode;
        return this;
    }

    public String getBaseUnitName() {
        return baseUnitName;
    }

    public TpInventory setBaseUnitName(String baseUnitName) {
        this.baseUnitName = baseUnitName;
        return this;
    }

    public String getUnitPurchaseCode() {
        return unitPurchaseCode;
    }

    public TpInventory setUnitPurchaseCode(String unitPurchaseCode) {
        this.unitPurchaseCode = unitPurchaseCode;
        return this;
    }

    public String getUnitPurchaseName() {
        return unitPurchaseName;
    }

    public TpInventory setUnitPurchaseName(String unitPurchaseName) {
        this.unitPurchaseName = unitPurchaseName;
        return this;
    }

    public String getUnitSaleCode() {
        return unitSaleCode;
    }

    public TpInventory setUnitSaleCode(String unitSaleCode) {
        this.unitSaleCode = unitSaleCode;
        return this;
    }

    public String getUnitSaleName() {
        return unitSaleName;
    }

    public TpInventory setUnitSaleName(String unitSaleName) {
        this.unitSaleName = unitSaleName;
        return this;
    }

    public String getUnitStockCode() {
        return unitStockCode;
    }

    public TpInventory setUnitStockCode(String unitStockCode) {
        this.unitStockCode = unitStockCode;
        return this;
    }

    public String getUnitStockName() {
        return unitStockName;
    }

    public TpInventory setUnitStockName(String unitStockName) {
        this.unitStockName = unitStockName;
        return this;
    }

    public String getUnitRetailCode() {
        return unitRetailCode;
    }

    public TpInventory setUnitRetailCode(String unitRetailCode) {
        this.unitRetailCode = unitRetailCode;
        return this;
    }

    public String getUnitRetailName() {
        return unitRetailName;
    }

    public TpInventory setUnitRetailName(String unitRetailName) {
        this.unitRetailName = unitRetailName;
        return this;
    }

    public String getUnitManufactureCode() {
        return unitManufactureCode;
    }

    public TpInventory setUnitManufactureCode(String unitManufactureCode) {
        this.unitManufactureCode = unitManufactureCode;
        return this;
    }

    public String getUnitManufactureName() {
        return unitManufactureName;
    }

    public TpInventory setUnitManufactureName(String unitManufactureName) {
        this.unitManufactureName = unitManufactureName;
        return this;
    }

    public String getTpDisabled() {
        return tpDisabled;
    }

    public TpInventory setTpDisabled(String tpDisabled) {
        this.tpDisabled = tpDisabled;
        return this;
    }

    @Override
    public String toString() {
        return "TpInventory{" +
        "flow=" + flow +
        ", tpId=" + tpId +
        ", tpCode=" + tpCode +
        ", tpName=" + tpName +
        ", inventoryClassCode=" + inventoryClassCode +
        ", inventoryClassName=" + inventoryClassName +
        ", unitCode=" + unitCode +
        ", unitName=" + unitName +
        ", baseUnitCode=" + baseUnitCode +
        ", baseUnitName=" + baseUnitName +
        ", unitPurchaseCode=" + unitPurchaseCode +
        ", unitPurchaseName=" + unitPurchaseName +
        ", unitSaleCode=" + unitSaleCode +
        ", unitSaleName=" + unitSaleName +
        ", unitStockCode=" + unitStockCode +
        ", unitStockName=" + unitStockName +
        ", unitRetailCode=" + unitRetailCode +
        ", unitRetailName=" + unitRetailName +
        ", unitManufactureCode=" + unitManufactureCode +
        ", unitManufactureName=" + unitManufactureName +
        ", tpDisabled=" + tpDisabled +
        "}";
    }
}
