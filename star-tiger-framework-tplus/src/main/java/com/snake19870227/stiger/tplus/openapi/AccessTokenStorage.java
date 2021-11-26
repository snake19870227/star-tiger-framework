package com.snake19870227.stiger.tplus.openapi;

import com.snake19870227.stiger.tplus.entity.po.TpToken;

public interface AccessTokenStorage {

    TpToken getAccessToken();

    TpToken getAccessToken(String userId);
}
