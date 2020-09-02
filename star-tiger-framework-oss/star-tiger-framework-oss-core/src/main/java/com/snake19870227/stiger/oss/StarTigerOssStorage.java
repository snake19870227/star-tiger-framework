package com.snake19870227.stiger.oss;

import java.io.InputStream;

/**
 * @author BuHuaYang
 * @date 2020/9/2
 */
public interface StarTigerOssStorage {

    /**
     * Put object.
     *
     * @param bucketName  the bucket name
     * @param objectName  the object name
     * @param inputStream the input stream
     * @param contentType the content type
     * @throws Exception the exception
     */
    void putObject(String bucketName, String objectName, InputStream inputStream, String contentType) throws Exception;

    /**
     * Gets object.
     *
     * @param bucketName the bucket name
     * @param objectName the object name
     * @return the object
     */
    InputStream getObject(String bucketName, String objectName) throws Exception;

    /**
     * Gets object url.
     *
     * @param bucketName the bucket name
     * @param objectName the object name
     * @return the object url
     */
    String getObjectUrl(String bucketName, String objectName) throws Exception;

    /**
     * Remove object.
     *
     * @param bucketName the bucket name
     * @param objectName the object name
     */
    void removeObject(String bucketName, String objectName) throws Exception;
}
