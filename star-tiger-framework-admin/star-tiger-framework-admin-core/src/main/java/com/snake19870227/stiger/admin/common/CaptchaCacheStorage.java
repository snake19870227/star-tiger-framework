package com.snake19870227.stiger.admin.common;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/07/23
 */
@Component
public class CaptchaCacheStorage {

    public static final String CAPTCHA_CACHE = "captcha_cache";

    /**
     * 验证码放入缓存.
     *
     * @param key 验证码存入的key
     * @param code 验证码
     */
    @CachePut(cacheNames = CaptchaCacheStorage.CAPTCHA_CACHE, key = "#key")
    public String put(String key, String code) {
        return code;
    }

    /**
     * 从缓存取验证码.
     *
     * @param key 验证码存入的key
     * @return 验证码
     */
    @Cacheable(cacheNames = CaptchaCacheStorage.CAPTCHA_CACHE, key = "#key")
    public String get(String key) {
        return null;
    }

    /**
     * 验证码手动过期.
     *
     * @param key 验证码存入的key
     */
    @CacheEvict(cacheNames = CaptchaCacheStorage.CAPTCHA_CACHE, key = "#key")
    public void expire(String key) {

    }
}
