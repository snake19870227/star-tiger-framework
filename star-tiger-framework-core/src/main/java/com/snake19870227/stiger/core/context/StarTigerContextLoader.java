package com.snake19870227.stiger.core.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * @author Bu HuaYang
 */
public class StarTigerContextLoader implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerContextLoader.class);

    @Value("${spring.application.name:}")
    private String applicationName;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("loading SpringContext...");
        if (StringUtils.isEmpty(applicationName)) {
            logger.error("Property 'spring.application.name' is empty.");
            System.exit(0);
        }
        Environment env = applicationContext.getEnvironment();
        StarTigerContext.setApplicationName(applicationName);
        StarTigerContext.setApplicationVersion(env.getProperty("stiger.admin.web.version"));
        StarTigerContext.setSpringContext(applicationContext);
        logger.info("SpringContext is loaded.");
    }
}
