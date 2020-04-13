package com.snake19870227.stiger.core.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/04/13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodLog {

    String value() default "";

    boolean print() default true;

    boolean pushEvent() default true;
}
