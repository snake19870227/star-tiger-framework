package com.snake19870227.stiger.admin.web;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author BuHuaYang
 * 2020/9/3
 */
public class StarTigerAdminControllerHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected boolean isHandler(Class<?> beanType) {
        return AnnotatedElementUtils.hasAnnotation(beanType, StarTigerAdminController.class);
    }
}
