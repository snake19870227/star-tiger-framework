package com.snake19870227.stiger.web.restful;

import io.swagger.annotations.ApiModelProperty;

import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.web.StarTigerWebConstant;
import com.snake19870227.stiger.web.exception.BaseControllerException;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/07/24
 */
public class RestResp<T> {

    @ApiModelProperty(value = "结果状态码")
    private String code;

    @ApiModelProperty(value = "结果状态说明")
    private String msg;

    @ApiModelProperty(value = "结果业务对象")
    private T data;

    public static RestResp<?> ok() {
        String msg = StarTigerContext.getMessage(StarTigerWebConstant.StatusCode.STATUS_CODE_0000);
        return createResp(StarTigerWebConstant.StatusCode.CODE_0000, msg, null);
    }

    public static <T> RestResp<T> ok(String message) {
        return createResp(StarTigerWebConstant.StatusCode.CODE_0000, message, null);
    }

    public static <T> RestResp<T> okWithData(T data) {
        String msg = StarTigerContext.getMessage(StarTigerWebConstant.StatusCode.STATUS_CODE_0000);
        return createResp(StarTigerWebConstant.StatusCode.CODE_0000, msg, data);
    }

    public static <T> RestResp<T> okByCode(String codeNo) {
        String msg = StarTigerContext.getMessage(StarTigerWebConstant.StatusCode.PREFIX_CODE + codeNo);
        return createResp(codeNo, msg, null);
    }

    public static <T> RestResp<T> okByCode(String prefix, String codeNo) {
        String msg = StarTigerContext.getMessage(prefix + codeNo);
        return createResp(codeNo, msg, null);
    }

    public static RestResp<?> failure() {
        String msg = StarTigerContext.getMessage(StarTigerWebConstant.StatusCode.STATUS_CODE_9999);
        return createResp(StarTigerWebConstant.StatusCode.CODE_9999, msg, null);
    }

    public static RestResp<?> failure(String message) {
        String msg = StarTigerContext.getMessage(StarTigerWebConstant.StatusCode.STATUS_CODE_9998, message);
        return createResp(StarTigerWebConstant.StatusCode.CODE_9998, msg, null);
    }

    public static RestResp<?> failure(BaseControllerException e) {
        return createResp(e.getErrorCode(), e.getMessage(), null);
    }

    public static RestResp<?> failure(Exception e) {
        if (e instanceof BaseControllerException) {
            return failure(((BaseControllerException) e));
        } else {
            String msg = StarTigerContext.getMessage(StarTigerWebConstant.StatusCode.STATUS_CODE_9998, e.getMessage());
            return createResp(StarTigerWebConstant.StatusCode.STATUS_CODE_9998, msg, null);
        }
    }

    @Deprecated
    public static <T> RestResp<T> buildResp(String code, T data) {
        String msg = StarTigerContext.getMessage(StarTigerWebConstant.StatusCode.PREFIX_CODE + code);
        return createResp(code, msg, data);
    }

    @Deprecated
    public static <T> RestResp<T> buildResp(String code, T data, Object... placeholders) {
        String msg = StarTigerContext.getMessage(StarTigerWebConstant.StatusCode.PREFIX_CODE + code, placeholders);
        return createResp(code, msg, data);
    }

    @Deprecated
    public static <T> RestResp<T> buildResp(String code, Exception ex) {
        String msg;
        if (ex != null) {
            msg = StarTigerContext.getMessage(StarTigerWebConstant.StatusCode.PREFIX_CODE + code, ex.getMessage());
        } else {
            msg = StarTigerContext.getMessage(StarTigerWebConstant.StatusCode.PREFIX_CODE + code);
        }
        return createResp(code, msg, null);
    }

    @Deprecated
    public static <T> RestResp<T> buildResp(String code, String msg) {
        return createResp(code, msg, null);
    }

    @Deprecated
    public static <T> RestResp<T> buildResp(String code) {
        String msg = StarTigerContext.getMessage(StarTigerWebConstant.StatusCode.PREFIX_CODE + code);
        return createResp(code, msg, null);
    }

    public static <T> RestResp<T> createResp(String code, String msg, T data) {
        RestResp<T> resp = new RestResp<>();
        resp.setCode(code);
        resp.setMsg(msg);
        resp.setData(data);
        return resp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
