package com.snake19870227.stiger.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/21
 */
public interface PostWebErrorHandler {

    void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex, ModelAndView mv);

    void errorPageHandler(HttpServletRequest request, HttpStatus status, ModelAndView mv);

}
