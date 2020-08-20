package com.snake19870227.stiger.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/20
 */
@SpringBootApplication(
        scanBasePackages = {
                "com.snake19870227.stiger.admin"
        }
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
