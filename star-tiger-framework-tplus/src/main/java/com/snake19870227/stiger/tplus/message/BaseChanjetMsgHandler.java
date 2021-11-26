package com.snake19870227.stiger.tplus.message;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import com.snake19870227.stiger.tplus.entity.dto.ChanjetMsg;
import com.snake19870227.stiger.tplus.entity.po.TpMessage;
import com.snake19870227.stiger.tplus.service.ITpMessageService;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2021/05/06
 */
public abstract class BaseChanjetMsgHandler implements ChanjetMsgHandler {

    @Autowired
    protected ITpMessageService tpMessageService;

    @Override
    public void handle(ChanjetMsg chanjetMsg) {
        saveMessage(chanjetMsg);
        doHandle(chanjetMsg);
    }

    protected abstract void doHandle(ChanjetMsg chanjetMsg);

    protected void saveMessage(ChanjetMsg chanjetMsg) {
        TpMessage tpMessage = new TpMessage();
        tpMessage.setFlow(IdUtil.fastSimpleUUID());
        tpMessage.setMsgId(chanjetMsg.getId());
        tpMessage.setAppKey(chanjetMsg.getAppKey());
        tpMessage.setMsgType(chanjetMsg.getMsgType());
        tpMessage.setMsgTime(chanjetMsg.getMsgTime());
        tpMessage.setBizContent(chanjetMsg.getBizContent());
        String nowString = LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss");
        tpMessage.setCreateTime(nowString);
        tpMessage.setModifyTime(nowString);
        tpMessageService.save(tpMessage);
    }

    public ITpMessageService getTpMessageService() {
        return tpMessageService;
    }

    public void setTpMessageService(ITpMessageService tpMessageService) {
        this.tpMessageService = tpMessageService;
    }
}
