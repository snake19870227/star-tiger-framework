package com.snake19870227.stiger.web.exception;

/**
 * @author Bu HuaYang
 */
public class MvcException extends BaseControllerException {

    private static final long serialVersionUID = 5959317727556640504L;

    public MvcException(String errorCode, Object... args) {
        super(errorCode, args);
    }

    public MvcException(String errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public MvcException(String errorCode) {
        super(errorCode);
    }

    public MvcException(Throwable cause) {
        super(cause);
    }
}
