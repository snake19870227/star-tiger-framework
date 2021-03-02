package com.snake19870227.stiger.sample.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.snake19870227.stiger.pay.server.config.EnablePayServer;

/**
 * @author BuHuaYang
 * 2021/3/2
 */
@SpringBootApplication
@EnablePayServer
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
