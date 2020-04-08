package com.snake19870227.stiger.core.exception;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/03/17
 */
public class OptException extends BaseBusinessException {

    private static final long serialVersionUID = -4200148175517878694L;

    public OptException(String message) {
        super(message);
    }

    public OptException(String message, Throwable cause) {
        super(message, cause);
    }

    public OptException(Throwable cause) {
        super(cause);
    }
}
