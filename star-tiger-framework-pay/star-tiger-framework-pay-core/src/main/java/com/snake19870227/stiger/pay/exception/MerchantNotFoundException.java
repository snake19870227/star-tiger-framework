package com.snake19870227.stiger.pay.exception;

import com.snake19870227.stiger.core.exception.BusinessException;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/28
 */
public class MerchantNotFoundException extends BusinessException {

    public MerchantNotFoundException(String bizType) {
        super("未找到业务类型对应的支付商户.[" + bizType + "]");
    }
}
