package com.snake19870227.stiger.tplus.message;

import java.util.HashMap;
import java.util.Map;

import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.tplus.entity.dto.ChanjetMsg;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2021/05/08
 */
public class ChanjetMsgHandlerFactory {

    public static Map<String, Class<? extends ChanjetMsgHandler>> CHANJET_MSG_HANDLER_MAP = new HashMap<>();

    public static ChanjetMsgHandler getHandler(ChanjetMsg chanjetMsg) {
        Class<? extends ChanjetMsgHandler> clazz = CHANJET_MSG_HANDLER_MAP.get(chanjetMsg.getMsgType());
        if (clazz != null) {
            return StarTigerContext.getBean(clazz);
        }
        return null;
    }
}
