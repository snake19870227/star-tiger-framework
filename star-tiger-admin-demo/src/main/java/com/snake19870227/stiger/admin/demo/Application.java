package com.snake19870227.stiger.admin.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.snake19870227.stiger.admin.autoconfigure.annotation.EnableStarTigerAdminLayui;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/30
 */
@SpringBootApplication(
        scanBasePackages = {
                "com.snake19870227.stiger"
        }
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
