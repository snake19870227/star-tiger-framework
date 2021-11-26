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
import com.snake19870227.stiger.tplus.service.ITpAppTicketService;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2021/05/06
 */
@Component
public class ChanjetMsgAppTicketHandler extends BaseChanjetMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(ChanjetMsgAppTicketHandler.class);

    public static final String MSG_TYPE = ChanjetMsgType.APP_TICKET.getId();

    static {
        ChanjetMsgHandlerFactory.CHANJET_MSG_HANDLER_MAP.put(MSG_TYPE, ChanjetMsgAppTicketHandler.class);
    }

    protected final ITpAppTicketService tpAppTicketService;

    protected final ISysCfgService sysCfgService;

    public ChanjetMsgAppTicketHandler(ITpAppTicketService tpAppTicketService, ISysCfgService sysCfgService) {
        this.tpAppTicketService = tpAppTicketService;
        this.sysCfgService = sysCfgService;
    }

    @Override
    protected void doHandle(ChanjetMsg chanjetMsg) {
        logger.info("收到消息AppTicket消息\n{}", chanjetMsg.toString());
        if (StrUtil.isNotBlank(chanjetMsg.getBizContent())) {
            JSONObject obj = JSONUtil.parseObj(chanjetMsg.getBizContent());
            String appTicket = obj.getStr("appTicket");
            if (StrUtil.isNotBlank(appTicket)) {
//                TpAppTicket tpAppTicket = new TpAppTicket();
//                tpAppTicket.setFlow(IdUtil.fastSimpleUUID());
//                tpAppTicket.setAppTicket(appTicket);
                String nowString = LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss");
//                tpAppTicket.setCreateTime(nowString);
//                tpAppTicket.setModifyTime(nowString);
//                tpAppTicketService.save(tpAppTicket);

                SysCfg sysCfg = new SysCfg();
                sysCfg.setCfgCode(ChanjetConstant.CFG_KEY_APP_TICKET);
                sysCfg.setCfgValue(appTicket);
                sysCfg.setRecordStatus(StarTigerConstant.FLAG_Y);
                sysCfg.setLastModifyTime(nowString);
                sysCfgService.saveOrUpdate(sysCfg);
            }
        }
    }
}
