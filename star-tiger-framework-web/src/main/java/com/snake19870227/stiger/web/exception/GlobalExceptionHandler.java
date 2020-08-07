package com.snake19870227.stiger.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import com.snake19870227.stiger.web.utils.MvcUtil;
import com.snake19870227.stiger.web.utils.WebUtil;
import com.snake19870227.stiger.web.view.ModelAndViewBuilder;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/03/25
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private boolean useHttpStatusCode;

    private PostWebErrorHandler postWebErrorHandler;

    public GlobalExceptionHandler(ObjectProvider<PostWebErrorHandler> postExceptionHandlerProvider) {
        this.postWebErrorHandler = postExceptionHandlerProvider.getIfAvailable();
        logger.debug("创建全局异常处理器");
    }

    @ExceptionHandler(BaseControllerException.class)
    public ModelAndView controllerException(HttpServletRequest request,
                                            HttpServletResponse response,
                                            BaseControllerException ex,
                                            HandlerMethod handlerMethod) {

        ModelAndView mv = null;

        if (MvcUtil.isResponseBody(handlerMethod)
                || MvcUtil.isHttpEntity(handlerMethod)
                || WebUtil.isAjaxRequest(request)) {
            mv = ModelAndViewBuilder.buildToJsonBody(ex);
        } else {
            Map<String, Object> model = ex.getModel();
            mv = new ModelAndView("error/500", model);
        }

        updateHttpStatusCode(response);

        doPostWebErrorHandler(request, response, ex, handlerMethod, mv);

        return mv;
    }

    protected void updateHttpStatusCode(HttpServletResponse response) {
        if (useHttpStatusCode) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    protected void doPostWebErrorHandler(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Exception ex,
                                         HandlerMethod handlerMethod,
                                         ModelAndView mv) {
        if (postWebErrorHandler != null) {
            postWebErrorHandler.exceptionHandler(request, response, handlerMethod, ex, mv);
        }
    }

    public boolean isUseHttpStatusCode() {
        return useHttpStatusCode;
    }

    public void setUseHttpStatusCode(boolean useHttpStatusCode) {
        this.useHttpStatusCode = useHttpStatusCode;
    }
}
