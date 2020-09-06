package com.snake19870227.stiger.oss.aliyun;

import java.io.InputStream;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.snake19870227.stiger.oss.StarTigerOssStorage;

/**
 * @author BuHuaYang
 * 2020/9/5
 */
public class AliyunStarTigerOssStorage implements StarTigerOssStorage {

    private final OSSClient ossClient;

    public AliyunStarTigerOssStorage(OSSClient ossClient) {
        this.ossClient = ossClient;
    }

    @Override
    public void putObject(String bucketName, String objectName, InputStream inputStream, String contentType) throws Exception {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        this.ossClient.putObject(bucketName, objectName, inputStream, objectMetadata);
    }

    @Override
    public InputStream getObject(String bucketName, String objectName) throws Exception {
        return this.ossClient.getObject(bucketName, objectName).getObjectContent();
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName) throws Exception {
        return "https://" + bucketName + "." + this.ossClient.getEndpoint() + "/" + objectName;
    }

    @Override
    public void removeObject(String bucketName, String objectName) throws Exception {
        this.ossClient.deleteObject(bucketName, objectName);
    }
}
