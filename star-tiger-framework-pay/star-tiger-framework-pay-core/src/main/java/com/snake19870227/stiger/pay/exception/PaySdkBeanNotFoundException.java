package com.snake19870227.stiger.pay.exception;

import com.snake19870227.stiger.core.exception.BusinessException;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/28
 */
public class PaySdkBeanNotFoundException extends BusinessException {

    public PaySdkBeanNotFoundException(String beanName) {
        super("未找到支付SDK对应的bean.[" + beanName + "]");
    }

    public PaySdkBeanNotFoundException(String beanName, Throwable cause) {
        super("未找到支付SDK对应的bean.[" + beanName + "]", cause);
    }
}
