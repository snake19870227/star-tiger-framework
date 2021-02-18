package com.snake19870227.stiger.pay.channel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snake19870227.stiger.core.utils.JsonUtil;

/**
 * @author BuHuaYang
 * 2020/9/28
 */
public interface ChannelClientParam {

    default String toJson() throws JsonProcessingException {
        return JsonUtil.buildJacksonObjectMapper().writeValueAsString(this);
    }
}
