package com.snake19870227.stiger.core.exception;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/03/25
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 7160024882316484233L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "[" + this.getClass().getSimpleName() + "]" + super.getMessage();
    }
}
