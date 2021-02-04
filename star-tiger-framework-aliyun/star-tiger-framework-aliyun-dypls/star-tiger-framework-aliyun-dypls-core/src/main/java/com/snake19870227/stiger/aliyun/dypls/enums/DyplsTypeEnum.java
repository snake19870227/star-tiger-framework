package com.snake19870227.stiger.aliyun.dypls.enums;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

import com.aliyuncs.AcsRequest;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxbRequest;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnRequest;
import com.snake19870227.stiger.core.StarTigerEnum;

/**
 * @author BuHuaYang
 * 2021/1/28
 */
public enum DyplsTypeEnum implements StarTigerEnum {

    /**
     * AXB模式
     */
    BindAxb("BindAxb", "AXB模式", BindAxbRequest.class),

    /**
     * AXN模式
     */
    BindAxn("BindAxn", "AXN模式", BindAxnRequest.class)
    ;

    private final String id;
    private final String name;

    private final Class<? extends AcsRequest<? extends AcsResponse>> requestClass;

    DyplsTypeEnum(String id, String name, Class<? extends AcsRequest<? extends AcsResponse>> requestClass) {
        this.id = id;
        this.name = name;
        this.requestClass = requestClass;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Class<? extends AcsRequest<? extends AcsResponse>> getRequestClass() {
        return requestClass;
    }

    public AcsRequest<? extends AcsResponse> createRequest(String phonea,
                                                           String phoneb,
                                                           String phonex,
                                                           String expireTime,
                                                           String poolKey,
                                                           boolean isRecordingEnabled,
                                                           String city,
                                                           String outId) throws IllegalAccessException, InstantiationException {
        AcsRequest<? extends AcsResponse> request = requestClass.newInstance();
        ReflectUtil.invoke(request, "setPhoneNoA", phonea);
        ReflectUtil.invoke(request, "setExpiration", expireTime);
        ReflectUtil.invoke(request, "setPoolKey", poolKey);
        ReflectUtil.invoke(request, "setIsRecordingEnabled", isRecordingEnabled);
        if (StrUtil.isNotBlank(phoneb)) {
            ReflectUtil.invoke(request, "setPhoneNoB", phoneb);
        }
        if (StrUtil.isNotBlank(phonex)) {
            ReflectUtil.invoke(request, "setPhoneNoX", phonex);
        }
        if (StrUtil.isNotBlank(outId)) {
            ReflectUtil.invoke(request, "setOutId", outId);
        }
        if (StrUtil.isNotBlank(city)) {
            ReflectUtil.invoke(request, "setExpectCity", city);
        }
        return request;
    }
}
