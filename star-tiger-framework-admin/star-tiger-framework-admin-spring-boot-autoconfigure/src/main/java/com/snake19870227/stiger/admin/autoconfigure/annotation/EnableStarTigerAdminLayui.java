package com.snake19870227.stiger.admin.autoconfigure.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import com.snake19870227.stiger.admin.autoconfigure.StarTigerAdminAutoConfiguration;
import com.snake19870227.stiger.admin.autoconfigure.StarTigerAdminLayuiManangerAutoConfiguration;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/03/29
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
        StarTigerAdminAutoConfiguration.class,
        StarTigerAdminLayuiManangerAutoConfiguration.class
})
public @interface EnableStarTigerAdminLayui {
}
