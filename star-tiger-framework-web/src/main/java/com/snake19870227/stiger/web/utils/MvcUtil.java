package com.snake19870227.stiger.web.utils;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/03/26
 */
public class MvcUtil {

    public static boolean isModelAndView(HandlerMethod handlerMethod) {
        return ModelAndView.class.isAssignableFrom(handlerMethod.getReturnType().getParameterType());
    }

    public static boolean isHttpEntity(HandlerMethod handlerMethod) {
        return (HttpEntity.class.isAssignableFrom(handlerMethod.getReturnType().getParameterType()) &&
                !RequestEntity.class.isAssignableFrom(handlerMethod.getReturnType().getParameterType()));
    }

    public static boolean isResponseBody(HandlerMethod handlerMethod) {
        return (AnnotatedElementUtils.hasAnnotation(handlerMethod.getReturnType().getContainingClass(), ResponseBody.class) ||
                handlerMethod.getReturnType().hasMethodAnnotation(ResponseBody.class));
    }

    public static boolean isViewName(HandlerMethod handlerMethod) {
        Class<?> paramType = handlerMethod.getReturnType().getParameterType();
        return (void.class == paramType || CharSequence.class.isAssignableFrom(paramType));
    }
}
