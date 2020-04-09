package com.snake19870227.stiger.web.restful;

import cn.hutool.core.util.ObjectUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.web.exception.BaseControllerException;

/**
 * @author Bu HuaYang
 */
public class RestResponseBuilder {

    private static final Logger logger = LoggerFactory.getLogger(RestResponseBuilder.class);

    public static final String DEFAULT_SUCCESS_RESP_CODE = StarTigerConstant.StatusCode.CODE_0000;

    public static final String DEFAULT_FAILURE_RESP_CODE = StarTigerConstant.StatusCode.CODE_9999;

    public static final String DEFAULT_EXCEPTION_RESP_CODE = StarTigerConstant.StatusCode.CODE_9998;

    public static RestResponse.DefaultRestResponse createSuccessDefaultRestResp() {
        return createDefaultRestResp(true, DEFAULT_SUCCESS_RESP_CODE, null);
    }

    public static RestResponse.DefaultRestResponse createFailureDefaultRestResp() {
        return createDefaultRestResp(false, DEFAULT_FAILURE_RESP_CODE, null);
    }

    public static RestResponse.DefaultRestResponse createSuccessDefaultRestResp(Object data) {
        return createDefaultRestResp(true, DEFAULT_SUCCESS_RESP_CODE, data);
    }

    public static RestResponse.DefaultRestResponse createFailureDefaultRestResp(Object data) {
        return createDefaultRestResp(false, DEFAULT_FAILURE_RESP_CODE, data);
    }

    public static <N extends Throwable> RestResponse.DefaultRestResponse createFailureDefaultRestResp(N ex, Object data) {
        if (ex instanceof BaseControllerException) {
            BaseControllerException exception = (BaseControllerException) ex;
            return new RestResponse.DefaultRestResponse(exception.getErrorCode(), exception.getLocalizedMessage(), data);
        } else {
            return new RestResponse.DefaultRestResponse(DEFAULT_EXCEPTION_RESP_CODE, buildMessage(DEFAULT_EXCEPTION_RESP_CODE, ex.getLocalizedMessage()), data);
        }
    }

    public static RestResponse.DefaultRestResponse createDefaultRestResp(boolean isSuccess, String respCode, Object data) {

        String respMessage = null;
        try {
            respMessage = buildMessage(respCode);
        } catch (Exception e) {
            logger.warn("未找到国际化文本配置[{}]", respCode, e);
            respCode = isSuccess ? DEFAULT_SUCCESS_RESP_CODE : DEFAULT_FAILURE_RESP_CODE;
            respMessage = buildMessage(respCode);
        }

        return new RestResponse.DefaultRestResponse(respCode, respMessage, data);
    }

    public static RestResponse.DefaultRestResponse createDefaultRestResp(boolean isSuccess, String respCode, Object[] messageArgs, Object data) {

        String respMessage = null;
        try {
            respMessage = buildMessage(respCode, messageArgs);
        } catch (Exception e) {
            logger.warn("未找到国际化文本配置[{}]", respCode, e);
            respCode = isSuccess ? DEFAULT_SUCCESS_RESP_CODE : DEFAULT_FAILURE_RESP_CODE;
            respMessage = buildMessage(respCode);
        }

        return new RestResponse.DefaultRestResponse(respCode, respMessage, data);
    }

    public static <F, M extends RestResponse<F>> M createSuccessRestResp(F data, Class<M> clazz) {
        return createRestResp(DEFAULT_SUCCESS_RESP_CODE, buildMessage(DEFAULT_SUCCESS_RESP_CODE), data, clazz);
    }

    public static <F, M extends RestResponse<F>> M createFailureRestResp(F data, Class<M> clazz) {
        return createRestResp(DEFAULT_FAILURE_RESP_CODE, buildMessage(DEFAULT_FAILURE_RESP_CODE), data, clazz);
    }

    public static <F, M extends RestResponse<F>, N extends Throwable> M createFailureRestResp(N ex, F data, Class<M> clazz) {
        if (ex instanceof BaseControllerException) {
            BaseControllerException exception = (BaseControllerException) ex;
            return createRestResp(exception.getErrorCode(), exception.getLocalizedMessage(), data, clazz);
        } else {
            return createRestResp(DEFAULT_EXCEPTION_RESP_CODE, buildMessage(DEFAULT_EXCEPTION_RESP_CODE, ex.getLocalizedMessage()), data, clazz);
        }
    }

    public static <F, M extends RestResponse<F>> M createRestResp(String respCode, F data, Class<M> clazz) {
        return createRestResp(respCode, buildMessage(respCode), data, clazz);
    }

    public static <F, M extends RestResponse<F>> M createRestResp(String respCode, Object[] messageArgs, F data, Class<M> clazz) {
        return createRestResp(respCode, buildMessage(respCode, messageArgs), data, clazz);
    }

    public static <F, M extends RestResponse<F>> M createRestResp(String respCode, String respMessage, F data, Class<M> clazz) {

        M restResp;

        Constructor<M> constructor1 = null;
        if (ObjectUtil.isNotNull(data)) {
            constructor1 = ClassUtils.getConstructorIfAvailable(clazz, String.class, String.class, data.getClass());
        }

        Constructor<M> constructor2 = ClassUtils.getConstructorIfAvailable(clazz, String.class, String.class);

        try {
            if (constructor1 != null) {
                restResp = constructor1.newInstance(respCode, respMessage, data);
            } else if (constructor2 != null) {
                restResp = constructor2.newInstance(respCode, respMessage);
                restResp.setData(data);
            } else {
                restResp = clazz.newInstance();
                restResp.setRespCode(respCode);
                restResp.setRespMessage(respMessage);
                restResp.setData(data);
            }
            return restResp;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            logger.error("创建RestResponse失败", e);
        }

        return null;
    }

    private static String buildMessage(String code, Object... args) {
        return StarTigerContext.getMessage(StarTigerConstant.StatusCode.PREFIX_CODE + code, args);
    }
}
