package com.snake19870227.stiger.sample.aliyun.dypls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.snake19870227.stiger.aliyun.dypls.AliyunDyplsClient;
import com.snake19870227.stiger.aliyun.dypls.config.EnableAliyunDyplsServer;
import com.snake19870227.stiger.core.context.StarTigerContext;

/**
 * @author BuHuaYang
 * 2021/2/5
 */
@SpringBootApplication
@EnableAliyunDyplsServer
public class Application implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AliyunDyplsClient aliyunDyplsClient = StarTigerContext.getBean(AliyunDyplsClient.class);
        logger.info(aliyunDyplsClient.toString());
    }
}
