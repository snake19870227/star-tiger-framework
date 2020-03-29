package com.snake19870227.stiger.core.context;

import cn.hutool.core.util.ArrayUtil;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.core.utils.JsonUtil;

/**
 * @author Bu HuaYang
 */
public class StarTigerContext {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerContext.class);

    private static String[] activeProfiles;
    private static String applicationName;
    private static String applicationVersion;

    private static ApplicationContext springContext;

    protected static ApplicationContext getSpringContext() {
        return springContext;
    }

    public static Environment getEnvironment() {
        return getSpringContext().getEnvironment();
    }

    public static String getApplicationName() {
        return applicationName;
    }

    public static String getApplicationId() {
        return applicationName + "-" + springContext.getId();
    }

    public static String[] getActiveProfiles() {
        return activeProfiles;
    }

    public static boolean isProfileActived(String... profileNames) {
        if (ArrayUtil.isEmpty(activeProfiles)) {
            return false;
        }
        return ArrayUtil.containsAny(activeProfiles, profileNames);
    }

    public static String getMessage(String code) {
        return springContext.getMessage(code, null, Locale.CHINA);
    }

    public static String getMessage(String code, Object... args) {
        return springContext.getMessage(code, args, Locale.CHINA);
    }

    public static <T> T getBean(Class<? extends T> beanClass) throws BeansException {
        return springContext.getBean(beanClass);
    }

    public static <T> T getBean(String beanName, Class<? extends T> beanClass) throws BeansException {
        return springContext.getBean(beanName, beanClass);
    }

    public static void setApplicationName(String applicationName) {
        StarTigerContext.applicationName = applicationName;
    }

    public static void setApplicationVersion(String applicationVersion) {
        StarTigerContext.applicationVersion = applicationVersion;
    }

    public static void setSpringContext(ApplicationContext springContext) {
        StarTigerContext.springContext = springContext;
        StarTigerContext.activeProfiles = springContext.getEnvironment().getActiveProfiles();
    }

    public static ObjectMapper getJsonMapper() {
        ObjectMapper objectMapper;
        try {
            objectMapper = springContext.getBean(ObjectMapper.class);
        } catch (BeansException e) {
            logger.warn("系统上下文中未找到[{}]", ObjectMapper.class.getName());
            objectMapper = JsonUtil.buildJacksonObjectMapper();
        }

        return objectMapper;
    }
}
