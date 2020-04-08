package com.snake19870227.stiger.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/03/27
 */
@ConfigurationProperties(prefix = "stiger.frame")
public class StarTigerFrameProperties {

    private boolean useHttpStatusCode = true;

    public boolean isUseHttpStatusCode() {
        return useHttpStatusCode;
    }

    public void setUseHttpStatusCode(boolean useHttpStatusCode) {
        this.useHttpStatusCode = useHttpStatusCode;
    }
}
