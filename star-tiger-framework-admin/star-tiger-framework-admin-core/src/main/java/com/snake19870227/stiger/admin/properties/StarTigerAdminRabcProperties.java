package com.snake19870227.stiger.admin.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author BuHuaYang
 * 2020/9/16
 */
@ConfigurationProperties(prefix = "stiger.admin.rabc")
public class StarTigerAdminRabcProperties {

    private String initialPassword = "654321";

    private DefaultUsers defaultUsers;

    public StarTigerAdminRabcProperties() {
        this.defaultUsers = new DefaultUsers();
    }

    public String getInitialPassword() {
        return initialPassword;
    }

    public void setInitialPassword(String initialPassword) {
        this.initialPassword = initialPassword;
    }

    public DefaultUsers getDefaultUsers() {
        return defaultUsers;
    }

    public void setDefaultUsers(DefaultUsers defaultUsers) {
        this.defaultUsers = defaultUsers;
    }

    public static class DefaultUsers {

        private String superAdminUsername = "root";
        private String superAdminPassword = "root123456";

        private String actuatorAdminUsername = "actuator";
        private String actuatorAdminPassword = "actuator123456";

        public String getSuperAdminUsername() {
            return superAdminUsername;
        }

        public void setSuperAdminUsername(String superAdminUsername) {
            this.superAdminUsername = superAdminUsername;
        }

        public String getSuperAdminPassword() {
            return superAdminPassword;
        }

        public void setSuperAdminPassword(String superAdminPassword) {
            this.superAdminPassword = superAdminPassword;
        }

        public String getActuatorAdminUsername() {
            return actuatorAdminUsername;
        }

        public void setActuatorAdminUsername(String actuatorAdminUsername) {
            this.actuatorAdminUsername = actuatorAdminUsername;
        }

        public String getActuatorAdminPassword() {
            return actuatorAdminPassword;
        }

        public void setActuatorAdminPassword(String actuatorAdminPassword) {
            this.actuatorAdminPassword = actuatorAdminPassword;
        }
    }
}
