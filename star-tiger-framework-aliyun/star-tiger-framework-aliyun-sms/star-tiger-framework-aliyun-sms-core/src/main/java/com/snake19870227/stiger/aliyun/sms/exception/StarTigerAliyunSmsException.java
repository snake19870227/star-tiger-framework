package com.snake19870227.stiger.aliyun.sms.exception;

import com.aliyuncs.exceptions.ClientException;
import com.snake19870227.stiger.sms.exception.StarTigerSmsException;

/**
 * @author BuHuaYang
 * 2021/2/9
 */
public class StarTigerAliyunSmsException extends StarTigerSmsException {

    public StarTigerAliyunSmsException(ClientException e) {
        super(e.getErrCode(), e.getErrMsg(), e.getRequestId(), e);
    }
}
