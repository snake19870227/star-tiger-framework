package com.snake19870227.stiger.oss;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author BuHuaYang
 * 2020/9/2
 */
@ConfigurationProperties(prefix = "stiger.oss")
public class StarTigerOssProperties {

    private Minio minio;

    public Minio getMinio() {
        return minio;
    }

    public void setMinio(Minio minio) {
        this.minio = minio;
    }

    public static class Minio {
        private boolean active;
        private String endpoint;
        private String accessKey;
        private String secretKey;

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }
    }
}
