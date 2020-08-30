package com.snake19870227.stiger.admin.manager.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/30
 */
@Component
@ConfigurationProperties(prefix = "stiger.admin")
public class StarTigerAdminProperties {

    private boolean debugMode = false;

    private Init init;

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public Init getInit() {
        return init;
    }

    public void setInit(Init init) {
        this.init = init;
    }

    public static class Init {
        private boolean multiModule;
        private String password;
        private LogoInfo logoInfo;

        public boolean isMultiModule() {
            return multiModule;
        }

        public void setMultiModule(boolean multiModule) {
            this.multiModule = multiModule;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public LogoInfo getLogoInfo() {
            return logoInfo;
        }

        public void setLogoInfo(LogoInfo logoInfo) {
            this.logoInfo = logoInfo;
        }
    }

    public static class LogoInfo {
        private String title;
        private String imageUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
