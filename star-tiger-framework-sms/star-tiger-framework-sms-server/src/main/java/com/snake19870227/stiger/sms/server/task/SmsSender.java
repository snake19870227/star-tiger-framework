package com.snake19870227.stiger.sms.server.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.sms.SmsClient;
import com.snake19870227.stiger.sms.entity.po.SmsLog;
import com.snake19870227.stiger.sms.service.ISmsLogService;

/**
 * @author BuHuaYang
 * 2021/2/9
 */
public class SmsSender {

    private static final Logger logger = LoggerFactory.getLogger(SmsSender.class);

    @Autowired
    private SmsClient smsClient;

    @Autowired
    private ISmsLogService smsLogService;

    public void doSend() {
        QueryWrapper<SmsLog> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0)
                .eq("channel", smsClient.getChannel())
                .orderByAsc("create_datetime");
        List<SmsLog> list = smsLogService.list(wrapper);
        logger.info("待发送短信:{}条", list.size());
        for (SmsLog smsLog : list) {
            smsClient.sendSms(smsLog);
            smsLogService.updateById(smsLog);
        }
    }
}
