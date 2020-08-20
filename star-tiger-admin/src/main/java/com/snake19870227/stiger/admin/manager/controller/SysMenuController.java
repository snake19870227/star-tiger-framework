package com.snake19870227.stiger.admin.manager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.admin.entity.po.SysMenu;
import com.snake19870227.stiger.admin.sys.service.ISysMenuService;
import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.web.restful.RestResp;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/06
 */
@Controller
@RequestMapping(path = "/sys/menu")
public class SysMenuController {

    private static final Logger logger = LoggerFactory.getLogger(SysMenuController.class);

    private final ISysMenuService sysMenuService;

    public SysMenuController(ISysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    @GetMapping(path = "/main")
    public String main() {
        return "sys/menu/main";
    }

    @GetMapping(path = "/data")
    @ResponseBody
    public RestResp<List<SysMenu>> data() {

        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("enable_flag", StarTigerConstant.FLAG_Y);
        queryWrapper.orderByAsc("menu_order");

        List<SysMenu> menus = sysMenuService.list(queryWrapper);

        return RestResp.buildResp("10000", menus);
    }

    @GetMapping(path = "/{menuFlow}")
    @ResponseBody
    public RestResp<SysMenu> read(@PathVariable(name = "menuFlow") String menuFlow) {

        SysMenu menu = sysMenuService.getById(menuFlow);

        return RestResp.buildResp("10000", menu);
    }

    @PostMapping
    @ResponseBody
    public RestResp<?> add(@RequestBody SysMenu menu) {

        sysMenuService.save(menu);

        return RestResp.buildResp("10000");
    }

    @PutMapping
    @ResponseBody
    public RestResp<?> mod(@RequestBody SysMenu menu) {

        sysMenuService.updateById(menu);

        return RestResp.buildResp("10000");
    }

    @PutMapping(path = "/enableStatus/{menuFlow}/{flag}")
    @ResponseBody
    public RestResp<?> enable(@PathVariable(name = "menuFlow") String menuFlow,
                              @PathVariable(name = "flag") String flag) {

        SysMenu menu = sysMenuService.getById(menuFlow);

        menu.setEnableFlag(flag);

        sysMenuService.updateById(menu);

        return RestResp.buildResp("10000");
    }
}
