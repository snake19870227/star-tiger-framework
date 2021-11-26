package com.snake19870227.stiger.tplus.util;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2021/05/06
 */
public class TimestampUtil {

    public static LocalDateTime toLocalDateTime(long timestamp) {
        return LocalDateTimeUtil.of(timestamp, TimeZone.getTimeZone(ZoneId.systemDefault()));
    }

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTimeUtil.of(1603877707372L, TimeZone.getTimeZone(ZoneId.systemDefault()));
        System.out.println(localDateTime);
    }
}
