package com.snake19870227.stiger.aliyun.dypls.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.aliyuncs.dyplsapi.model.v20170525.QueryRecordFileDownloadUrlResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.aliyun.dypls.AliyunDyplsClient;
import com.snake19870227.stiger.aliyun.dypls.entity.dto.SecretRecording;
import com.snake19870227.stiger.aliyun.dypls.entity.dto.SecretReport;
import com.snake19870227.stiger.aliyun.dypls.entity.dto.SecretStartReport;
import com.snake19870227.stiger.aliyun.dypls.entity.po.AliDyplsBind;
import com.snake19870227.stiger.aliyun.dypls.entity.po.AliDyplsCall;
import com.snake19870227.stiger.aliyun.dypls.event.SecretRecordingEvent;
import com.snake19870227.stiger.aliyun.dypls.event.SecretReportEvent;
import com.snake19870227.stiger.aliyun.dypls.event.SecretStartReportEvent;
import com.snake19870227.stiger.aliyun.dypls.service.IAliDyplsBindService;
import com.snake19870227.stiger.aliyun.dypls.service.IAliDyplsCallService;
import com.snake19870227.stiger.core.context.StarTigerContext;

/**
 * @author BuHuaYang
 * 2021/1/25
 */
@RestController
@RequestMapping(path = "/aliyun/dypls/axb/report")
public class DyplsReportController {

    private static final Logger logger = LoggerFactory.getLogger(DyplsReportController.class);

    @Value("${mmk.admin.aliyun.dypls.axb.pool-key:}")
    private String defaultPoolKey;

    @Autowired(required = false)
    private AliyunDyplsClient aliyunDyplsClient;

    private final IAliDyplsBindService aliDyplsBindService;
    private final IAliDyplsCallService aliDyplsCallService;

    public DyplsReportController(IAliDyplsBindService aliDyplsBindService, IAliDyplsCallService aliDyplsCallService) {
        this.aliDyplsBindService = aliDyplsBindService;
        this.aliDyplsCallService = aliDyplsCallService;
    }

    @PostMapping(path = "/SecretStartReport")
    @ResponseBody
    public Map<String, Object> secretStartReport(@RequestBody List<SecretStartReport> reports) {

        if (CollUtil.isNotEmpty(reports)) {
            for (SecretStartReport report : reports) {
                logger.info("开始呼叫:[{}]", JSONUtil.toJsonStr(report));
                StarTigerContext.publishEvent(new SecretStartReportEvent(report));
            }
        }

        return MapUtil.of("code", 0);
    }

    @PostMapping(path = "/SecretReport")
    @ResponseBody
    public Map<String, Object> secretReport(@RequestBody List<SecretReport> reports) {

        if (CollUtil.isNotEmpty(reports)) {
            for (SecretReport report : reports) {
                try {
                    String reportStr = JSONUtil.toJsonStr(report);
                    logger.info("通话结束:[{}]", reportStr);
                    AliDyplsCall dyplsCall = new AliDyplsCall();
                    dyplsCall.setSubId(report.getSubId());
                    dyplsCall.setCallId(report.getCallId());
                    dyplsCall.setPhonea(report.getPhoneNo());
                    dyplsCall.setPhoneb(report.getPeerNo());
                    dyplsCall.setPhonex(report.getSecretNo());
                    dyplsCall.setPoolKey(report.getPoolKey());
                    dyplsCall.setCallType(report.getCallType());
                    dyplsCall.setCallTime(report.getCallTime());
                    dyplsCall.setStartTime(report.getStartTime());
                    dyplsCall.setCallOutTime(report.getCallOutTime());
                    dyplsCall.setRingTime(report.getRingTime());
                    dyplsCall.setFreeRingTime(report.getFreeRingTime());
                    dyplsCall.setReleaseTime(report.getReleaseTime());
                    dyplsCall.setReleaseDir(report.getReleaseDir());
                    dyplsCall.setUnconnectedCause(report.getUnconnectedCause());
                    dyplsCall.setReleaseCause(report.getReleaseCause());
                    dyplsCall.setOutId(report.getOutId());
                    dyplsCall.setInfo(reportStr);
                    aliDyplsCallService.save(dyplsCall);
                    StarTigerContext.publishEvent(new SecretReportEvent(dyplsCall));
                } catch (Exception e) {
                    logger.error("记录通话报告失败", e);
                }
            }
        }

        return MapUtil.of("code", 0);
    }

    @PostMapping(path = "/SecretRecording")
    @ResponseBody
    public Map<String, Object> secretRecording(@RequestBody List<SecretRecording> reports) {

        if (CollUtil.isNotEmpty(reports)) {
            for (SecretRecording report : reports) {
                try {
                    logger.info("录制结束:[{}]", JSONUtil.toJsonStr(report));
                    LocalDateTime time = LocalDateTimeUtil.of(report.getCallTime());
                    String callTime = LocalDateTimeUtil.format(time, "yyyy-MM-dd HH:mm:ss");
                    AliDyplsCall call = getCallByCallId(report.getCallId());
                    QueryRecordFileDownloadUrlResponse urlResponse = aliyunDyplsClient.queryRecordFileDownloadUrl(report.getCallId(), callTime, report.getPoolKey());
                    if (urlResponse != null && StrUtil.isNotBlank(urlResponse.getDownloadUrl())) {
                        logger.info("录制文件下载地址:{}", urlResponse.getDownloadUrl());
                        if (call != null) {
                            call.setRecordTime(callTime);
                            call.setRecordFileUrl(urlResponse.getDownloadUrl());
                            aliDyplsCallService.updateById(call);
                            StarTigerContext.publishEvent(new SecretRecordingEvent(new SecretRecordingEvent.SecretRecordInfo(call, urlResponse.getDownloadUrl())));
                        }
                    } else {
                        logger.warn("未得到录制下载地址,响应:[\n{}\n]", JSONUtil.toJsonStr(urlResponse));
                    }
                } catch (Exception e) {
                    logger.error("记录录音报告失败", e);
                }
            }
        }

        return MapUtil.of("code", 0);
    }

    private AliDyplsBind getBindBySubId(String subId) {
        QueryWrapper<AliDyplsBind> bindWrapper = new QueryWrapper<>();
        bindWrapper.eq("sub_id", subId);
        List<AliDyplsBind> list = aliDyplsBindService.list(bindWrapper);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    private AliDyplsCall getCallByCallId(String callId) {
        QueryWrapper<AliDyplsCall> bindWrapper = new QueryWrapper<>();
        bindWrapper.eq("call_id", callId);
        List<AliDyplsCall> list = aliDyplsCallService.list(bindWrapper);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}
