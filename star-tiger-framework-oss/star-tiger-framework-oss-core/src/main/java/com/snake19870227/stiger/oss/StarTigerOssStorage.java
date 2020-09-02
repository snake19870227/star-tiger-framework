package com.snake19870227.stiger.oss;

import java.io.InputStream;

/**
 * @author BuHuaYang
 * 2020/07/17
 */
public interface StarTigerOssStorage {

    /**
     * 上传对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param inputStream 对象输入流
     * @param contentType 对象媒体类型
     * @throws Exception 报错异常
     */
    void putObject(String bucketName, String objectName, InputStream inputStream, String contentType) throws Exception;

    /**
     * 获取对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @return 对象输入流
     * @throws Exception 报错异常
     */
    InputStream getObject(String bucketName, String objectName) throws Exception;

    /**
     * 获取对象url
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @return 对象url
     * @throws Exception 报错异常
     */
    String getObjectUrl(String bucketName, String objectName) throws Exception;

    /**
     * 删除对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @throws Exception 报错异常
     */
    void removeObject(String bucketName, String objectName) throws Exception;
}
