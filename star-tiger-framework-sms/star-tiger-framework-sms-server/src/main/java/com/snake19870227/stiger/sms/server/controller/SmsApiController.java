package com.snake19870227.stiger.sms.server.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.sms.SendReq;
import com.snake19870227.stiger.sms.SmsClient;
import com.snake19870227.stiger.sms.entity.po.SmsLog;
import com.snake19870227.stiger.sms.entity.po.SmsTemplate;
import com.snake19870227.stiger.sms.service.ISmsLogService;
import com.snake19870227.stiger.sms.service.ISmsTemplateService;
import com.snake19870227.stiger.web.StarTigerWebConstant;
import com.snake19870227.stiger.web.exception.MvcException;
import com.snake19870227.stiger.web.restful.RestResp;

/**
 * @author BuHuaYang
 * 2021/1/4
 */
@Api(tags = "短信服务")
@RestController
@RequestMapping(path = "/sms/v1")
public class SmsApiController {

    private static final Logger logger = LoggerFactory.getLogger(SmsApiController.class);

    @Autowired(required = false)
    private SmsClient smsClient;

    private final ISmsLogService smsLogService;
    private final ISmsTemplateService smsTemplateService;

    public SmsApiController(ISmsLogService smsLogService, ISmsTemplateService smsTemplateService) {
        this.smsLogService = smsLogService;
        this.smsTemplateService = smsTemplateService;
    }

    @ApiOperation("发送短信")
    @PostMapping(path = "/aliyun/send")
    public RestResp<?> send(@RequestBody SendReq req) {
        try {

            QueryWrapper<SmsTemplate> wrapper = new QueryWrapper<>();
            wrapper.eq("channel", smsClient.getChannel())
                    .eq("template_code", req.getTemplateCode());
            SmsTemplate template = smsTemplateService.getOne(wrapper);

            if (template == null) {
                template = smsClient.queryTemplate(req.getTemplateCode());
                if (template == null) {
                    return RestResp.buildResp("20001");
                }
                smsTemplateService.save(template);
            }

            SmsLog smsLog = new SmsLog();
            smsLog.setPhone(req.getPhoneNumber());
            smsLog.setChannel(smsClient.getChannel());
            smsLog.setSignName(req.getSignName());
            smsLog.setTemplateId(template.getId());
            smsLog.setTemplateCode(template.getTemplateCode());
            smsLog.setTemplateName(template.getTemplateName());
            smsLog.setSendParam(JSONUtil.toJsonStr(req.getParams()));
            smsLog.setCreateDatetime(LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));
            smsLogService.save(smsLog);

            return RestResp.ok();
        } catch (Exception e) {
            throw new MvcException(e);
        }
    }
}
