package com.snake19870227.stiger.admin.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import com.snake19870227.stiger.admin.entity.po.SysCfg;

/**
 * @author BuHuaYang
 * 2020/9/21
 */
public class SysCfgCache {

    public static final String SYS_CFG_CACHE_NAME = "SysCfgCache";

    @CachePut(cacheNames = SYS_CFG_CACHE_NAME, key = "#cfg.cfgCode")
    public SysCfg put(SysCfg cfg) {
        return cfg;
    }

    @CacheEvict(cacheNames = SYS_CFG_CACHE_NAME, key = "#cfgCode")
    public void expire(String cfgCode) {

    }

    @Cacheable(cacheNames = SYS_CFG_CACHE_NAME, key = "#cfgCode")
    public SysCfg get(String cfgCode) {
        return null;
    }
}
