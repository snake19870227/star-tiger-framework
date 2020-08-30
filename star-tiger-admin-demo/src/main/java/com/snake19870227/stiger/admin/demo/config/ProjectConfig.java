package com.snake19870227.stiger.admin.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/30
 */
@Configuration
@AutoConfigureAfter(MybatisPlusAutoConfiguration.class)
@MapperScan(basePackages = {
        "com.snake19870227.stiger.admin.dao"
})
@EnableTransactionManagement(proxyTargetClass = true)
public class ProjectConfig {
}
