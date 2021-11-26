package com.snake19870227.stiger.tplus.openapi.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Inventory extends InventoryCode {

    @JsonProperty("ID")
    private Long id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("InventoryClassCode")
    private String inventoryClassCode;
    @JsonProperty("InventoryClassName")
    private String inventoryClassName;
    @JsonProperty("UnitCode")
    private String unitCode;
    @JsonProperty("UnitName")
    private String unitName;
    @JsonProperty("BaseUnitCode")
    private String baseUnitCode;
    @JsonProperty("BaseUnitName")
    private String baseUnitName;
    @JsonProperty("Disabled")
    private String disabled;
    @JsonProperty("UnitByPurchaseCode")
    private String unitByPurchaseCode;
    @JsonProperty("UnitByPurchaseName")
    private String unitByPurchaseName;
    @JsonProperty("UnitBySaleCode")
    private String unitBySaleCode;
    @JsonProperty("UnitBySaleName")
    private String unitBySaleName;
    @JsonProperty("UnitByStockCode")
    private String unitByStockCode;
    @JsonProperty("UnitByStockName")
    private String unitByStockName;
    @JsonProperty("UnitByRetailCode")
    private String unitByRetailCode;
    @JsonProperty("UnitByRetailName")
    private String unitByRetailName;
    @JsonProperty("UnitByManufactureCode")
    private String unitByManufactureCode;
    @JsonProperty("UnitByManufactureName")
    private String unitByManufactureName;
    @JsonProperty("RetailPriceNew")
    private String retailPriceNew;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInventoryClassCode() {
        return inventoryClassCode;
    }

    public void setInventoryClassCode(String inventoryClassCode) {
        this.inventoryClassCode = inventoryClassCode;
    }

    public String getInventoryClassName() {
        return inventoryClassName;
    }

    public void setInventoryClassName(String inventoryClassName) {
        this.inventoryClassName = inventoryClassName;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getBaseUnitCode() {
        return baseUnitCode;
    }

    public void setBaseUnitCode(String baseUnitCode) {
        this.baseUnitCode = baseUnitCode;
    }

    public String getBaseUnitName() {
        return baseUnitName;
    }

    public void setBaseUnitName(String baseUnitName) {
        this.baseUnitName = baseUnitName;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getUnitByPurchaseCode() {
        return unitByPurchaseCode;
    }

    public void setUnitByPurchaseCode(String unitByPurchaseCode) {
        this.unitByPurchaseCode = unitByPurchaseCode;
    }

    public String getUnitByPurchaseName() {
        return unitByPurchaseName;
    }

    public void setUnitByPurchaseName(String unitByPurchaseName) {
        this.unitByPurchaseName = unitByPurchaseName;
    }

    public String getUnitBySaleCode() {
        return unitBySaleCode;
    }

    public void setUnitBySaleCode(String unitBySaleCode) {
        this.unitBySaleCode = unitBySaleCode;
    }

    public String getUnitBySaleName() {
        return unitBySaleName;
    }

    public void setUnitBySaleName(String unitBySaleName) {
        this.unitBySaleName = unitBySaleName;
    }

    public String getUnitByStockCode() {
        return unitByStockCode;
    }

    public void setUnitByStockCode(String unitByStockCode) {
        this.unitByStockCode = unitByStockCode;
    }

    public String getUnitByStockName() {
        return unitByStockName;
    }

    public void setUnitByStockName(String unitByStockName) {
        this.unitByStockName = unitByStockName;
    }

    public String getUnitByRetailCode() {
        return unitByRetailCode;
    }

    public void setUnitByRetailCode(String unitByRetailCode) {
        this.unitByRetailCode = unitByRetailCode;
    }

    public String getUnitByRetailName() {
        return unitByRetailName;
    }

    public void setUnitByRetailName(String unitByRetailName) {
        this.unitByRetailName = unitByRetailName;
    }

    public String getUnitByManufactureCode() {
        return unitByManufactureCode;
    }

    public void setUnitByManufactureCode(String unitByManufactureCode) {
        this.unitByManufactureCode = unitByManufactureCode;
    }

    public String getUnitByManufactureName() {
        return unitByManufactureName;
    }

    public void setUnitByManufactureName(String unitByManufactureName) {
        this.unitByManufactureName = unitByManufactureName;
    }

    public String getRetailPriceNew() {
        return retailPriceNew;
    }

    public void setRetailPriceNew(String retailPriceNew) {
        this.retailPriceNew = retailPriceNew;
    }
}
