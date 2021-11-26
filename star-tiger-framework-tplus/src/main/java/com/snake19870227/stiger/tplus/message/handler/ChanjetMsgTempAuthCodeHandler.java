package com.snake19870227.stiger.tplus.message.handler;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.tplus.ChanjetConstant;
import com.snake19870227.stiger.tplus.entity.dto.ChanjetMsg;
import com.snake19870227.stiger.tplus.entity.po.SysCfg;
import com.snake19870227.stiger.tplus.message.BaseChanjetMsgHandler;
import com.snake19870227.stiger.tplus.message.ChanjetMsgHandlerFactory;
import com.snake19870227.stiger.tplus.message.ChanjetMsgType;
import com.snake19870227.stiger.tplus.service.ISysCfgService;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2021/05/06
 */
@Component
public class ChanjetMsgTempAuthCodeHandler extends BaseChanjetMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(ChanjetMsgTempAuthCodeHandler.class);

    public static final String MSG_TYPE = ChanjetMsgType.TEMP_AUTH_CODE.getId();

    static {
        ChanjetMsgHandlerFactory.CHANJET_MSG_HANDLER_MAP.put(MSG_TYPE, ChanjetMsgTempAuthCodeHandler.class);
    }

    protected final ISysCfgService sysCfgService;

    public ChanjetMsgTempAuthCodeHandler(ISysCfgService sysCfgService) {
        this.sysCfgService = sysCfgService;
    }

    @Override
    protected void doHandle(ChanjetMsg chanjetMsg) {
        logger.info("收到消息企业临时授权码消息\n{}", chanjetMsg.toString());
        if (StrUtil.isNotBlank(chanjetMsg.getBizContent())) {
            JSONObject obj = JSONUtil.parseObj(chanjetMsg.getBizContent());
            String tempAuthCode = obj.getStr("tempAuthCode");
            if (StrUtil.isNotBlank(tempAuthCode)) {
                SysCfg sysCfg = new SysCfg();
                sysCfg.setCfgCode(ChanjetConstant.CFG_KEY_TEMP_AUTH_CODE);
                sysCfg.setCfgValue(tempAuthCode);
                sysCfg.setRecordStatus(StarTigerConstant.FLAG_Y);
                sysCfg.setLastModifyTime(LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));
                sysCfgService.saveOrUpdate(sysCfg);
            }
        }
    }
}
