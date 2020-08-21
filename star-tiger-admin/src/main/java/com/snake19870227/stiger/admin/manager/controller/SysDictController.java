package com.snake19870227.stiger.admin.manager.controller;

import cn.hutool.core.util.StrUtil;

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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snake19870227.stiger.admin.entity.po.SysDict;
import com.snake19870227.stiger.admin.entity.po.SysDictItem;
import com.snake19870227.stiger.admin.sys.service.ISysDictItemService;
import com.snake19870227.stiger.admin.sys.service.ISysDictService;
import com.snake19870227.stiger.web.restful.RestResp;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/21
 */
@Controller
@RequestMapping(path = "/sys/dict")
public class SysDictController {

    private static final Logger logger = LoggerFactory.getLogger(SysDictController.class);

    private final ISysDictService sysDictService;

    private final ISysDictItemService sysDictItemService;

    public SysDictController(ISysDictService sysDictService,
                             ISysDictItemService sysDictItemService) {
        this.sysDictService = sysDictService;
        this.sysDictItemService = sysDictItemService;
    }

    @GetMapping(path = "/main")
    public String main() {
        return "sys/dict/main";
    }

    @GetMapping(path = "/data")
    @ResponseBody
    public RestResp<Page<SysDict>> data(@RequestParam(name = "dictCode", required = false) String dictCode,
                                        @RequestParam(name = "dictName", required = false) String dictName,
                                        @RequestParam(name = "page", defaultValue = "1") Long page,
                                        @RequestParam(name = "limit", defaultValue = "10") Long limit) {

        Page<SysDict> pageInfo = new Page<>(page, limit);

        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();

        if (StrUtil.isNotBlank(dictCode)) {
            queryWrapper.eq("dict_code", dictCode);
        }

        if (StrUtil.isNotBlank(dictName)) {
            queryWrapper.like("dict_name", dictName);
        }

        queryWrapper.orderByAsc("dict_name");

        pageInfo = sysDictService.page(pageInfo, queryWrapper);

        return RestResp.buildResp("10000", pageInfo);
    }

    @GetMapping(path = "/{dictFlow}")
    @ResponseBody
    public RestResp<SysDict> read(@PathVariable(name = "dictFlow") String dictFlow) {

        SysDict dict = sysDictService.getById(dictFlow);

        return RestResp.buildResp("10000", dict);
    }

    @GetMapping(path = "/items/{dictFlow}")
    @ResponseBody
    public RestResp<List<SysDictItem>> items(@PathVariable(name = "dictFlow") String dictFlow) {

        QueryWrapper<SysDictItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dict_flow", dictFlow);
        queryWrapper.orderByAsc("dict_item_name");

        List<SysDictItem> dictItems = sysDictItemService.list(queryWrapper);

        return RestResp.buildResp("10000", dictItems);
    }

    @GetMapping(path = "/item/{dictItemFlow}")
    @ResponseBody
    public RestResp<SysDictItem> readItem(@PathVariable(name = "dictItemFlow") String dictItemFlow) {

        SysDictItem dictItem = sysDictItemService.getById(dictItemFlow);

        return RestResp.buildResp("10000", dictItem);
    }

    @PostMapping
    @ResponseBody
    public RestResp<?> add(@RequestBody SysDict dict) {

        sysDictService.save(dict);

        return RestResp.buildResp("10000");
    }

    @PutMapping
    @ResponseBody
    public RestResp<?> mod(@RequestBody SysDict dict) {

        sysDictService.updateById(dict);

        return RestResp.buildResp("10000");
    }

    @PostMapping(path = "/item")
    @ResponseBody
    public RestResp<?> addItem(@RequestBody SysDictItem dictItem) {

        sysDictItemService.save(dictItem);

        return RestResp.buildResp("10000");
    }

    @PutMapping(path = "/item")
    @ResponseBody
    public RestResp<?> modItem(@RequestBody SysDictItem dictItem) {

        sysDictItemService.updateById(dictItem);

        return RestResp.buildResp("10000");
    }
}
