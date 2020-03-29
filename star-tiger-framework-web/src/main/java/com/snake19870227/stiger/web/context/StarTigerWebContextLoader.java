package com.snake19870227.stiger.web.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Bu HuaYang
 */
public class StarTigerWebContextLoader implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerWebContextLoader.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("loading SpringWebContext...");
        StarTigerWebContext.setSpringContext(applicationContext);
        logger.info("SpringWebContext is loaded.");
    }
}
