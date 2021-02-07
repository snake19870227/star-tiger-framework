package com.snake19870227.stiger.aliyun.sms;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.snake19870227.stiger.sms.SmsClient;
import com.snake19870227.stiger.sms.SmsRespnoseHandler;
import com.snake19870227.stiger.sms.entity.po.SmsLog;
import com.snake19870227.stiger.sms.entity.po.SmsTemplate;

/**
 * @author BuHuaYang
 * 2021/1/2
 */
public class AliyunSmsClient implements SmsClient {

    private static final Logger logger = LoggerFactory.getLogger(AliyunSmsClient.class);

    public static final String CHANNEL = "aliyun";

    private final DefaultProfile profile;

    private final IAcsClient client;

    public AliyunSmsClient(DefaultProfile profile) {
        this.profile = profile;
        this.client = new DefaultAcsClient(profile);
    }

    @Override
    public void sendSms(SmsLog smsLog) {
        smsLog.setSendDatetime(LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));
        try {
            CommonResponse commonResponse = doSendSms(smsLog.getPhone(), smsLog.getSignName(), smsLog.getSendParam(), smsLog.getTemplateCode());
            if (StrUtil.isBlank(commonResponse.getData())) {
                String msg = StrUtil.format("阿里云发送短信接口返回空[{}]", smsLog.getId());
                throw new RuntimeException(msg);
            }
            AliyunSmsResponse<String> r = new AliyunSmsResponse<>(commonResponse.getData(), r1 -> r1.getStr("BizId"));
            if (r.isSuccess() && StrUtil.isNotBlank(r.getData())) {
                smsLog.setStatus(1);
                smsLog.setSendId(r.getData());
            } else {
                smsLog.setStatus(2);
            }
            smsLog.setSendCode(r.getCode());
            smsLog.setSendMsg(r.getMessage());
        } catch (Exception e) {
            if (smsLog.getStatus() == 2) {
                smsLog.setRetryCount(smsLog.getRetryCount() + 1);
            }
            logger.error("发送[{}]短信失败", smsLog.getId(), e);
            smsLog.setStatus(2);
            smsLog.setSendMsg(e.getMessage());
        }
    }

    @Override
    public SmsTemplate queryTemplate(String templateCode) {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("QuerySmsTemplate");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("TemplateCode", templateCode);
        try {
            CommonResponse response = client.getCommonResponse(request);
            if (StrUtil.isBlank(response.getData())) {
                throw new RuntimeException("阿里云查询模板接口返回空");
            }
            AliyunSmsResponse<AliyunSmsTemplate> r = new AliyunSmsResponse<>(response.getData(), AliyunSmsTemplate::new);
            AliyunSmsTemplate data = r.getData();
            if (r.isSuccess() && data != null) {
                SmsTemplate template = new SmsTemplate();
                template.setChannel(CHANNEL);
                template.setTemplateCode(data.getTemplateCode());
                template.setTemplateName(data.getTemplateName());
                template.setTemplateType(String.valueOf(data.getTemplateType()));
                template.setTemplateStatus(String.valueOf(data.getTemplateStatus()));
                template.setTemplateContent(data.getTemplateContent());
                template.setRemark(data.getReason());
                LocalDateTime createDatetime = LocalDateTimeUtil.parse(data.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
                template.setCommitDatetime(LocalDateTimeUtil.format(createDatetime, "yyyyMMddHHmmss"));
                return template;
            }
            return null;
        } catch (Exception e) {
            String msg = StrUtil.format("阿里云短信模板查询失败[{}]", templateCode);
            throw new RuntimeException(msg, e);
        }
    }

    @Override
    public String getChannel() {
        return CHANNEL;
    }

    private CommonResponse doSendSms(String phoneNumber, String signName, String params,
                           String templateCode) {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
//        String param = JSONUtil.toJsonStr(params);
        request.putQueryParameter("TemplateParam", params);
        try {
            CommonResponse response = client.getCommonResponse(request);
            logger.info("阿里云短信发送结果:{}", response.getData());
            return response;
        } catch (Exception e) {
            String msg = StrUtil.format("阿里云短信发送失败[{}|{}|{}|{}]", phoneNumber, signName, templateCode, params);
            throw new RuntimeException(msg, e);
        }
    }
}
