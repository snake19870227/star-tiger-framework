package com.snake19870227.stiger.tplus.server.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.snake19870227.stiger.tplus.entity.po.ViewUserMenu;

/**
 * @author BuHuaYang
 * 8/27 027
 */
public interface ExtMapper {

    List<ViewUserMenu> allMenusWithUser(@Param("userId") String userId);
}
