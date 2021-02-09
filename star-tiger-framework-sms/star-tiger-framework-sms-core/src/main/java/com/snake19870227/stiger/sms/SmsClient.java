package com.snake19870227.stiger.sms;

import java.util.Map;

import com.snake19870227.stiger.sms.entity.po.SmsLog;
import com.snake19870227.stiger.sms.entity.po.SmsTemplate;
import com.snake19870227.stiger.sms.exception.StarTigerSmsException;

/**
 * @author BuHuaYang
 * 2021/1/2
 */
public interface SmsClient {

    void sendSms(SmsLog smsLog);

    SmsLog sendSms(String phoneNumber, String signName, String params, String templateCode) throws StarTigerSmsException;

    SmsLog sendSms(String phoneNumber, String signName, Map<String, Object> params, String templateCode) throws StarTigerSmsException;

    SmsTemplate queryTemplate(String templateCode);

    String getChannel();
}
