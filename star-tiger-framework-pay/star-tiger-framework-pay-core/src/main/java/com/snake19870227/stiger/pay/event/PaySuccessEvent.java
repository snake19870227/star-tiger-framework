package com.snake19870227.stiger.pay.event;

import org.springframework.context.ApplicationEvent;
import com.snake19870227.stiger.pay.entity.po.PayTrade;

/**
 * @author BuHuaYang
 * 2020/9/4
 */
public class PaySuccessEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public PaySuccessEvent(PaySuccessEventSource source) {
        super(source);
    }

    public static class PaySuccessEventSource {

        PayTrade payTrade;

        public PaySuccessEventSource(PayTrade payTrade) {
            this.payTrade = payTrade;
        }

        public PayTrade getPayTrade() {
            return payTrade;
        }

        public void setPayTrade(PayTrade payTrade) {
            this.payTrade = payTrade;
        }
    }
}
