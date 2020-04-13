package com.snake19870227.stiger.autoconfigure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.snake19870227.stiger.core.log.MethodLogAspect;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/04/13
 */
@Configuration
public class StarTigerLogAutoConfiguration {

    @Bean
    public MethodLogAspect methodLogAspect() {
        return new MethodLogAspect();
    }
}
