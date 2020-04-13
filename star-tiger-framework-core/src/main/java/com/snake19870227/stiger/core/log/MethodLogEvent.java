package com.snake19870227.stiger.core.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.ApplicationEvent;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/04/13
 */
public class MethodLogEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MethodLogEvent(MethodLogEventSource source) {
        super(source);
    }

    public static class MethodLogEventSource {

        ProceedingJoinPoint point;

        MethodLog methodLog;

        Long executeTime;

        public MethodLogEventSource(ProceedingJoinPoint point, MethodLog methodLog, Long executeTime) {
            this.point = point;
            this.methodLog = methodLog;
            this.executeTime = executeTime;
        }

        public ProceedingJoinPoint getPoint() {
            return point;
        }

        public void setPoint(ProceedingJoinPoint point) {
            this.point = point;
        }

        public MethodLog getMethodLog() {
            return methodLog;
        }

        public void setMethodLog(MethodLog methodLog) {
            this.methodLog = methodLog;
        }

        public Long getExecuteTime() {
            return executeTime;
        }

        public void setExecuteTime(Long executeTime) {
            this.executeTime = executeTime;
        }
    }
}
