package com.snake19870227.stiger.sample.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.snake19870227.stiger.aliyun.sms.server.controller.SmsAliyunReportController;
import com.snake19870227.stiger.sms.SmsClient;
import com.snake19870227.stiger.sms.server.config.EnableSmsServer;
import com.snake19870227.stiger.sms.server.controller.SmsApiController;

/**
 * @author BuHuaYang
 * 2020/9/17
 */
@SpringBootApplication
@EnableSmsServer
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        SmsClient smsClient = context.getBean(SmsClient.class);
        SmsApiController smsApiController = context.getBean(SmsApiController.class);
        SmsAliyunReportController smsAliyunReportController = context.getBean(SmsAliyunReportController.class);
        System.out.println(smsClient.getChannel());
        System.out.println(smsApiController);
        System.out.println(smsAliyunReportController);
    }
}
