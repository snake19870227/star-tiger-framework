package com.snake19870227.stiger.tplus.message.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.snake19870227.stiger.tplus.entity.dto.ChanjetMsg;
import com.snake19870227.stiger.tplus.message.BaseChanjetMsgHandler;
import com.snake19870227.stiger.tplus.message.ChanjetMsgHandlerFactory;
import com.snake19870227.stiger.tplus.message.ChanjetMsgType;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2021/05/06
 */
@Component
public class ChanjetMsgAppTestHandler extends BaseChanjetMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(ChanjetMsgAppTestHandler.class);

    public static final String MSG_TYPE = ChanjetMsgType.APP_TEST.getId();

    static {
        ChanjetMsgHandlerFactory.CHANJET_MSG_HANDLER_MAP.put(MSG_TYPE, ChanjetMsgAppTestHandler.class);
    }

    @Override
    protected void doHandle(ChanjetMsg chanjetMsg) {
        logger.info("收到消息地址验证消息\n{}", chanjetMsg.toString());
    }
}
