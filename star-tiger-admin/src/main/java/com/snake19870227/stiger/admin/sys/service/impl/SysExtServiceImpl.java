package com.snake19870227.stiger.admin.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.admin.common.TreeNode;
import com.snake19870227.stiger.admin.dao.base.SysMenuMapper;
import com.snake19870227.stiger.admin.dao.base.SysRoleMapper;
import com.snake19870227.stiger.admin.dao.base.SysRoleResourceMapper;
import com.snake19870227.stiger.admin.dao.base.SysUserMapper;
import com.snake19870227.stiger.admin.dao.base.SysUserRoleMapper;
import com.snake19870227.stiger.admin.dao.ext.SysExtMapper;
import com.snake19870227.stiger.admin.entity.bo.UserInfo;
import com.snake19870227.stiger.admin.entity.po.SysMenu;
import com.snake19870227.stiger.admin.entity.po.SysResource;
import com.snake19870227.stiger.admin.entity.po.SysRole;
import com.snake19870227.stiger.admin.entity.po.SysRoleResource;
import com.snake19870227.stiger.admin.entity.po.SysUser;
import com.snake19870227.stiger.admin.entity.po.SysUserRole;
import com.snake19870227.stiger.admin.security.UserSecurityDetail;
import com.snake19870227.stiger.admin.sys.service.ISysExtService;
import com.snake19870227.stiger.core.StarTigerConstant;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/07/21
 */
@Service
public class SysExtServiceImpl implements ISysExtService {

    private static final Logger logger = LoggerFactory.getLogger(SysExtServiceImpl.class);

    @Value("${stiger.admin.init.password:123}")
    private String initPassword;

    private final PasswordEncoder passwordEncoder;

    private final SysUserMapper sysUserMapper;

    private final SysMenuMapper sysMenuMapper;

    private final SysRoleMapper sysRoleMapper;

    private final SysRoleResourceMapper sysRoleResourceMapper;

    private final SysUserRoleMapper sysUserRoleMapper;

    private final SysExtMapper sysExtMapper;

    public SysExtServiceImpl(PasswordEncoder passwordEncoder,
                             SysUserMapper sysUserMapper,
                             SysMenuMapper sysMenuMapper,
                             SysRoleMapper sysRoleMapper,
                             SysRoleResourceMapper sysRoleResourceMapper,
                             SysUserRoleMapper sysUserRoleMapper,
                             SysExtMapper sysExtMapper) {
        this.passwordEncoder = passwordEncoder;
        this.sysUserMapper = sysUserMapper;
        this.sysMenuMapper = sysMenuMapper;
        this.sysRoleMapper = sysRoleMapper;
        this.sysRoleResourceMapper = sysRoleResourceMapper;
        this.sysUserRoleMapper = sysUserRoleMapper;
        this.sysExtMapper = sysExtMapper;
    }

    @Override
    public UserInfo getUserInfo(String username) {

        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);

        SysUser sysUser = sysUserMapper.selectOne(userQueryWrapper);

        if (sysUser == null) {
            return null;
        }

        List<SysRole> sysRoles = sysExtMapper.selectRoleByUser(sysUser.getUserFlow());

        List<SysResource> sysResources = sysExtMapper.selectResourceByUser(sysUser.getUserFlow());

        return new UserInfo(sysUser, sysRoles, sysResources);
    }

    @Override
    public List<TreeNode<SysMenu>> treeMenu() {

        List<SysMenu> menus = allMenus();

        return buildTreeNode(menus);
    }

    @Override
    public List<TreeNode<SysMenu>> treeMenu(UserSecurityDetail userSecurityDetail) {

        List<SysMenu> menus = allMenus();

        AntPathMatcher pathMatcher = new AntPathMatcher();

        List<SysMenu> userMenus = menus.stream().filter(menu -> {
            for (AntPathRequestMatcher requestMatcher : userSecurityDetail.getMatchers()) {
                boolean flag = pathMatcher.match(requestMatcher.getPattern(), menu.getMenuPath());
                if (StrUtil.isBlank(menu.getMenuPath()) || flag) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        return buildTreeNode(userMenus);
    }

    @Override
    public boolean addRole(SysRole role, List<String> resourceFlows) {

        int i = sysRoleMapper.insert(role);

        int j = 0;

        for (String resourceFlow : resourceFlows) {
            SysRoleResource roleResource = new SysRoleResource();
            roleResource.setResFlow(resourceFlow);
            roleResource.setRoleFlow(role.getRoleFlow());
            j += sysRoleResourceMapper.insert(roleResource);
        }

        return i == 1 && j == resourceFlows.size();
    }

    @Override
    public boolean modRole(SysRole role, List<String> resourceFlows) {

        int i = sysRoleMapper.updateById(role);

        QueryWrapper<SysRoleResource> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq("role_flow", role.getRoleFlow());

        sysRoleResourceMapper.delete(deleteWrapper);

        int j = 0;

        for (String resourceFlow : resourceFlows) {
            SysRoleResource roleResource = new SysRoleResource();
            roleResource.setResFlow(resourceFlow);
            roleResource.setRoleFlow(role.getRoleFlow());
            j += sysRoleResourceMapper.insert(roleResource);
        }

        return i == 1 && j == resourceFlows.size();
    }

    @Override
    public List<String> getResourceFlowsByRole(String roleFlow) {
        QueryWrapper<SysRoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_flow", roleFlow);
        List<SysRoleResource> roleResources = sysRoleResourceMapper.selectList(queryWrapper);
        return roleResources.stream()
                .map(SysRoleResource::getResFlow)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getRoleFlowsByUser(String userFlow) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_flow", userFlow);
        List<SysUserRole> userRoles = sysUserRoleMapper.selectList(queryWrapper);
        return userRoles.stream()
                .map(SysUserRole::getRoleFlow)
                .collect(Collectors.toList());
    }

    @Override
    public boolean addUser(SysUser user, List<String> roleFlows) {

        user.setEncodePassword(passwordEncoder.encode(initPassword));
        user.setCreateDateTime(LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));

        int i = sysUserMapper.insert(user);

        int j = 0;

        for (String roleFlow : roleFlows) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserFlow(user.getUserFlow());
            userRole.setRoleFlow(roleFlow);
            j += sysUserRoleMapper.insert(userRole);
        }

        return i == 1 && j == roleFlows.size();
    }

    @Override
    public boolean modUser(SysUser user, List<String> roleFlows) {

        int i = sysUserMapper.updateById(user);

        QueryWrapper<SysUserRole> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq("user_flow", user.getUserFlow());

        sysUserRoleMapper.delete(deleteWrapper);

        int j = 0;

        for (String roleFlow : roleFlows) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserFlow(user.getUserFlow());
            userRole.setRoleFlow(roleFlow);
            j += sysUserRoleMapper.insert(userRole);
        }

        return i == 1 && j == roleFlows.size();
    }

    @Override
    public boolean resetUserPassword(String userFlow) {
        SysUser user = new SysUser();
        user.setEncodePassword(passwordEncoder.encode(initPassword));
        user.setUserFlow(userFlow);
        return sysUserMapper.updateById(user) == 1;
    }

    private List<SysMenu> allMenus() {
        QueryWrapper<SysMenu> menuQueryWrapper = new QueryWrapper<>();

        menuQueryWrapper.eq("enable_flag", StarTigerConstant.FLAG_Y);

        menuQueryWrapper.orderByAsc("menu_order");

        return sysMenuMapper.selectList(menuQueryWrapper);
    }

    private TreeNode<SysMenu> createMenuTreeNode(SysMenu menu) {
        return TreeNode.create(menu, (node, data) -> {
            node.setId(data.getMenuFlow());
            node.setTitle(data.getMenuName());
        });
    }

    private List<TreeNode<SysMenu>> buildTreeNode(List<SysMenu> menus) {
        List<TreeNode<SysMenu>> menuTree = new ArrayList<>();

        Map<String, TreeNode<SysMenu>> levelOneMenus = new LinkedHashMap<>();

        menus.stream().filter(menu -> {
            if (StrUtil.isBlank(menu.getParentMenuFlow())
                    && menu.getMenuLevel() == 1) {
                TreeNode<SysMenu> treeNode = createMenuTreeNode(menu);
                menuTree.add(treeNode);
                levelOneMenus.put(menu.getMenuFlow(), treeNode);
                return false;
            }
            return true;
        }).forEach(menu -> {
            if (levelOneMenus.containsKey(menu.getParentMenuFlow())) {
                TreeNode<SysMenu> parentTreeNode = levelOneMenus.get(menu.getParentMenuFlow());
                TreeNode<SysMenu> treeNode = createMenuTreeNode(menu);
                treeNode.setParentNode(parentTreeNode);
                parentTreeNode.getChildren().add(treeNode);
            }
        });

        return menuTree.stream()
                .filter(treeNode -> CollUtil.isNotEmpty(treeNode.getChildren()))
                .collect(Collectors.toList());
    }
}
