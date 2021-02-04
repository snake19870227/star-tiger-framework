package com.snake19870227.stiger.core.utils;

import cn.hutool.core.date.LocalDateTimeUtil;

/**
 * @author BuHuaYang
 * 2021/1/26
 */
public class FormatUtil {

    public static String transformDatetime(String source, String beforeFormatter, String afterFormatter) {
        return LocalDateTimeUtil.format(LocalDateTimeUtil.parse(source, beforeFormatter), afterFormatter);
    }
}
