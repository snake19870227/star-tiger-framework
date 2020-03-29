package com.snake19870227.stiger.web;

/**
 * @author Bu HuaYang
 */
public class StarTigerWebConstant {

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
        public static final String ACTIVE_PROFILES = "activeProfiles";
        public static final String PROJECT_VERSION = "projectVersion";
    }
}
