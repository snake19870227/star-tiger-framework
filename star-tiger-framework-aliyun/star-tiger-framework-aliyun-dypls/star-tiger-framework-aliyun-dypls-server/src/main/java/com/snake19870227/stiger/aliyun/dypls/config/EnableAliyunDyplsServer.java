package com.snake19870227.stiger.aliyun.dypls.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * @author BuHuaYang
 * 2021/2/4
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(StarTigerAliDyplsServerMarkerConfig.class)
public @interface EnableAliyunDyplsServer {
}
