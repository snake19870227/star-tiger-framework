package com.snake19870227.stiger.sms.server.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.sms.SendReq;
import com.snake19870227.stiger.sms.SmsClient;
import com.snake19870227.stiger.sms.entity.po.SmsLog;
import com.snake19870227.stiger.sms.entity.po.SmsTemplate;
import com.snake19870227.stiger.sms.server.StarTigerSmsServerConstent;
import com.snake19870227.stiger.sms.service.ISmsLogService;
import com.snake19870227.stiger.sms.service.ISmsTemplateService;
import com.snake19870227.stiger.web.exception.MvcException;
import com.snake19870227.stiger.web.restful.RestResp;

/**
 * @author BuHuaYang
 * 2021/1/4
 */
@Api(tags = "短信服务")
@RestController
@RequestMapping(path = "/sms")
public class SmsApiController {

    private static final Logger logger = LoggerFactory.getLogger(SmsApiController.class);

    @Autowired
    private SmsClient smsClient;

    private final ISmsLogService smsLogService;
    private final ISmsTemplateService smsTemplateService;

    public SmsApiController(ISmsLogService smsLogService, ISmsTemplateService smsTemplateService) {
        this.smsLogService = smsLogService;
        this.smsTemplateService = smsTemplateService;
    }

    @ApiOperation("异步发送短信")
    @PostMapping(path = "/asyncSend")
    public RestResp<?> asyncSend(@RequestBody SendReq req) {
        try {

            QueryWrapper<SmsTemplate> wrapper = new QueryWrapper<>();
            wrapper.eq("channel", smsClient.getChannel())
                    .eq("template_code", req.getTemplateCode());
            SmsTemplate template = smsTemplateService.getOne(wrapper);

            if (template == null) {
                template = smsClient.queryTemplate(req.getTemplateCode());
                if (template == null) {
                    return RestResp.okByCode(StarTigerSmsServerConstent.StatusCode.PREFIX_CODE, StarTigerSmsServerConstent.StatusCode.CODE_4001);
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

    @ApiOperation("同步发送短信")
    @PostMapping(path = "/syncSend")
    public RestResp<SmsLog> syncSend(@RequestBody SendReq req) {
        try {

            QueryWrapper<SmsTemplate> wrapper = new QueryWrapper<>();
            wrapper.eq("channel", smsClient.getChannel())
                    .eq("template_code", req.getTemplateCode());
            SmsTemplate template = smsTemplateService.getOne(wrapper);

            if (template == null) {
                template = smsClient.queryTemplate(req.getTemplateCode());
                if (template == null) {
                    return RestResp.okByCode(StarTigerSmsServerConstent.StatusCode.PREFIX_CODE, StarTigerSmsServerConstent.StatusCode.CODE_4001);
                }
                smsTemplateService.save(template);
            }

            SmsLog smsLog = smsClient.sendSms(req.getPhoneNumber(), req.getSignName(), req.getParams(), req.getTemplateCode());
            smsLog.setTemplateId(template.getId());
            smsLog.setTemplateName(template.getTemplateName());
            smsLogService.save(smsLog);

            return RestResp.okWithData(smsLog);
        } catch (Exception e) {
            throw new MvcException(e);
        }
    }

    @ApiOperation("查询发送状态")
    @GetMapping(path = "/sendStatus")
    public RestResp<SmsLog> sendStatus(@RequestParam(name = "sendId") String sendId) {
        try {

            QueryWrapper<SmsLog> wrapper = new QueryWrapper<>();
            wrapper.eq("send_id", sendId);
            SmsLog smsLog = smsLogService.getOne(wrapper, false);

            if (smsLog == null) {
                return RestResp.okByCode(StarTigerSmsServerConstent.StatusCode.PREFIX_CODE, StarTigerSmsServerConstent.StatusCode.CODE_4002);
            }

            return RestResp.okWithData(smsLog);

        } catch (Exception e) {
            throw new MvcException(e);
        }
    }
}
