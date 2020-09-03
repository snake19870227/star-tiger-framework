package com.snake19870227.stiger.sms.aliyun.exception;

import com.snake19870227.stiger.core.exception.BusinessException;

/**
 * @author BuHuaYang
 * 2020/9/3
 */
public class StarTigerSmsException extends BusinessException {

    public StarTigerSmsException(String message) {
        super(message);
    }

    public StarTigerSmsException(String message, Throwable cause) {
        super(message, cause);
    }
}
