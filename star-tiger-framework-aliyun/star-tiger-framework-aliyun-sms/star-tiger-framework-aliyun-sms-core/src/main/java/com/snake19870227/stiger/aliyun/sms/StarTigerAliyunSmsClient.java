package com.snake19870227.stiger.aliyun.sms;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySmsTemplateRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySmsTemplateResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.snake19870227.stiger.aliyun.sms.exception.StarTigerAliyunSmsException;
import com.snake19870227.stiger.sms.SmsClient;
import com.snake19870227.stiger.sms.entity.po.SmsLog;
import com.snake19870227.stiger.sms.entity.po.SmsTemplate;
import com.snake19870227.stiger.sms.exception.StarTigerSmsException;

/**
 * @author BuHuaYang
 * 2021/1/2
 */
public class StarTigerAliyunSmsClient implements SmsClient {

    private static final Logger logger = LoggerFactory.getLogger(StarTigerAliyunSmsClient.class);

    public static final String CHANNEL = "aliyun";

    private final DefaultProfile profile;

    private final IAcsClient client;

    public StarTigerAliyunSmsClient(DefaultProfile profile) {
        this.profile = profile;
        this.client = new DefaultAcsClient(profile);
    }

    @Override
    public void sendSms(SmsLog smsLog) {
        smsLog.setSendDatetime(LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));
        try {
            SmsLog resultLog = sendSms(smsLog.getPhone(), smsLog.getSignName(), smsLog.getSendParam(), smsLog.getTemplateCode());
            smsLog.setStatus(resultLog.getStatus());
            smsLog.setSendId(resultLog.getSendId());
            smsLog.setSendCode(resultLog.getSendCode());
            smsLog.setSendMsg(resultLog.getSendMsg());
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
    public SmsLog sendSms(String phoneNumber, String signName, String params, String templateCode) throws StarTigerSmsException {
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phoneNumber);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam(params);
        try {
            SmsLog smsLog = new SmsLog();
            SendSmsResponse response = client.getAcsResponse(request);
            logger.info("阿里云短信发送结果:[{}][{}]", response.getCode(), response.getMessage());
            smsLog.setSendCode(response.getCode());
            smsLog.setSendMsg(response.getMessage());
            if (StrUtil.equalsIgnoreCase(StarTigerAliyunSmsConstent.RESPONSE_CODE_OK, response.getCode())) {
                smsLog.setPhone(phoneNumber);
                smsLog.setChannel(CHANNEL);
                smsLog.setSignName(signName);
                smsLog.setTemplateCode(templateCode);
                smsLog.setSendParam(params);
                smsLog.setCreateDatetime(LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));
                smsLog.setStatus(1);
                smsLog.setSendId(response.getBizId());
            } else {
                smsLog.setStatus(2);
            }
            return smsLog;
        } catch (ClientException e) {
            throw new StarTigerAliyunSmsException(e);
        } catch (Exception e) {
            String msg = StrUtil.format("阿里云短信发送失败[{}|{}|{}|{}]", phoneNumber, signName, templateCode, params);
            throw new StarTigerSmsException(msg, e);
        }
    }

    @Override
    public SmsLog sendSms(String phoneNumber, String signName, Map<String, Object> params, String templateCode) throws StarTigerSmsException {
        return sendSms(phoneNumber, signName, JSONUtil.toJsonStr(params), templateCode);
    }

    @Override
    public SmsTemplate queryTemplate(String templateCode) {
        QuerySmsTemplateRequest request = new QuerySmsTemplateRequest();
        request.setTemplateCode(templateCode);
        try {
            QuerySmsTemplateResponse response = client.getAcsResponse(request);
            if (StrUtil.equalsIgnoreCase(StarTigerAliyunSmsConstent.RESPONSE_CODE_OK, response.getCode())) {
                SmsTemplate template = new SmsTemplate();
                template.setChannel(CHANNEL);
                template.setTemplateCode(response.getTemplateCode());
                template.setTemplateName(response.getTemplateName());
                template.setTemplateType(String.valueOf(response.getTemplateType()));
                template.setTemplateStatus(String.valueOf(response.getTemplateStatus()));
                template.setTemplateContent(response.getTemplateContent());
                template.setRemark(response.getReason());
                LocalDateTime createDatetime = LocalDateTimeUtil.parse(response.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
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
}
