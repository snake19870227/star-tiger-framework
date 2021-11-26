package com.snake19870227.stiger.tplus.server.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2021/04/30
 */
@SpringBootApplication(
        scanBasePackages = {
                "com.hepingdairy.tplus"
        }
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
