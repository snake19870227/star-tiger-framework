package com.snake19870227.stiger.web.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Bu HuaYang
 */
public class WebUtil {

    public static final String AJAX_HEADER_NAME = "X-Requested-With";
    public static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return StrUtil.equals(AJAX_HEADER_VALUE, ServletUtil.getHeader(request, AJAX_HEADER_NAME, StandardCharsets.UTF_8));
    }

    public static String getPath(HttpServletRequest request, boolean withContextPath, boolean withUrlParams) {
        StringBuilder path = new StringBuilder();
        if (withContextPath) {
            path.append(request.getContextPath());
        }
        path.append(request.getServletPath());
        if (withUrlParams) {
            path.append("?").append(request.getQueryString());
        }
        return path.toString();
    }

    public static boolean matches(HttpServletRequest request, String pattern, String httpMethod) {
        AntPathRequestMatcher pathMatcher;
        if (StrUtil.isNotBlank(httpMethod)) {
            pathMatcher = new AntPathRequestMatcher(pattern, httpMethod);
        } else {
            pathMatcher = new AntPathRequestMatcher(pattern);
        }
        return pathMatcher.matches(request);
    }
}
