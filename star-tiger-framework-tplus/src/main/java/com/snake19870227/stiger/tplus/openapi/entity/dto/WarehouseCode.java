package com.snake19870227.stiger.tplus.openapi.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WarehouseCode {

    @JsonProperty("Code")
    private String code;

    public WarehouseCode() {
    }

    public WarehouseCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
