package com.snake19870227.stiger.pay.exception;

/**
 * @author BuHuaYang
 * 2020/10/18
 */
public class PayChannelRemoteTradeNotExistException extends PayChannelRemoteException {

    public PayChannelRemoteTradeNotExistException(String code, String codeDes) {
        super(code, codeDes);
    }
}
