package com.snake19870227.stiger.core.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.snake19870227.stiger.core.exception.BusinessException;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/04/19
 */
public class BusinessAssert {

    public static void notNull(Object obj, String message, Class<? extends BusinessException> exClass) {
        doAssert(obj, message, exClass, Objects::isNull);
    }

    public static void notBlank(String str, String message, Class<? extends BusinessException> exClass) {
        doAssert(str, message, exClass, StrUtil::isBlank);
    }

    public static void notEmpty(Collection<?> c, String message, Class<? extends BusinessException> exClass) {
        doAssert(c, message, exClass, CollUtil::isEmpty);
    }

    private static <T> void doAssert(T obj, String message, Class<? extends BusinessException> exClass, Predicate<T> predicate) {
        try {
            if (predicate.test(obj)) {
                throw createException(message, exClass);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(message);
        }
    }

    private static <T extends BusinessException> T createException(String message, Class<T> exClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = exClass.getConstructor(String.class);
        return constructor.newInstance(message);
    }
}
