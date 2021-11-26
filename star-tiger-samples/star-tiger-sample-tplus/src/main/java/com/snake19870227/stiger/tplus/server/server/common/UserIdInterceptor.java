package com.snake19870227.stiger.tplus.server.server.common;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import com.snake19870227.stiger.tplus.server.server.Constant;

public class UserIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute(Constant.USER_ID_SESSION_KEY);
        if (StrUtil.isBlank(userId)) {
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }
        return true;
    }
}
