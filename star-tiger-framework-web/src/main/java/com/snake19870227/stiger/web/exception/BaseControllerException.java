package com.snake19870227.stiger.web.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.core.exception.BaseBusinessException;
import com.snake19870227.stiger.web.StarTigerWebConstant;

/**
 * @author Bu HuaYang
 */
public abstract class BaseControllerException extends RuntimeException {

    private static final long serialVersionUID = -777186496884353814L;

    protected String errorCode;

    public BaseControllerException(String errorCode, Object... args) {
        super(StarTigerContext.getMessage(StarTigerConstant.StatusCode.PREFIX_CODE + errorCode, args));
        this.errorCode = errorCode;
    }

    public BaseControllerException(String errorCode, Throwable cause, Object... args) {
        super(StarTigerContext.getMessage(StarTigerConstant.StatusCode.PREFIX_CODE + errorCode, args), cause);
        this.errorCode = errorCode;
    }

    public BaseControllerException(String message) {
        super(message);
        this.errorCode = StarTigerConstant.StatusCode.CODE_9998;
    }

    public BaseControllerException(Throwable cause) {
        super(cause);
        if (BaseBusinessException.class.isAssignableFrom(cause.getClass())) {
            this.errorCode = StarTigerConstant.StatusCode.CODE_9998;
        } else {
            this.errorCode = StarTigerConstant.StatusCode.CODE_9999;
        }
    }

    @Override
    public String getMessage() {
        List<String> causeMessages = new ArrayList<>();
        loadBusinessCauseMessages(causeMessages, getCause());
        StringBuilder message = new StringBuilder();
        message.append(currentMessage());
        if (!causeMessages.isEmpty()) {
            causeMessages.forEach(causeMessage -> message.append(";").append(causeMessage));
        }
        return message.toString();
    }

    public String getErrorCode() {
        return errorCode;
    }

    private String currentMessage() {
        return "[" + errorCode + "]" + super.getMessage();
    }

    public Map<String, Object> getModel() {
        Map<String, Object> model = new HashMap<>(1);
        model.put(StarTigerWebConstant.ViewAttrKey.ERROR_MESSAGE, currentMessage());
        List<String> causeMessages = new ArrayList<>();
        loadBusinessCauseMessages(causeMessages, getCause());
        model.put(StarTigerWebConstant.ViewAttrKey.ERROR_DETAIL_MESSAGES, causeMessages);
        return model;
    }

    private void loadBusinessCauseMessages(List<String> causeMessages, Throwable t) {
        if (t instanceof BaseBusinessException) {
            causeMessages.add(t.getMessage());
            if (t.getCause() != null) {
                loadBusinessCauseMessages(causeMessages, t.getCause());
            }
        }
    }
}
