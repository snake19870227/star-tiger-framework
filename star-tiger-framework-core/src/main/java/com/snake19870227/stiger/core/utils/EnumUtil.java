package com.snake19870227.stiger.core.utils;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import com.snake19870227.stiger.core.StarTigerEnum;

public class EnumUtil {

    public static List<? extends StarTigerEnum> toList(Class<? extends StarTigerEnum> enumClass) {
        return Arrays.asList(enumClass.getEnumConstants());
    }

    public static <T extends StarTigerEnum> LinkedHashMap<String, String> toMap(Class<? extends StarTigerEnum> enumClass) {
        return toMap(enumClass.getEnumConstants());
    }

    public static <T extends StarTigerEnum> LinkedHashMap<String, String> toMap(T[] values) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (StarTigerEnum item : values) {
            map.put(item.getId(), item.getName());
        }
        return map;
    }

    public static <T extends StarTigerEnum> String getId(T enumValue) {
        if (enumValue == null) {
            return null;
        }
        return enumValue.getId();
    }

    public static <T extends StarTigerEnum> String getName(T enumValue) {
        if (enumValue == null) {
            return null;
        }
        return enumValue.getName();
    }

    public static <T extends StarTigerEnum> T getById(String code, Class<T> enumClass) {
        return getById(code, enumClass.getEnumConstants());
    }


    public static String getNameByCode(String id, Class<? extends StarTigerEnum> enumClass) {
        return getById(id, enumClass.getEnumConstants()).getName();
    }

    public static <T extends StarTigerEnum> T getById(Object id, T[] values) {
        if (id == null) {
            return null;
        }
        if (id instanceof String && StrUtil.isBlank((String) id)) {
            return null;
        }

        for (T item : values) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public static <T extends StarTigerEnum> T getRequiredById(String code, Class<T> enumClass) {
        return (T) getRequiredById(code, enumClass.getEnumConstants());
    }

    public static <T extends StarTigerEnum> T getRequiredById(String code, T[] values) throws IllegalArgumentException {
        if (StrUtil.isBlank(code)) {
            return null;
        }

        StarTigerEnum v = getById(code, values);
        if (v == null) {
            if (values.length > 0) {
                String className = values[0].getClass().getName();
                throw new IllegalArgumentException("not found Enum:" + className + " value by id:" + code);
            } else {
                throw new IllegalArgumentException("not found Enum by id:" + code);
            }
        }
        return (T) v;
    }

}

