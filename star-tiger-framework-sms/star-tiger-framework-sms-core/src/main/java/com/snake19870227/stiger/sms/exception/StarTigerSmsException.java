package com.snake19870227.stiger.sms.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @author BuHuaYang
 * 2021/2/9
 */
public class StarTigerSmsException extends RuntimeException {

    private String reqId;

    private String errorCode;
    private String errorMsg;

    public StarTigerSmsException(String errorCode, String errorMsg, String reqId) {
        super(StrUtil.format("短信发送异常[{}:{}:{}]", reqId, errorCode, errorMsg));
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.reqId = reqId;
    }

    public StarTigerSmsException(String errorCode, String errorMsg, String reqId, Throwable cause) {
        super(StrUtil.format("短信发送异常[{}:{}:{}]", reqId, errorCode, errorMsg), cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.reqId = reqId;
    }

    public StarTigerSmsException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getReqId() {
        return reqId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
