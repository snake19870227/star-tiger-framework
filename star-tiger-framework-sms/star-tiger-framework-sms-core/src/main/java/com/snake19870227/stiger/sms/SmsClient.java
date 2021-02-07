package com.snake19870227.stiger.sms;

import com.snake19870227.stiger.sms.entity.po.SmsLog;
import com.snake19870227.stiger.sms.entity.po.SmsTemplate;

/**
 * @author BuHuaYang
 * 2021/1/2
 */
public interface SmsClient {

    void sendSms(SmsLog smsLog);

    SmsTemplate queryTemplate(String templateCode);

    String getChannel();
}
