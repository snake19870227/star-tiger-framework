package com.snake19870227.stiger.core.utils;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author BuHuaYang
 * 2020/11/6
 */
public class DateTimeUtil {

    public static final long SENCONDS_OF_MINUTE = 60;

    public static final long SENCONDS_OF_HOUR = SENCONDS_OF_MINUTE * 60;

    public static final long SENCONDS_OF_DAY = SENCONDS_OF_HOUR * 24;

    public static final long SENCONDS_OF_YEAR = SENCONDS_OF_DAY * 365;

    /**
     * 获取一个日期时间当对于当前时间的时刻<br>
     * 1.当前时间 2020-10-10 10:10:10，传入时间 2020-10-10 10:11:8，则返回"刚刚"
     * @param dateTime 传入时间
     * @param pattern 传入时间格式
     * @return 时刻
     */
    public static String getPastMoment(String dateTime, String pattern) {
        return getPastMoment(dateTime, pattern, pattern);
    }

    public static String getPastMoment(String dateTime, String pattern, String showPattern) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime past = LocalDateTimeUtil.parse(dateTime, pattern);
        Duration between = LocalDateTimeUtil.between(past, now);
        if (between.isNegative()) {
            throw new IllegalArgumentException("不可晚于当前时间");
        }
        long seconds = between.getSeconds();
        if (seconds > 0 && seconds <= SENCONDS_OF_MINUTE) {
            return "刚刚";
        }
        if (seconds > SENCONDS_OF_MINUTE && seconds <= SENCONDS_OF_HOUR) {
            long m = seconds / 60;
            return m + "分钟前";
        }
        if (seconds > SENCONDS_OF_HOUR && seconds <= SENCONDS_OF_DAY) {
            long h = seconds / (60 * 60);
            long s = seconds % (60 * 60);
            long m = s / 60;
            return h + "小时" + m + "分钟前";
        }
        if (seconds > SENCONDS_OF_DAY && seconds <= SENCONDS_OF_YEAR) {
            long d = seconds / (60 * 60 * 24);
            return d + "天前";
        }
        if (seconds > SENCONDS_OF_YEAR) {
            return LocalDateTimeUtil.format(past, showPattern);
        }
        return "";
    }
}
