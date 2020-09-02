package com.snake19870227.stiger.oss.minio;

import io.minio.MinioClient;

import java.io.BufferedInputStream;
import java.io.InputStream;

import com.snake19870227.stiger.oss.StarTigerOssStorage;

/**
 * @author BuHuaYang
 * @date 2020/9/2
 */
public class MinioStarTigerOssStorage implements StarTigerOssStorage {

    private final MinioClient minioClient;

    public MinioStarTigerOssStorage(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public void putObject(String bucketName, String objectName, InputStream inputStream, String contentType) throws Exception {
        minioClient.putObject(bucketName, objectName, new BufferedInputStream(inputStream), contentType);
    }

    @Override
    public InputStream getObject(String bucketName, String objectName) throws Exception {
        return minioClient.getObject(bucketName, objectName);
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName) throws Exception {
        return minioClient.getObjectUrl(bucketName, objectName);
    }

    @Override
    public void removeObject(String bucketName, String objectName) throws Exception {
        minioClient.removeObject(bucketName, objectName);
    }
}
