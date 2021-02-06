package com.snake19870227.stiger.sample.aliyun.dypls.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import com.snake19870227.stiger.aliyun.dypls.entity.dto.SecretReport;
import com.snake19870227.stiger.aliyun.dypls.entity.po.AliDyplsCall;
import com.snake19870227.stiger.aliyun.dypls.event.SecretRecordingEvent;
import com.snake19870227.stiger.aliyun.dypls.event.SecretReportEvent;

/**
 * @author BuHuaYang
 * 2021/2/6
 */
@Component
public class SecretRecordListener implements ApplicationListener<SecretRecordingEvent> {

    private static final Logger logger = LoggerFactory.getLogger(SecretRecordListener.class);

    @Override
    public void onApplicationEvent(SecretRecordingEvent event) {
        SecretRecordingEvent.SecretRecordInfo eventSource = (SecretRecordingEvent.SecretRecordInfo) event.getSource();
        AliDyplsCall call = eventSource.getCall();
        String downloadUrl = eventSource.getDownloadUrl();
        logger.info("监听到录音结束:[{}][subId:{}][url:{}]", event.getTimestamp(), call.getSubId(), downloadUrl);
    }
}
