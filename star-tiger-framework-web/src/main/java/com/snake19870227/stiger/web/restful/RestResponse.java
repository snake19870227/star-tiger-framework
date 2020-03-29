package com.snake19870227.stiger.web.restful;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Bu HuaYang
 */
public class RestResponse<T> {

    @ApiModelProperty(value = "结果状态码")
    private String respCode;

    @ApiModelProperty(value = "结果状态说明")
    private String respMessage;

    @ApiModelProperty(value = "结果业务对象")
    private T data;

    public RestResponse() {
    }

    public RestResponse(String respCode, String respMessage) {
        this.respCode = respCode;
        this.respMessage = respMessage;
    }

    public RestResponse(String respCode, String respMessage, T data) {
        this.respCode = respCode;
        this.respMessage = respMessage;
        this.data = data;
    }

    @Override
    public String toString() {
        return "[" + respCode + "]" + respMessage;
    }

    public static class DefaultRestResponse extends RestResponse<Object> {

        public DefaultRestResponse(String respCode, String respMessage) {
            super(respCode, respMessage);
        }

        public DefaultRestResponse(String respCode, String respMessage, Object data) {
            super(respCode, respMessage, data);
        }
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
