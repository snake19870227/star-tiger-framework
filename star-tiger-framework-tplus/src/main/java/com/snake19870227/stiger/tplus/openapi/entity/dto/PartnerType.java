package com.snake19870227.stiger.tplus.openapi.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PartnerType {

    @JsonProperty("Name")
    private String name;

    public PartnerType() {
    }

    public PartnerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
