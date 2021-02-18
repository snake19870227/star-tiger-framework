package com.snake19870227.stiger.pay.exception;

import com.snake19870227.stiger.core.exception.BusinessException;

/**
 * @author BuHuaYang
 * 2020/10/18
 */
public class PayChannelRemoteException extends BusinessException {

    private String code;
    private String codeDes;

    public PayChannelRemoteException(String code, String codeDes) {
        super("[" + code + "]" + codeDes);
        this.code = code;
        this.codeDes = codeDes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeDes() {
        return codeDes;
    }

    public void setCodeDes(String codeDes) {
        this.codeDes = codeDes;
    }
}
