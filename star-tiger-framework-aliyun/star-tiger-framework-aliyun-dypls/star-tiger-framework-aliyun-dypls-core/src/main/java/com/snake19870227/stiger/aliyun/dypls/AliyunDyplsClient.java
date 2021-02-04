package com.snake19870227.stiger.aliyun.dypls;

import cn.hutool.core.util.StrUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aliyuncs.AcsRequest;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxbResponse;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnResponse;
import com.aliyuncs.dyplsapi.model.v20170525.QueryCallStatusRequest;
import com.aliyuncs.dyplsapi.model.v20170525.QueryCallStatusResponse;
import com.aliyuncs.dyplsapi.model.v20170525.QueryRecordFileDownloadUrlRequest;
import com.aliyuncs.dyplsapi.model.v20170525.QueryRecordFileDownloadUrlResponse;
import com.aliyuncs.dyplsapi.model.v20170525.UnbindSubscriptionRequest;
import com.aliyuncs.dyplsapi.model.v20170525.UnbindSubscriptionResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.snake19870227.stiger.aliyun.dypls.enums.DyplsTypeEnum;

/**
 * @author BuHuaYang
 * 2021/1/25
 */
public class AliyunDyplsClient {

    private static final Logger logger = LoggerFactory.getLogger(AliyunDyplsClient.class);

    private final String poolKey;

    private final DefaultProfile profile;

    private final IAcsClient client;

    public AliyunDyplsClient(String poolKey, DefaultProfile profile) {
        this.poolKey = poolKey;
        this.profile = profile;
        this.client = new DefaultAcsClient(profile);
    }

    public BindAxbResponse.SecretBindDTO axbBind(String phonea, String phoneb, String expireTime, String poolKey, boolean isRecordingEnabled) {
        return axbBind(phonea, phoneb, expireTime, poolKey, isRecordingEnabled, null);
    }

    public BindAxbResponse.SecretBindDTO axbBind(String phonea, String phoneb, String expireTime, String poolKey, boolean isRecordingEnabled, String outId) {
        return axbBind(phonea, phoneb, null, expireTime, poolKey, isRecordingEnabled, null, null);
    }

    public BindAxbResponse.SecretBindDTO axbBind(String phonea, String phoneb, String phonex, String expireTime, String poolKey, boolean isRecordingEnabled, String city, String outId) {
        BindAxbResponse response = bind(DyplsTypeEnum.BindAxb, phonea, phoneb, phonex, expireTime, poolKey, isRecordingEnabled, city, null);
        if (response != null && StrUtil.equalsIgnoreCase("OK", response.getCode())) {
            logger.info("AXB绑定结果:[{}|{}]", response.getCode(), response.getMessage());
            return response.getSecretBindDTO();
        }
        return null;
    }

    public BindAxnResponse.SecretBindDTO axnBind(String phonea, String phoneb, String phonex, String expireTime, String poolKey, boolean isRecordingEnabled, String city, String outId) {
        BindAxnResponse response = bind(DyplsTypeEnum.BindAxn, phonea, phoneb, phonex, expireTime, poolKey, isRecordingEnabled, city, null);
        if (response != null && StrUtil.equalsIgnoreCase("OK", response.getCode())) {
            logger.info("AXN绑定结果:[{}|{}]", response.getCode(), response.getMessage());
            return response.getSecretBindDTO();
        }
        return null;
    }

    public <T extends AcsResponse> T bind(DyplsTypeEnum dyplsTypeEnum,
                                          String phonea,
                                          String phoneb,
                                          String phonex,
                                          String expireTime,
                                          String poolKey,
                                          boolean isRecordingEnabled,
                                          String city,
                                          String outId) {
        if (StrUtil.isBlank(poolKey)) {
            logger.warn("未指定poolKey,使用默认");
            poolKey = this.poolKey;
        }
        logger.info("绑定参数:[type:{}|a:{}|b:{}|x:{}|pool:{}|record:{}|outid:{}]", dyplsTypeEnum.getId(), phonea, phoneb, phonex, poolKey, isRecordingEnabled, outId);
        try {
            AcsRequest<? extends AcsResponse> request = dyplsTypeEnum.createRequest(phonea, phoneb, phonex, expireTime, poolKey, isRecordingEnabled, city, outId);
            try {
                return (T) client.getAcsResponse(request);
            } catch (ServerException e) {
                logger.error("");
            } catch (ClientException e) {
                logger.error("[bind]ErrCode:" + e.getErrCode());
                logger.error("[bind]ErrMsg:" + e.getErrMsg());
                logger.error("[bind]RequestId:" + e.getRequestId());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("绑定失败[type:{}|a:{}|b:{}|x:{}]", dyplsTypeEnum.name(), phonea, phoneb, phonex);
        }
        return null;
    }

    public UnbindSubscriptionResponse unbind(String phonex, String subId, String poolKey) {
        UnbindSubscriptionRequest request = new UnbindSubscriptionRequest();
        request.setSubsId(subId);
        request.setSecretNo(phonex);
        request.setPoolKey(poolKey);
        try {
            return client.getAcsResponse(request);
        } catch (ClientException e) {
            logger.error("[unbind]ErrCode:" + e.getErrCode());
            logger.error("[unbind]ErrMsg:" + e.getErrMsg());
            logger.error("[unbind]RequestId:" + e.getRequestId());
        }
        return null;
    }

    public QueryRecordFileDownloadUrlResponse queryRecordFileDownloadUrl(String callId, String callTime, String poolKey) {
        QueryRecordFileDownloadUrlRequest request = new QueryRecordFileDownloadUrlRequest();
        request.setCallId(callId);
        request.setCallTime(callTime);
        request.setPoolKey(poolKey);
        try {
            return client.getAcsResponse(request);
        } catch (ClientException e) {
            logger.error("[queryRecordFileDownloadUrl]ErrCode:" + e.getErrCode());
            logger.error("[queryRecordFileDownloadUrl]ErrMsg:" + e.getErrMsg());
            logger.error("[queryRecordFileDownloadUrl]RequestId:" + e.getRequestId());
        }
        return null;
    }

    public QueryCallStatusResponse queryCallStatus(String subId, String poolKey) {
        QueryCallStatusRequest request = new QueryCallStatusRequest();
        request.setPoolKey(poolKey);
        request.setSubsId(subId);
        try {
            return client.getAcsResponse(request);
        } catch (ClientException e) {
            logger.error("[queryCallStatus]ErrCode:" + e.getErrCode());
            logger.error("[queryCallStatus]ErrMsg:" + e.getErrMsg());
            logger.error("[queryCallStatus]RequestId:" + e.getRequestId());
        }
        return null;
    }
}
