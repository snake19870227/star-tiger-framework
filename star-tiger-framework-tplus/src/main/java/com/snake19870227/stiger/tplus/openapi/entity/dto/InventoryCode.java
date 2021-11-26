package com.snake19870227.stiger.tplus.openapi.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InventoryCode {

    @JsonProperty("Code")
    private String code;

    public InventoryCode() {
    }

    public InventoryCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
