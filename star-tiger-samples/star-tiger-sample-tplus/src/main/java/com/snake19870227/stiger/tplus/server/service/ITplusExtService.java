package com.snake19870227.stiger.tplus.server.service;

import java.util.List;

import com.snake19870227.stiger.tplus.entity.po.ViewUserMenu;

/**
 * @author BuHuaYang
 * 8/27 027
 */
public interface ITplusExtService {

    List<ViewUserMenu> allMenusWithUser(String userId);
}
