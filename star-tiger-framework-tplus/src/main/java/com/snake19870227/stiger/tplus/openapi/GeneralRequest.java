package com.snake19870227.stiger.tplus.openapi;

import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BuHuaYang
 * 8/6 006
 */
public class GeneralRequest<T> {

    private String apiUrl;

    private Class<T> responseResultClass;

    private Map<String, Object> requestBodyMap;

    public GeneralRequest() {
        this.requestBodyMap = new HashMap<>();
    }

    public GeneralRequest<T> bodyProperty(String key, Object val) {
        this.requestBodyMap.put(key, val);
        return this;
    }

    public String requestBody() {
        return JSONUtil.toJsonStr(requestBodyMap);
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public GeneralRequest<T> setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
        return this;
    }

    public Class<T> getResponseResultClass() {
        return responseResultClass;
    }

    public GeneralRequest<T> setResponseResultClass(Class<T> responseResultClass) {
        this.responseResultClass = responseResultClass;
        return this;
    }

    public Map<String, Object> getRequestBodyMap() {
        return requestBodyMap;
    }
}
