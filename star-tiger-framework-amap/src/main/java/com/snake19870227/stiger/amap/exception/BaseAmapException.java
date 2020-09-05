package com.snake19870227.stiger.amap.exception;

import com.snake19870227.stiger.core.exception.BusinessException;

/**
 * @author BuHuaYang
 * 2020/9/5
 */
public abstract class BaseAmapException extends BusinessException {

    public BaseAmapException(String message) {
        super(message);
    }

    public BaseAmapException(String message, Throwable cause) {
        super(message, cause);
    }
}
