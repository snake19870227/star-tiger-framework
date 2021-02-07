package com.snake19870227.stiger.aliyun.sms;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.snake19870227.stiger.sms.SmsRespnoseHandler;

/**
 * @author BuHuaYang
 * 2021/1/5
 */
public class AliyunSmsResponse<T> {

    public static final String SUCCESS_CODE = "OK";

    private JSONObject r;

    private boolean success;

    private String code;

    private String message;

    private String requestId;

    private T data;

    public AliyunSmsResponse(String respStr) {
        this.r = JSONUtil.parseObj(respStr);
        this.code = r.getStr("Code");
        this.message = r.getStr("Message");
        this.requestId = r.getStr("RequestId");
        this.success = StrUtil.equalsIgnoreCase(SUCCESS_CODE, this.code);
    }

    public AliyunSmsResponse(String respStr, SmsRespnoseHandler<T> handler) {
        this(respStr);
        if (this.success) {
            this.data = handler.handler(r);
        }
    }

    public JSONObject getR() {
        return r;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
