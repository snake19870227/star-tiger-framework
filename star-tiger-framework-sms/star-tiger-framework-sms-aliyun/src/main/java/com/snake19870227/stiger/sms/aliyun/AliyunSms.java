package com.snake19870227.stiger.sms.aliyun;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.snake19870227.stiger.sms.aliyun.exception.StarTigerSmsException;
import com.snake19870227.stiger.sms.aliyun.properties.StarTigerSmsAliyunProperties;

/**
 * @author BuHuaYang
 * 2020/9/3
 */
public class AliyunSms {

    private static final Logger logger = LoggerFactory.getLogger(AliyunSms.class);

    private final StarTigerSmsAliyunProperties starTigerSmsAliyunProperties;

    private final IAcsClient acsClient;

    public AliyunSms(StarTigerSmsAliyunProperties starTigerSmsAliyunProperties,
                     IAcsClient acsClient) {
        this.starTigerSmsAliyunProperties = starTigerSmsAliyunProperties;
        this.acsClient = acsClient;
    }

    public void send(String signName, String templateCode, String phoneNumbers, Map<String, String> templateParamMap) {
        try {
            CommonRequest request = createCommonRequest();
            request.setSysAction(StarTigerSmsConstant.Aliyun.ACTION_SEND_SMS);
            request.putQueryParameter(StarTigerSmsConstant.Aliyun.PARAM_PHONE_NUMBERS, phoneNumbers);
            request.putQueryParameter(StarTigerSmsConstant.Aliyun.PARAM_SIGN_NAME, signName);
            request.putQueryParameter(StarTigerSmsConstant.Aliyun.PARAM_TEMPLATE_CODE, templateCode);
            if (MapUtil.isNotEmpty(templateParamMap)) {
                request.putQueryParameter(StarTigerSmsConstant.Aliyun.PARAM_TEMPLATE_PARAM, JSONUtil.toJsonStr(templateParamMap));
            }
            CommonResponse response = acsClient.getCommonResponse(request);
            if (StrUtil.isNotBlank(response.getData())) {
                JSONObject obj = JSONUtil.parseObj(response.getData());
                String code = obj.getStr(StarTigerSmsConstant.Aliyun.RESP_CODE);
                String message = obj.getStr(StarTigerSmsConstant.Aliyun.RESP_MESSAGE);
                String requestId = obj.getStr(StarTigerSmsConstant.Aliyun.RESP_REQUEST_ID);
                if (StrUtil.equals(code, StarTigerSmsConstant.Aliyun.CODE_OK)) {
                    String bizId = obj.getStr(StarTigerSmsConstant.Aliyun.RESP_BIZ_ID);
                    logger.info("短信发送成功.[{}|{}|{}]", signName, templateCode, phoneNumbers);
                } else {
                    throw new StarTigerSmsException("发送短信失败.[" + message + "]");
                }
            } else {
                throw new StarTigerSmsException("发送短信失败.[阿里云响应为空]");
            }
        } catch (ClientException e) {
            throw new StarTigerSmsException("发送短信失败.[]", e);
        }
    }

    public void send(String signName, String templateCode, String[] phoneNumberArray, Map<String, String> templateParamMap) {
        if (ArrayUtil.isEmpty(phoneNumberArray)) {
            throw new StarTigerSmsException("发送目标手机号不能为空.");
        }
        String phoneNumbers = ArrayUtil.join(phoneNumberArray, ",");
        send(signName, templateCode, phoneNumbers, templateParamMap);
    }

    private CommonRequest createCommonRequest() {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(starTigerSmsAliyunProperties.getSendEndpoint());
        request.setSysVersion(starTigerSmsAliyunProperties.getSysVersion());
        request.putQueryParameter(StarTigerSmsConstant.Aliyun.PARAM_REGION_ID, starTigerSmsAliyunProperties.getRegionId());
        return request;
    }
}
