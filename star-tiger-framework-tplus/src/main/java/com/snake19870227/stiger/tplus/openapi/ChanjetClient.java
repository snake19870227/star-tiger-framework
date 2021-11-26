package com.snake19870227.stiger.tplus.openapi;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.snake19870227.stiger.tplus.ChanjetConstant;

/**
 * @author BuHuaYang
 * 8/6 006
 */
public class ChanjetClient {

    private static final Logger logger = LoggerFactory.getLogger(ChanjetClient.class);

    private String appKey;

    private String appSecret;

    public ChanjetClient() {

    }

    public ChanjetClient(String appKey, String appSecret) {
        this();
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public <T> GeneralResponse<T> executePost(GeneralRequest<T> request) {
        try {
            String responseBody = HttpRequest.post(request.getApiUrl())
                    .header(ChanjetConstant.HEADER_APP_KEY, appKey)
                    .header(ChanjetConstant.HEADER_APP_SECRET, appSecret)
                    .body(request.requestBody())
                    .execute()
                    .body();
            logger.debug("畅捷通公共api请求响应:[{}]", responseBody);
            return GeneralResponse.parse(responseBody, request.getResponseResultClass());
        } catch (Exception e) {
            logger.error("畅捷通公共api访问失败", e);
            return null;
        }
    }

    public <T> GeneralResponse<T> executeGet(GeneralRequest<T> request) {
        try {
            String responseBody = HttpRequest.get(request.getApiUrl())
                    .header(ChanjetConstant.HEADER_APP_KEY, appKey)
                    .header(ChanjetConstant.HEADER_APP_SECRET, appSecret)
                    .form(request.getRequestBodyMap())
                    .execute()
                    .body();
            logger.debug("畅捷通公共api请求响应:[{}]", responseBody);
            return GeneralResponse.parse(responseBody, request.getResponseResultClass());
        } catch (Exception e) {
            logger.error("畅捷通公共api访问失败", e);
            return null;
        }
    }

    public String tplusPost(String path, Object reqBody, String accessToken) {
        try {
            HttpResponse response = HttpRequest.post(ChanjetConstant.SERVER_URL + path)
                    .header(ChanjetConstant.HEADER_APP_KEY, appKey)
                    .header(ChanjetConstant.HEADER_APP_SECRET, appSecret)
                    .header(ChanjetConstant.HEADER_OPEN_TOKEN, accessToken)
                    .body(JSONUtil.toJsonStr(reqBody))
                    .execute();
            int status = response.getStatus();
            String responseBody = response.body();
            if (status == 200) {
                return responseBody;
            } else {
//                GeneralResponse<?> errorResp = GeneralResponse.parse(responseBody, null);
                throw new RuntimeException("畅捷通 T+ api访问失败[" + responseBody + "]");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
