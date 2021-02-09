package com.snake19870227.stiger.aliyun.dypls.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxbResponse;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnResponse;
import com.aliyuncs.dyplsapi.model.v20170525.UnbindSubscriptionResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.aliyun.dypls.AliyunDyplsClient;
import com.snake19870227.stiger.aliyun.dypls.entity.po.AliDyplsBind;
import com.snake19870227.stiger.aliyun.dypls.enums.DyplsTypeEnum;
import com.snake19870227.stiger.aliyun.dypls.service.IAliDyplsBindService;
import com.snake19870227.stiger.core.utils.FormatUtil;
import com.snake19870227.stiger.web.exception.MvcException;
import com.snake19870227.stiger.web.restful.RestResp;

/**
 * @author BuHuaYang
 * 2021/1/25
 */
@Api(tags = "匿名电话服务")
@RestController
@RequestMapping(path = "/aliyun/dypls/v1")
public class DyplsController {

    private static final Logger logger = LoggerFactory.getLogger(DyplsController.class);

    @Value("${mmk.admin.aliyun.dypls.axn.pool-key:}")
    private String defaultPoolKey;

    @Autowired(required = false)
    private AliyunDyplsClient aliyunDyplsClient;

    private final IAliDyplsBindService aliDyplsBindService;

    public DyplsController(IAliDyplsBindService aliDyplsBindService) {
        this.aliDyplsBindService = aliDyplsBindService;
    }

    @ApiOperation("AXB绑定")
    @GetMapping(path = "/axb/bind")
    public RestResp<?> axbBind(@RequestParam(name = "a") String phonea,
                               @RequestParam(name = "b") String phoneb,
                               @RequestParam(name = "x", required = false) String phonex,
                               @RequestParam(name = "simpleExpireTime", required = false) String simpleExpireTime,
                               @RequestParam(name = "poolKey", required = false) String poolKey,
                               @RequestParam(name = "record", defaultValue = "false") boolean isRecordingEnabled,
                               @RequestParam(name = "city", required = false) String city,
                               @RequestParam(name = "outId", required = false) String outId) {

        try {

            String expireTime;

            if (StrUtil.isBlank(poolKey)) {
                poolKey = defaultPoolKey;
            }

            if (StrUtil.isBlank(simpleExpireTime)) {
                LocalDateTime time = LocalDateTime.now().plusMinutes(10);
                expireTime = LocalDateTimeUtil.format(time, "yyyy-MM-dd HH:mm:ss");
            } else {
                expireTime = FormatUtil.transformDatetime(simpleExpireTime, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
            }

            BindAxbResponse.SecretBindDTO bind = aliyunDyplsClient.axbBind(phonea, phoneb, phonex, expireTime, poolKey, isRecordingEnabled, city, outId);

            if (bind != null) {

                AliDyplsBind dyplsBind = new AliDyplsBind();

                dyplsBind.setId(IdUtil.fastSimpleUUID());
                dyplsBind.setSubId(bind.getSubsId());
                dyplsBind.setSubType(DyplsTypeEnum.BindAxb.getId());
                dyplsBind.setPhonea(phonea);
                dyplsBind.setPhoneb(phoneb);
                dyplsBind.setPhonex(bind.getSecretNo());
                dyplsBind.setPoolKey(poolKey);
                dyplsBind.setExpireTime(FormatUtil.transformDatetime(expireTime, "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
                dyplsBind.setIsRecording(isRecordingEnabled ? 1 : 0);
                dyplsBind.setCity(city);
                dyplsBind.setStatus(1);
                dyplsBind.setOutId(outId);
                aliDyplsBindService.save(dyplsBind);

                return RestResp.okWithData(dyplsBind);
            } else {
                throw new MvcException("绑定失败");
            }
        } catch (MvcException e) {
            throw e;
        } catch (Exception e) {
            throw new MvcException(e);
        }
    }

    @ApiOperation("AXN绑定")
    @GetMapping(path = "/axn/bind")
    public RestResp<?> axnBind(@RequestParam(name = "a") String phonea,
                               @RequestParam(name = "b", required = false) String phoneb,
                               @RequestParam(name = "x", required = false) String phonex,
                               @RequestParam(name = "simpleExpireTime", required = false) String simpleExpireTime,
                               @RequestParam(name = "poolKey", required = false) String poolKey,
                               @RequestParam(name = "record", defaultValue = "false") boolean isRecordingEnabled,
                               @RequestParam(name = "city", required = false) String city,
                               @RequestParam(name = "outId", required = false) String outId) {

        try {

            String expireTime;

            if (StrUtil.isBlank(poolKey)) {
                poolKey = defaultPoolKey;
            }

            if (StrUtil.isBlank(simpleExpireTime)) {
                LocalDateTime time = LocalDateTime.now().plusMinutes(10);
                expireTime = LocalDateTimeUtil.format(time, "yyyy-MM-dd HH:mm:ss");
            } else {
                expireTime = FormatUtil.transformDatetime(simpleExpireTime, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
            }

            BindAxnResponse.SecretBindDTO bind = aliyunDyplsClient.axnBind(phonea, phoneb, phonex, expireTime, poolKey, isRecordingEnabled, city, outId);

            if (bind != null) {

                AliDyplsBind dyplsBind = new AliDyplsBind();

                dyplsBind.setId(IdUtil.fastSimpleUUID());
                dyplsBind.setSubId(bind.getSubsId());
                dyplsBind.setSubType(DyplsTypeEnum.BindAxn.getId());
                dyplsBind.setPhonea(phonea);
                dyplsBind.setPhoneb(phoneb);
                dyplsBind.setPhonex(bind.getSecretNo());
                dyplsBind.setPoolKey(poolKey);
                dyplsBind.setExpireTime(FormatUtil.transformDatetime(expireTime, "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
                dyplsBind.setIsRecording(isRecordingEnabled ? 1 : 0);
                dyplsBind.setCity(city);
                dyplsBind.setStatus(1);
                dyplsBind.setOutId(outId);
                aliDyplsBindService.save(dyplsBind);

                return RestResp.okWithData(dyplsBind);
            } else {
                throw new MvcException("绑定失败");
            }
        } catch (MvcException e) {
            throw e;
        } catch (Exception e) {
            throw new MvcException(e);
        }
    }

    private AliDyplsBind getExistBind(String phonea, String phoneb) {
        QueryWrapper<AliDyplsBind> wrapper = new QueryWrapper<>();
        wrapper.in("status", 0, 1)
                .eq("phonea", phonea)
                .eq("phoneb", phoneb);
        List<AliDyplsBind> binds = aliDyplsBindService.list(wrapper);
        if (CollUtil.isNotEmpty(binds)) {
            return binds.get(0);
        }
        return null;
    }

    @ApiOperation("解绑")
    @GetMapping(path = "/unbind")
    public RestResp<?> bind(@RequestParam(name = "x") String phonex,
                            @RequestParam(name = "subId") String subId,
                            @RequestParam(name = "poolKey", required = false) String poolKey) {

        if (StrUtil.isBlank(poolKey)) {
            poolKey = defaultPoolKey;
        }

        try {

            UnbindSubscriptionResponse response = aliyunDyplsClient.unbind(phonex, subId, poolKey);

            if (StrUtil.equalsIgnoreCase("OK", response.getCode())) {
                return RestResp.ok();
            }

            return RestResp.failure(response.getCode() + " - " + response.getMessage());
        } catch (Exception e) {
            throw new MvcException(e);
        }
    }
}
