package com.snake19870227.stiger.admin.autoconfigure;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.snake19870227.stiger.admin.common.CaptchaCacheStorage;
import com.snake19870227.stiger.admin.common.StarTigerAdminConstant;
import com.snake19870227.stiger.admin.manager.properties.StarTigerAdminProperties;
import com.snake19870227.stiger.admin.manager.security.ManagerAuthFailureHandler;
import com.snake19870227.stiger.admin.manager.security.ManagerAuthSuccessHandler;
import com.snake19870227.stiger.admin.manager.security.ManagerSecurityExceptionHandler;
import com.snake19870227.stiger.admin.security.ImageCaptchaAuthenticationFilter;

import static com.snake19870227.stiger.admin.manager.common.StarTigerAdminManagerConstant.WebAttrKey.MULTI_MODULE;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/30
 */
@Configuration
public class StarTigerAdminLayuiManangerAutoConfiguration {

    private final StarTigerAdminProperties starTigerAdminProperties;

    public StarTigerAdminLayuiManangerAutoConfiguration(StarTigerAdminProperties starTigerAdminProperties) {
        this.starTigerAdminProperties = starTigerAdminProperties;
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> servletContext.setAttribute(MULTI_MODULE, starTigerAdminProperties.getInit().isMultiModule());
    }

    @Configuration
    public static class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Value("${management.endpoints.web.base-path:/actuator}")
        private String springActuatorPath;

//        @Value("${stiger.admin.web.security.remember-me-key}")
//        private String rememberMeKey;

//        @Value("${stiger.admin.oauth2.enable:false}")
//        private boolean enableOauth2;

        private final ManagerAuthSuccessHandler managerAuthSuccessHandler;

        private final ManagerAuthFailureHandler managerAuthFailureHandler;

        private final ManagerSecurityExceptionHandler managerSecurityExceptionHandler;

        private final CaptchaCacheStorage captchaCacheStorage;

        public CustomWebSecurityConfigurerAdapter(ManagerAuthSuccessHandler managerAuthSuccessHandler,
                                                  ManagerAuthFailureHandler managerAuthFailureHandler,
                                                  ManagerSecurityExceptionHandler managerSecurityExceptionHandler,
                                                  CaptchaCacheStorage captchaCacheStorage) {
            this.managerAuthSuccessHandler = managerAuthSuccessHandler;
            this.managerAuthFailureHandler = managerAuthFailureHandler;
            this.managerSecurityExceptionHandler = managerSecurityExceptionHandler;
            this.captchaCacheStorage = captchaCacheStorage;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http.authorizeRequests();

            urlRegistry
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .antMatchers(StarTigerAdminConstant.UrlPath.anonymousPaths()).permitAll()
                    .antMatchers(StarTigerAdminConstant.UrlPath.authenticatedPaths()).authenticated()
                    .anyRequest().access("@authAssert.canAccess(request, authentication)")
//                    .anyRequest().authenticated()
            ;

            http.csrf()
//                    .ignoringAntMatchers(springActuatorPaths, UrlPath.OAUTH_PATTERN)
            ;
            http.headers().frameOptions().sameOrigin();

            http.formLogin()
                    .loginPage(StarTigerAdminConstant.UrlPath.LOGIN)
                    .failureHandler(managerAuthFailureHandler)
                    .successHandler(managerAuthSuccessHandler)
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher(StarTigerAdminConstant.UrlPath.LOGOUT, HttpMethod.GET.name()))
//                .and()
//                .rememberMe()
//                    .key(rememberMeKey)
//                    .rememberMeServices(rememberMeServices)
//                    .authenticationSuccessHandler(webAuthenticationSuccessHandler)
//                .and()
//                .httpBasic()
                    .and()
                    .addFilterBefore(
                            new ImageCaptchaAuthenticationFilter(StarTigerAdminConstant.UrlPath.LOGIN, captchaCacheStorage, managerAuthFailureHandler),
                            UsernamePasswordAuthenticationFilter.class
                    )
                    .sessionManagement().maximumSessions(1)
            ;

//            if (enableOauth2) {
//                http.oauth2ResourceServer().jwt();
//            }

            http.exceptionHandling()
                    .authenticationEntryPoint(managerSecurityExceptionHandler)
                    .accessDeniedHandler(managerSecurityExceptionHandler)
            ;
        }
    }
}
