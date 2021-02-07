package com.snake19870227.stiger.sms.server.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.sms.entity.po.SmsLog;
import com.snake19870227.stiger.sms.service.ISmsLogService;

/**
 * @author BuHuaYang
 * 2021/1/5
 */
@RestController
@RequestMapping(path = "/aliyun/sms")
public class SmsAliyunReportController {

    private static final Logger logger = LoggerFactory.getLogger(SmsAliyunReportController.class);

    private final ISmsLogService smsLogService;

    public SmsAliyunReportController(ISmsLogService smsLogService) {
        this.smsLogService = smsLogService;
    }

    @PostMapping(path = "/{channel}/report")
    @ResponseBody
    public Map<String, Object> report(@PathVariable(name = "channel") String channel,
                                      @RequestBody String reqStr) {
        logger.info("收到阿里云短信发送状态回执[{}]", reqStr);
        if (JSONUtil.isJsonArray(reqStr)) {
            JSONArray objects = JSONUtil.parseArray(reqStr);
            for (JSONObject o : objects.jsonIter()) {
                String phoneNumber = o.getStr("phone_number");
                String sendTime = o.getStr("send_time");
                String reportTime = o.getStr("report_time");
                boolean success = o.getBool("success", false);
                String errCode = o.getStr("err_code");
                String errMsg = o.getStr("err_msg");
                String smsSize = o.getStr("sms_size");
                String bizId = o.getStr("biz_id");
                String outId = o.getStr("out_id");
                QueryWrapper<SmsLog> wrapper = new QueryWrapper<>();
                wrapper.eq("send_id", bizId)
                    .eq("channel", "channel");
                SmsLog smsLog = smsLogService.getOne(wrapper);
                if (smsLog == null) {
                    logger.warn("未找到本地发送记录[{}|{}|{}]", phoneNumber, bizId, errMsg);
                    continue;
                }
                LocalDateTime reportDatetime = LocalDateTimeUtil.parse(reportTime, "yyyy-MM-dd HH:mm:ss");
                smsLog.setReportDatetime(LocalDateTimeUtil.format(reportDatetime, "yyyyMMddHHmmss"));
                smsLog.setReportSuccess(success ? 1 : 0);
                smsLog.setReportCode(errCode);
                smsLog.setReportMsg(errMsg);
                smsLog.setSmsSize(Integer.parseInt(smsSize));
                if (success) {
                    smsLog.setStatus(3);
                } else {
                    smsLog.setStatus(4);
                }
                smsLogService.updateById(smsLog);
            }
        }
        return MapUtil.of("code", 0);
    }
}
