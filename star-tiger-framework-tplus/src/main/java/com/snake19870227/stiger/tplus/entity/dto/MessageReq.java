package com.snake19870227.stiger.tplus.entity.dto;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

import java.nio.charset.StandardCharsets;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2021/04/30
 */
public class MessageReq {

    private String encryptMsg;

    public String toClearText(String ifaceSecret) {
        AES aes = SecureUtil.aes(ifaceSecret.getBytes(StandardCharsets.UTF_8));
        return aes.decryptStr(getEncryptMsg());
    }

    public String getEncryptMsg() {
        return encryptMsg;
    }

    public void setEncryptMsg(String encryptMsg) {
        this.encryptMsg = encryptMsg;
    }
}
