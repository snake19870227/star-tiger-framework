package com.snake19870227.stiger.autoconfigure;

import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import com.snake19870227.stiger.core.StarTigerFrameProperties;
import com.snake19870227.stiger.core.context.StarTigerContextLoader;
import com.snake19870227.stiger.core.utils.ClassPathUtil;
import com.snake19870227.stiger.web.context.StarTigerWebContextLoader;
import com.snake19870227.stiger.web.exception.GlobalExceptionHandler;
import com.snake19870227.stiger.web.exception.PostWebErrorHandler;

/**
 * @author Bu HuaYang
 */
@Configuration
@EnableConfigurationProperties({
        StarTigerFrameProperties.class
})
@Import({
        StarTigerLogAutoConfiguration.class,
        StarTigerRedisAutoConfiguration.class
})
public class StarTigerAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerAutoConfiguration.class);

    private final StarTigerFrameProperties starTigerFrameProperties;

    public StarTigerAutoConfiguration(StarTigerFrameProperties starTigerFrameProperties) {
        this.starTigerFrameProperties = starTigerFrameProperties;
    }

    @Bean
    public StarTigerContextLoader starTigerContextLoader() {
        return new StarTigerContextLoader();
    }

    @Bean
    @ConditionalOnWebApplication
    @ConditionalOnClass(StarTigerWebContextLoader.class)
    public StarTigerWebContextLoader starTigerWebContextLoader() {
        return new StarTigerWebContextLoader();
    }

    @Bean
    public MessageSource messageSource() {
        String suffix = "_" + Locale.CHINA + ".properties";
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        String propertyFileNamePattern = "*" + suffix;
        List<Resource> fileList = ClassPathUtil.getResourceFolderFiles(propertyFileNamePattern, true);
        if (fileList.isEmpty()) {
            messageSource.setBasenames("base-messages");
        } else {
            String[] basenames = fileList.stream().map(file -> {
                String fileName = file.getFilename();
                logger.info("加载[{}]", fileName);
                return StrUtil.subBefore(fileName, suffix, true);
            }).toArray(String[]::new);
            messageSource.setBasenames(basenames);
        }
        return messageSource;
    }

    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler.class)
    public GlobalExceptionHandler globalExceptionHandler(ObjectProvider<PostWebErrorHandler> postWebErrorHandlerObjectProvider) {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler(postWebErrorHandlerObjectProvider);
        globalExceptionHandler.setUseHttpStatusCode(starTigerFrameProperties.isUseHttpStatusCode());
        return globalExceptionHandler;
    }
}
