package com.snake19870227.stiger.amap.webapi;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.amap.webapi.common.WebApiConstant;
import com.snake19870227.stiger.amap.webapi.entity.dto.DistrictWebApiResp;
import com.snake19870227.stiger.amap.webapi.exception.WebApiException;
import com.snake19870227.stiger.core.utils.JsonUtil;

/**
 * @author BuHuaYang
 * 2020/9/5
 */
public class DistrictApi {

    private final String endpoint;

    private final String key;

    private ObjectMapper objectMapper;

    public DistrictApi(String key) {
        this.endpoint = WebApiConstant.ENDPOINT_DISTRICT;
        this.key = key;
    }

    public DistrictWebApiResp query(String keywords, Integer subdistrict, Integer page, Integer offset) {

        if (subdistrict == null) {
            subdistrict = 1;
        }

        if (page == null) {
            page = 1;
        }

        if (offset == null) {
            offset = 20;
        }

        Map<String, Object> params = createCommonParams();

        params.put("subdistrict", String.valueOf(subdistrict));
        params.put("page", String.valueOf(page));
        params.put("offset", String.valueOf(offset));

        if (StrUtil.isBlank(keywords)) {
            params.put("keywords", keywords);
        }

        try {

            String responseJson = HttpUtil.get(endpoint, params);

            initObjectMapper();

            return objectMapper.readValue(responseJson, DistrictWebApiResp.class);

        } catch (Exception e) {
            throw new WebApiException("查询行政区划失败.[subdistrict=" + subdistrict + "|page=" + page + "|offset=" + offset + "]", e);
        }
    }

    private Map<String, Object> createCommonParams() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("key", key);
        return paramMap;
    }

    private void initObjectMapper() {
        if (this.objectMapper == null) {
            this.objectMapper = JsonUtil.buildJacksonObjectMapper();
        }
    }

    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = JsonUtil.buildJacksonObjectMapper();
            String jsonResponse = HttpUtil.get("https://restapi.amap.com/v3/config/district?key=81705a9bafa2351051d96f536403cd02&subdistrict=3&filter=340000");
            DistrictWebApiResp districtWebApiResp = objectMapper.readValue(jsonResponse, DistrictWebApiResp.class);
            System.out.println(districtWebApiResp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
