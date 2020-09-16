package com.snake19870227.stiger.core;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/03/27
 */
@ConfigurationProperties(prefix = "stiger.frame")
public class StarTigerFrameProperties {

    private List<String> enumPackages;

    private boolean useHttpStatusCode = true;

    private boolean debugMode = false;

    public List<String> getEnumPackages() {
        return enumPackages;
    }

    public void setEnumPackages(List<String> enumPackages) {
        this.enumPackages = enumPackages;
    }

    public boolean isUseHttpStatusCode() {
        return useHttpStatusCode;
    }

    public void setUseHttpStatusCode(boolean useHttpStatusCode) {
        this.useHttpStatusCode = useHttpStatusCode;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
