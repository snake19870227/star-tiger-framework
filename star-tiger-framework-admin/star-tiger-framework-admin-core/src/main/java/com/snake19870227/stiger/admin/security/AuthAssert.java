package com.snake19870227.stiger.admin.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import com.snake19870227.stiger.web.utils.WebUtil;

/**
 * @author Bu HuaYang
 */
public class AuthAssert {

    private static final Logger logger = LoggerFactory.getLogger(AuthAssert.class);

    public boolean canAccess(HttpServletRequest request, Authentication authentication) {

        if (authentication instanceof AnonymousAuthenticationToken) {
            logger.error("匿名用户未授权访问...");
            return false;
        }

        UserSecurityDetail detail = (UserSecurityDetail) authentication.getPrincipal();

        for (AntPathRequestMatcher requestMatcher : detail.getMatchers()) {
            boolean flag = requestMatcher.matches(request);
            if (flag) {
                logger.info("匹配成功:[{}][{}][{}]",
                        detail.getUsername(),
                        requestMatcher.getPattern(),
                        request.getMethod() + ":" + WebUtil.getPath(request, true, true));
                return true;
            }
        }

        logger.warn("匹配失败:[{}][{}]",
                detail.getUsername(),
                request.getMethod() + ":" + WebUtil.getPath(request, true, true));

        return false;
    }
}
