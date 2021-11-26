package com.snake19870227.stiger.tplus.openapi.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaleDeliveryDetail {

    @JsonProperty("Warehouse")
    private WarehouseCode warehouse;

    @JsonProperty("Unit")
    private Unit unit;

    @JsonProperty("Quantity")
    private Long quantity;

    @JsonProperty("Inventory")
    private InventoryCode inventory;

    @JsonProperty("OrigTaxAmount")
    private Double origTaxAmount;

    public WarehouseCode getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseCode warehouse) {
        this.warehouse = warehouse;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public InventoryCode getInventory() {
        return inventory;
    }

    public void setInventory(InventoryCode inventory) {
        this.inventory = inventory;
    }

    public Double getOrigTaxAmount() {
        return origTaxAmount;
    }

    public void setOrigTaxAmount(Double origTaxAmount) {
        this.origTaxAmount = origTaxAmount;
    }
}
