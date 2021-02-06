package com.snake19870227.stiger.aliyun.dypls.event;

import org.springframework.context.ApplicationEvent;
import com.snake19870227.stiger.aliyun.dypls.entity.dto.SecretStartReport;

/**
 * @author BuHuaYang
 * 2021/2/5
 */
public class SecretStartReportEvent extends ApplicationEvent implements SecretEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param report the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public SecretStartReportEvent(SecretStartReport report) {
        super(report);
    }
}
