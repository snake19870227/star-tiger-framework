package com.snake19870227.stiger.amap.webapi.exception;

import com.snake19870227.stiger.amap.exception.BaseAmapException;

/**
 * @author BuHuaYang
 * 2020/9/5
 */
public class WebApiException extends BaseAmapException {

    public WebApiException(String message) {
        super(message);
    }

    public WebApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
