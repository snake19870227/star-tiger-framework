package com.snake19870227.stiger.sample.aliyun.dypls.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import com.snake19870227.stiger.aliyun.dypls.entity.dto.SecretReport;
import com.snake19870227.stiger.aliyun.dypls.entity.dto.SecretStartReport;
import com.snake19870227.stiger.aliyun.dypls.event.SecretReportEvent;
import com.snake19870227.stiger.aliyun.dypls.event.SecretStartReportEvent;

/**
 * @author BuHuaYang
 * 2021/2/6
 */
@Component
public class SecretReportListener implements ApplicationListener<SecretReportEvent> {

    private static final Logger logger = LoggerFactory.getLogger(SecretReportListener.class);

    @Override
    public void onApplicationEvent(SecretReportEvent event) {
        SecretReport eventSource = (SecretReport) event.getSource();
        logger.info("监听到呼叫结束:[{}][subId:{}]", event.getTimestamp(), eventSource.getSubId());
    }
}
