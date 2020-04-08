package com.snake19870227.stiger.web.context;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;
import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.web.StarTigerWebConstant;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/03/13
 */
public class StarTigerWebContext {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerWebContext.class);

    private static ServletContext servletContext;

    private static ServerProperties serverProperties;

    public static void setSpringContext(ApplicationContext springContext) {
        serverProperties = springContext.getBean(ServerProperties.class);
        Environment env = springContext.getEnvironment();
        try {
            Optional<ServletContext> scObj = Optional.ofNullable(((WebApplicationContext) springContext).getServletContext());
            servletContext = scObj.orElseThrow((Supplier<Throwable>) () -> new NullPointerException("SpringContext 中未包含 ServletContext..."));
            servletContext.setAttribute(StarTigerWebConstant.WebAttrKey.ACTIVE_PROFILES, StarTigerContext.getActiveProfiles());
            servletContext.setAttribute(StarTigerWebConstant.WebAttrKey.PROJECT_VERSION, env.getProperty("stiger.admin.web.version"));
        } catch (Throwable e) {
            logger.error("未能获取到 ServletContext ! 终止程序...");
            System.exit(1);
        }
    }

    /**
     * 读取cookie
     *
     * @param name cookie name
     * @return cookie value
     */
    public static String getCookieVal(String name) {
        HttpServletRequest request = getRequest();
        Assert.notNull(request, "request from RequestContextHolder is null");
        return getCookieVal(request, name);
    }

    /**
     * 读取cookie
     *
     * @param request HttpServletRequest
     * @param name    cookie name
     * @return cookie value
     */
    public static String getCookieVal(HttpServletRequest request, String name) {
        Cookie cookie = WebUtils.getCookie(request, name);
        return cookie != null ? cookie.getValue() : null;
    }

    /**
     * 清除 某个指定的cookie
     *
     * @param response HttpServletResponse
     * @param key      cookie key
     */
    public static void removeCookie(HttpServletResponse response, String key) {
        setCookie(response, key, null, 0);
    }

    /**
     * 设置cookie
     *
     * @param response        HttpServletResponse
     * @param name            cookie name
     * @param value           cookie value
     * @param maxAgeInSeconds maxage
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAgeInSeconds);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    /**
     * 获取 HttpServletRequest
     *
     * @return {HttpServletRequest}
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取 HttpServletResponse
     *
     * @return {HttpServletResponse}
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
