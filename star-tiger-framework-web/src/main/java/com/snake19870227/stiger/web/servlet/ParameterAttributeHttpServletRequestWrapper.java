package com.snake19870227.stiger.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Bu HuaYang
 */
public class ParameterAttributeHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request the {@link HttpServletRequest} to be wrapped.
     * @throws IllegalArgumentException if the request is null
     */
    public ParameterAttributeHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        if (super.getParameterMap().containsKey(name)) {
            return super.getParameter(name);
        } else {
            return (String) super.getAttribute(name);
        }
    }
}
