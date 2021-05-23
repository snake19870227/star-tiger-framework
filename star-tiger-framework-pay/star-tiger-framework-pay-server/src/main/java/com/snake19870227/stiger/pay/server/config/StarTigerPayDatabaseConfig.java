package com.snake19870227.stiger.pay.server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;

/**
 * @author BuHuaYang
 * 2021/2/14
 */
@Configuration
@MapperScan(basePackages = {
        "com.snake19870227.stiger.admin.dao",
        "com.snake19870227.stiger.pay.dao"
})
public class StarTigerPayDatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerPayDatabaseConfig.class);

    public StarTigerPayDatabaseConfig() {
        logger.info("实例化配置类：" + this.getClass().getName());
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setOverflow(false);
        paginationInterceptor.setLimit(500);
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
