package com.snake19870227.stiger.web.view;

import cn.hutool.core.bean.BeanUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.web.restful.RestResp;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/03/14
 */
public class ModelAndViewBuilder {

    public static MappingJackson2JsonView view;

    static {
        view = new MappingJackson2JsonView(StarTigerContext.getJsonMapper());
    }

    public static ModelAndView buildToJsonBody(RestResp<?> resp) {
        return new ModelAndView(view, BeanUtil.beanToMap(resp));
    }
}
