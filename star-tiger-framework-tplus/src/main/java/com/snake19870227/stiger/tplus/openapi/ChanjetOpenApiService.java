package com.snake19870227.stiger.tplus.openapi;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.snake19870227.stiger.core.exception.BusinessException;
import com.snake19870227.stiger.tplus.ChanjetConstant;
import com.snake19870227.stiger.tplus.openapi.entity.dto.AccessToken;
import com.snake19870227.stiger.tplus.openapi.entity.dto.AppAccessToken;
import com.snake19870227.stiger.tplus.openapi.entity.dto.OrgAccessToken;
import com.snake19870227.stiger.tplus.openapi.entity.dto.PermanentAuthCode;

public class ChanjetOpenApiService {

    private static final Logger logger = LoggerFactory.getLogger(ChanjetOpenApiService.class);

    private final ChanjetClient commonClient;

    public ChanjetOpenApiService(ChanjetClient commonClient) {
        this.commonClient = commonClient;
    }

    public String buildAuthUrl(String orgId, String authCallbackUrl) {
        return buildAuthUrl(orgId, authCallbackUrl, null);
    }

    public String buildAuthUrl(String orgId, String authCallbackUrl, String state) {
        return "https://market.chanjet.com/user/authorize" +
                "?appKey=" + commonClient.getAppKey() +
                "&orgId=" + orgId +
                "&appName=tpluscloud" +
                "&redirectUri=" + URLUtil.encode(authCallbackUrl) +
                "&scope=auth_all" +
                "&state=" + StrUtil.blankToDefault(state, "");
    }

    public boolean resendAppTicket() {
        try {
            GeneralRequest<String> request = new GeneralRequest<>();
            request.setApiUrl(ChanjetConstant.OpenApi.AUTH_RESEND_APP_TICKET)
                    .setResponseResultClass(String.class);
            GeneralResponse<String> response = commonClient.executePost(request);
            if (StrUtil.equals(ChanjetConstant.RESPONSE_SUCCESS_CODE, response.getCode())) {
                return true;
            } else {
                logger.warn("触发appTicket推送失败:[{}]{}", response.getCode(), response.getMessage());
            }
        } catch (Exception e) {
            logger.error("触发appTicket推送失败", e);
        }
        return false;
    }

    public AppAccessToken getAppAccessToken(String appTicket) {
        try {
            GeneralRequest<AppAccessToken> request = new GeneralRequest<>();
            request.setApiUrl(ChanjetConstant.OpenApi.AUTH_GET_APP_ACCESS_TOKEN)
                    .setResponseResultClass(AppAccessToken.class)
                    .bodyProperty("appTicket", appTicket);
            GeneralResponse<AppAccessToken> response = commonClient.executePost(request);
            if (StrUtil.equals(ChanjetConstant.RESPONSE_SUCCESS_CODE, response.getCode())) {
                return response.getResult();
            }
            throw new BusinessException("获取应用凭证失败[" + response.getCode() + ":" + response.getMessage() + "]");
        } catch (Exception e) {
            logger.error("获取应用凭证失败", e);
            throw new BusinessException("获取应用凭证失败", e);
        }
    }

    public PermanentAuthCode getPermanentAuthCode(String appAccessToken, String tempAuthCode) {
        try {
            GeneralRequest<PermanentAuthCode> request = new GeneralRequest<>();
            request.setApiUrl(ChanjetConstant.OpenApi.AUTH_GET_PERMANENT_AUTH_CODE)
                    .setResponseResultClass(PermanentAuthCode.class)
                    .bodyProperty("appAccessToken", appAccessToken)
                    .bodyProperty("tempAuthCode", tempAuthCode);
            GeneralResponse<PermanentAuthCode> response = commonClient.executePost(request);
            if (StrUtil.equals(ChanjetConstant.RESPONSE_SUCCESS_CODE, response.getCode())) {
                return response.getResult();
            }
            throw new BusinessException("获取企业永久授权码失败[" + response.getCode() + ":" + response.getMessage() + "]");
        } catch (Exception e) {
            logger.error("获取企业永久授权码失败", e);
            throw new BusinessException("获取企业永久授权码失败", e);
        }
    }

    public OrgAccessToken getOrgAccessToken(String permanentAuthCode, String appAccessToken) {
        try {
            GeneralRequest<OrgAccessToken> request = new GeneralRequest<>();
            request.setApiUrl(ChanjetConstant.OpenApi.AUTH_GET_ORG_ACCESS_TOKEN)
                    .setResponseResultClass(OrgAccessToken.class)
                    .bodyProperty("permanentAuthCode", permanentAuthCode)
                    .bodyProperty("appAccessToken", appAccessToken);
            GeneralResponse<OrgAccessToken> response = commonClient.executePost(request);
            if (StrUtil.equals(ChanjetConstant.RESPONSE_SUCCESS_CODE, response.getCode())) {
                return response.getResult();
            }
            throw new BusinessException("获取企业凭证失败[" + response.getCode() + ":" + response.getMessage() + "]");
        } catch (BusinessException e) {
            logger.error("获取企业凭证失败", e);
            throw new BusinessException("获取企业凭证失败", e);
        }
    }

    public AccessToken getAccessToken(String redirectUri, String code) {
        try {
            GeneralRequest<AccessToken> request = new GeneralRequest<>();
            request.setApiUrl(ChanjetConstant.OpenApi.AUTH_GET_ACCESS_TOKEN)
                    .setResponseResultClass(AccessToken.class)
                    .bodyProperty("grantType", "authorization_code")
                    .bodyProperty("appKey", commonClient.getAppKey())
                    .bodyProperty("redirectUri", URLUtil.encode(redirectUri))
                    .bodyProperty("code", code);
            GeneralResponse<AccessToken> response = commonClient.executeGet(request);
            if (StrUtil.equals(ChanjetConstant.RESPONSE_SUCCESS_CODE, response.getCode())) {
                return response.getResult();
            }
            throw new BusinessException("授权码换token失败[" + response.getCode() + ":" + response.getMessage() + "]");
        } catch (Exception e) {
            logger.error("授权码换token失败", e);
            throw new BusinessException("授权码换token失败", e);
        }
    }

    public AccessToken getAccessTokenByPermanentCode(String orgAccessToken, String userAuthPermanentCode) {
        try {
            GeneralRequest<AccessToken> request = new GeneralRequest<>();
            request.setApiUrl(ChanjetConstant.OpenApi.AUTH_GET_ACCESS_TOKEN_BY_PERMANENT_CODE)
                    .setResponseResultClass(AccessToken.class)
                    .bodyProperty("orgAccessToken", orgAccessToken)
                    .bodyProperty("userAuthPermanentCode", userAuthPermanentCode);
            GeneralResponse<AccessToken> response = commonClient.executePost(request);
            if (StrUtil.equals(ChanjetConstant.RESPONSE_SUCCESS_CODE, response.getCode())) {
                return response.getResult();
            }
            throw new BusinessException("授权码换token失败[" + response.getCode() + ":" + response.getMessage() + "]");
        } catch (Exception e) {
            logger.error("授权码换token失败", e);
            throw new BusinessException("授权码换token失败", e);
        }
    }

    public AccessToken refreshToken(String refreshToken) {
        try {
            GeneralRequest<AccessToken> request = new GeneralRequest<>();
            request.setApiUrl(ChanjetConstant.OpenApi.AUTH_REFRESH_TOKEN)
                    .setResponseResultClass(AccessToken.class)
                    .bodyProperty("grantType", "refresh_token")
                    .bodyProperty("appKey", commonClient.getAppKey())
                    .bodyProperty("refreshToken", refreshToken);
            GeneralResponse<AccessToken> response = commonClient.executeGet(request);
            if (StrUtil.equals(ChanjetConstant.RESPONSE_SUCCESS_CODE, response.getCode())) {
                return response.getResult();
            }
            throw new BusinessException("刷新token失败[" + response.getCode() + ":" + response.getMessage() + "]");
        } catch (Exception e) {
            logger.error("刷新token失败", e);
            throw new BusinessException("刷新token失败", e);
        }
    }
}
