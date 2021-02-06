package com.snake19870227.stiger.aliyun.dypls.event;

import org.springframework.context.ApplicationEvent;
import com.snake19870227.stiger.aliyun.dypls.entity.po.AliDyplsCall;

/**
 * @author BuHuaYang
 * 2021/2/5
 */
public class SecretReportEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param call the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public SecretReportEvent(AliDyplsCall call) {
        super(call);
    }
}
