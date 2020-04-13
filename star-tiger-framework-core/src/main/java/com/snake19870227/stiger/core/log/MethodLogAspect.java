package com.snake19870227.stiger.core.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.snake19870227.stiger.core.context.StarTigerContext;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/04/13
 */
@Aspect
public class MethodLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(MethodLogAspect.class);

    @Around("@annotation(methodLog)")
    public Object around(ProceedingJoinPoint point, MethodLog methodLog) throws Throwable {

        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();

        logger.debug("执行'{}' - {}.{}", methodLog.value(), className, methodName);

        Long startTime = System.currentTimeMillis();
        Object obj = point.proceed();
        Long endTime = System.currentTimeMillis();

        Long executeTime = endTime - startTime;

        StarTigerContext.publishEvent(new MethodLogEvent(new MethodLogEvent.MethodLogEventSource(point, methodLog, executeTime)));

        return obj;
    }
}
