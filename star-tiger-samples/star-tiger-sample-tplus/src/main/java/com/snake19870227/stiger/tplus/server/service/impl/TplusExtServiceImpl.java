package com.snake19870227.stiger.tplus.server.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.snake19870227.stiger.tplus.entity.po.ViewUserMenu;
import com.snake19870227.stiger.tplus.server.dao.ext.ExtMapper;
import com.snake19870227.stiger.tplus.server.service.ITplusExtService;

/**
 * @author BuHuaYang
 * 8/27 027
 */
@Service
public class TplusExtServiceImpl implements ITplusExtService {

    private final ExtMapper extMapper;

    public TplusExtServiceImpl(ExtMapper extMapper) {
        this.extMapper = extMapper;
    }

    @Override
    public List<ViewUserMenu> allMenusWithUser(String userId) {
        return extMapper.allMenusWithUser(userId);
    }
}
