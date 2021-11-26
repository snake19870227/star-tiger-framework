package com.snake19870227.stiger.tplus.openapi.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Expenseitem {

    @JsonProperty("Code")
    private String code;

    public Expenseitem() {
    }

    public Expenseitem(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
