package com.snake19870227.stiger.web.view;

import cn.hutool.core.bean.BeanUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.web.restful.RestResp;
import com.snake19870227.stiger.web.restful.RestResponse;
import com.snake19870227.stiger.web.restful.RestResponseBuilder;
import com.snake19870227.stiger.web.exception.BaseControllerException;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/03/14
 */
public class ModelAndViewBuilder {

    public static MappingJackson2JsonView view;

    static {
        view = new MappingJackson2JsonView(StarTigerContext.getJsonMapper());
    }

    @Deprecated
    public static ModelAndView buildToJsonResponseBody(BaseControllerException e) {
        RestResponse.DefaultRestResponse restResponse = RestResponseBuilder.createFailureDefaultRestResp(e, null);
        return new ModelAndView(view, BeanUtil.beanToMap(restResponse));
    }

    public static ModelAndView buildToJsonBody(BaseControllerException e) {
        RestResp<?> resp = RestResp.buildResp("9999", e);
        return new ModelAndView(view, BeanUtil.beanToMap(resp));
    }

    @Deprecated
    public static ModelAndView buildToJsonResponseBody(RestResponse<?> restResponse) {
        return new ModelAndView(view, BeanUtil.beanToMap(restResponse));
    }

    public static ModelAndView buildToJsonBody(RestResp<?> resp) {
        return new ModelAndView(view, BeanUtil.beanToMap(resp));
    }
}
