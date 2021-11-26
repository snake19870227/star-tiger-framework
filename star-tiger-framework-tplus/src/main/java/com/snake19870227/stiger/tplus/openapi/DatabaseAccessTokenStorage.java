package com.snake19870227.stiger.tplus.openapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.tplus.ChanjetConstant;
import com.snake19870227.stiger.tplus.entity.po.TpToken;
import com.snake19870227.stiger.tplus.service.ITpTokenService;

@Component
public class DatabaseAccessTokenStorage implements AccessTokenStorage{

    private static final Logger logger = LoggerFactory.getLogger(DatabaseAccessTokenStorage.class);

    private final ITpTokenService tpTokenService;

    public DatabaseAccessTokenStorage(ITpTokenService tpTokenService) {
        this.tpTokenService = tpTokenService;
    }

    @Override
    public TpToken getAccessToken() {
        return tpTokenService.getById(ChanjetConstant.FLOW_ACCESS_TOKEN);
    }

    @Override
    public TpToken getAccessToken(String userId) {
        QueryWrapper<TpToken> wrapper = new QueryWrapper<>();
        wrapper.eq("USER_ID", userId);
        return tpTokenService.getOne(wrapper);
    }
}
