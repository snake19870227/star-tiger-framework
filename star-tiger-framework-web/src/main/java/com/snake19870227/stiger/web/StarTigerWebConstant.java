package com.snake19870227.stiger.web;

/**
 * @author Bu HuaYang
 */
public class StarTigerWebConstant {

    public static class StatusCode {

        public static final String PREFIX_CODE = "code.";

        public static final String CODE_0000 = "0000";
        public static final String STATUS_CODE_0000 = PREFIX_CODE + CODE_0000;

        public static final String CODE_9999 = "9999";
        public static final String STATUS_CODE_9999 = PREFIX_CODE + CODE_9999;

        public static final String CODE_9998 = "9998";
        public static final String STATUS_CODE_9998 = PREFIX_CODE + CODE_9998;
    }

    public static class ViewName {
        public static final String ERROR_500 = "error/500";
        public static final String ERROR_404 = "error/404";
        public static final String ERROR_400 = "error/400";
        public static final String ERROR_403 = "error/403";
        public static final String ERROR_401 = "error/401";
    }

    public static class ViewAttrKey {
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String ERROR_DETAIL_MESSAGES = "errorDetailMessages";
        public static final String ACCESS_DENIED_URL = "accessDeniedUrl";
    }

    public static class WebAttrKey {
        public static final String DEBUG_MODE = "debugMode";
        public static final String ACTIVE_PROFILES = "activeProfiles";
        public static final String PROJECT_VERSION = "projectVersion";
    }
}
