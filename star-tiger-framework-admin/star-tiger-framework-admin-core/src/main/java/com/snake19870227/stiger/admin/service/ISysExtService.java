package com.snake19870227.stiger.admin.service;

import java.util.List;

import com.snake19870227.stiger.admin.common.TreeNode;
import com.snake19870227.stiger.admin.entity.bo.UserInfo;
import com.snake19870227.stiger.admin.entity.po.SysCfg;
import com.snake19870227.stiger.admin.entity.po.SysMenu;
import com.snake19870227.stiger.admin.entity.po.SysRole;
import com.snake19870227.stiger.admin.entity.po.SysUser;
import com.snake19870227.stiger.admin.security.UserSecurityDetail;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/07/21
 */
public interface ISysExtService {

    SysCfg findAndCacheSysCfg(String cfgCode);

    UserInfo getUserInfo(String username);

    List<TreeNode<SysMenu>> treeMenu();

    List<TreeNode<SysMenu>> treeMenu(UserSecurityDetail userSecurityDetail);

    boolean addRole(SysRole role, List<String> resourceFlows);

    boolean modRole(SysRole role, List<String> resourceFlows);

    List<String> getResourceFlowsByRole(String roleFlow);

    List<String> getRoleFlowsByUser(String userFlow);

    boolean addUser(SysUser user, List<String> roleFlows);

    boolean modUser(SysUser user, List<String> roleFlows);

    boolean resetUserPassword(String userFlow);
}
