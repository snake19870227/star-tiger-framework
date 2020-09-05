package com.snake19870227.stiger.amap.webapi.entity.bo;

import java.util.List;

/**
 * @author BuHuaYang
 * 2020/9/5
 */
public class District {

    private Object citycode;
    private String adcode;
    private String name;
    private String center;
    private String level;

    private List<District> districts;

    public Object getCitycode() {
        return citycode;
    }

    public void setCitycode(Object citycode) {
        this.citycode = citycode;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
