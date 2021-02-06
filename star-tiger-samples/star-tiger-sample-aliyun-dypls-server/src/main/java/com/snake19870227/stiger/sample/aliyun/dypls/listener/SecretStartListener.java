package com.snake19870227.stiger.sample.aliyun.dypls.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import com.snake19870227.stiger.aliyun.dypls.entity.dto.SecretStartReport;
import com.snake19870227.stiger.aliyun.dypls.event.SecretStartReportEvent;

/**
 * @author BuHuaYang
 * 2021/2/6
 */
@Component
public class SecretStartListener implements ApplicationListener<SecretStartReportEvent> {

    private static final Logger logger = LoggerFactory.getLogger(SecretStartListener.class);

    @Override
    public void onApplicationEvent(SecretStartReportEvent event) {
        SecretStartReport eventSource = (SecretStartReport) event.getSource();
        logger.info("监听到开始呼叫:[{}][subId:{}]", event.getTimestamp(), eventSource.getSubId());
    }
}
