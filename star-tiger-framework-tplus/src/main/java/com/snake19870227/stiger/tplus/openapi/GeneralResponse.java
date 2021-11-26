package com.snake19870227.stiger.tplus.openapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import com.snake19870227.stiger.core.utils.JsonUtil;

/**
 * @author BuHuaYang
 * 8/6 006
 */
public class GeneralResponse<T> {

    private String code;

    private String message;

    private T result;

    public static <M> GeneralResponse<M> parse(String responseBody, Class<M> resultClass) throws Exception {

        ObjectMapper objectMapper = JsonUtil.buildJacksonObjectMapper();

        JsonNode bodyNode = objectMapper.readTree(responseBody);

        JsonNode codeNode = bodyNode.get("code");
        JsonNode messageNode = bodyNode.get("message");
        String code = "", message = "";
        if (codeNode == null) {
            throw new RuntimeException("无效的畅捷通响应[未找到'code']");
        }
        if (codeNode.isInt()) {
            code = String.valueOf(codeNode.intValue());
        } else if (codeNode.isLong()) {
            code = String.valueOf(codeNode.longValue());
        } else {
            code = codeNode.textValue();
        }
        if (messageNode != null) {
            message = messageNode.textValue();
        }

        GeneralResponse<M> response = new GeneralResponse<>();
        response.setCode(code);
        response.setMessage(message);

        if (resultClass != null) {
            JsonNode resultNode = bodyNode.get("result");
            if (resultNode != null && !(resultNode instanceof NullNode)) {
                M result;
                if (resultClass == String.class) {
                    String resultString = resultNode.textValue();
                    result = resultClass.getConstructor(String.class).newInstance(resultString);
                } else if (resultNode.isObject()) {
                    String resultString = resultNode.toString();
                    result = objectMapper.readValue(resultString, resultClass);
                } else {
                    throw new RuntimeException("无效的畅捷通响应[无法解析'result']");
                }
                response.setResult(result);
            }
        }

        return response;
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
