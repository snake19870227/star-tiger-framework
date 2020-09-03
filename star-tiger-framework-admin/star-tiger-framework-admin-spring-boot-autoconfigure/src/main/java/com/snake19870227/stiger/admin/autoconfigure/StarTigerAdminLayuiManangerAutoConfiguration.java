package com.snake19870227.stiger.admin.autoconfigure;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import com.snake19870227.stiger.admin.common.CaptchaCacheStorage;
import com.snake19870227.stiger.admin.common.StarTigerAdminConstant;
import com.snake19870227.stiger.admin.manager.controller.IndexController;
import com.snake19870227.stiger.admin.manager.controller.MainController;
import com.snake19870227.stiger.admin.manager.controller.SysDictController;
import com.snake19870227.stiger.admin.manager.controller.SysMenuController;
import com.snake19870227.stiger.admin.manager.controller.SysResourceController;
import com.snake19870227.stiger.admin.manager.controller.SysRoleController;
import com.snake19870227.stiger.admin.manager.controller.SysUserController;
import com.snake19870227.stiger.admin.manager.properties.StarTigerAdminProperties;
import com.snake19870227.stiger.admin.manager.security.ManagerAuthFailureHandler;
import com.snake19870227.stiger.admin.manager.security.ManagerAuthSuccessHandler;
import com.snake19870227.stiger.admin.manager.security.ManagerSecurityExceptionHandler;
import com.snake19870227.stiger.admin.security.ImageCaptchaAuthenticationFilter;
import com.snake19870227.stiger.admin.service.ISysDictItemService;
import com.snake19870227.stiger.admin.service.ISysDictService;
import com.snake19870227.stiger.admin.service.ISysExtService;
import com.snake19870227.stiger.admin.service.ISysMenuService;
import com.snake19870227.stiger.admin.service.ISysResourceService;
import com.snake19870227.stiger.admin.service.ISysRoleResourceService;
import com.snake19870227.stiger.admin.service.ISysRoleService;
import com.snake19870227.stiger.admin.service.ISysUserRoleService;
import com.snake19870227.stiger.admin.service.ISysUserService;
import com.snake19870227.stiger.admin.web.StarTigerAdminControllerHandlerMapping;

import static com.snake19870227.stiger.admin.manager.common.StarTigerAdminManagerConstant.WebAttrKey.MULTI_MODULE;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/30
 */
@Configuration
@EnableConfigurationProperties(StarTigerAdminProperties.class)
public class StarTigerAdminLayuiManangerAutoConfiguration {

    private final StarTigerAdminProperties starTigerAdminProperties;

    public StarTigerAdminLayuiManangerAutoConfiguration(StarTigerAdminProperties starTigerAdminProperties) {
        this.starTigerAdminProperties = starTigerAdminProperties;
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> servletContext.setAttribute(MULTI_MODULE, starTigerAdminProperties.getInit().isMultiModule());
    }

    @Bean
    public CaptchaCacheStorage captchaCacheStorage() {
        return new CaptchaCacheStorage();
    }

    @Bean
    public IndexController indexController(StarTigerAdminProperties adminProperties,
                                           CaptchaCacheStorage captchaCacheStorage) {
        return new IndexController(adminProperties, captchaCacheStorage);
    }

    @Bean
    public MainController mainController(StarTigerAdminProperties starTigerAdminProperties,
                                         PasswordEncoder passwordEncoder,
                                         ISysUserService sysUserService,
                                         ISysExtService sysExtService) {
        return new MainController(starTigerAdminProperties, passwordEncoder, sysUserService, sysExtService);
    }

    @Bean
    public SysDictController sysDictController(ISysDictService sysDictService,
                                               ISysDictItemService sysDictItemService) {
        return new SysDictController(sysDictService, sysDictItemService);
    }

    @Bean
    public SysMenuController sysMenuController(ISysMenuService sysMenuService) {
        return new SysMenuController(sysMenuService);
    }

    @Bean
    public SysResourceController sysResourceController(ISysResourceService sysResourceService,
                                                       ISysExtService sysExtService) {
        return new SysResourceController(sysResourceService, sysExtService);
    }

    @Bean
    public SysRoleController sysRoleController(ISysRoleService sysRoleService,
                                               ISysRoleResourceService sysRoleResourceService,
                                               ISysExtService sysExtService) {
        return new SysRoleController(sysRoleService, sysRoleResourceService, sysExtService);
    }

    @Bean
    public SysUserController sysUserController(ISysUserService sysUserService,
                                               ISysUserRoleService sysUserRoleService,
                                               ISysExtService sysExtService) {
        return new SysUserController(sysUserService, sysUserRoleService, sysExtService);
    }

    @Bean
    public RequestMappingHandlerMapping starTigerAdminControllerHandlerMapping(ContentNegotiationManager contentNegotiationManager) {
        RequestMappingHandlerMapping mapping = new StarTigerAdminControllerHandlerMapping();
        mapping.setOrder(0);
        mapping.setContentNegotiationManager(contentNegotiationManager);
        return mapping;
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

            http.csrf().ignoringAntMatchers(springActuatorPath + "/**")
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
