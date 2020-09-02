package com.snake19870227.stiger.admin.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.snake19870227.stiger.oss.StarTigerOssStorage;

/**
 * @author BuHuaYang
 * 2020/9/2
 */
@RestController
public class DemoController {

    private final StarTigerOssStorage ossStorage;

    public DemoController(StarTigerOssStorage ossStorage) {
        this.ossStorage = ossStorage;
    }
}
