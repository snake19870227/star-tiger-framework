package com.snake19870227.stiger.sms.server.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author BuHuaYang
 * 2021/2/10
 */
public class SmsSendTrigger {

    @Autowired
    private SmsSender smsSender;

    @Scheduled(cron = "0/5 * * * * ?")
    public void sendSms() {
        smsSender.doSend();
    }
}
