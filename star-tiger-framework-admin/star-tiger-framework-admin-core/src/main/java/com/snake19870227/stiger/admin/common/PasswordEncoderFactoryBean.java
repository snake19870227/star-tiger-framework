package com.snake19870227.stiger.admin.common;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/30
 */
@Component
public class PasswordEncoderFactoryBean implements FactoryBean<PasswordEncoder> {

    @Override
    public PasswordEncoder getObject() throws Exception {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public Class<?> getObjectType() {
        return PasswordEncoder.class;
    }
}
