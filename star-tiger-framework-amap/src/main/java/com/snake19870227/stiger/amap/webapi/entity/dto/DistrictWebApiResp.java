package com.snake19870227.stiger.amap.webapi.entity.dto;

import java.util.List;

import com.snake19870227.stiger.amap.webapi.entity.bo.District;

/**
 * @author BuHuaYang
 * 2020/9/5
 */
public class DistrictWebApiResp extends BaseWebApiResp {

    private List<District> districts;

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
