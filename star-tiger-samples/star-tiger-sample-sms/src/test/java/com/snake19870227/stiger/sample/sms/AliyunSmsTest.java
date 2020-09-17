package com.snake19870227.stiger.sample.sms;

import cn.hutool.core.map.MapUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.snake19870227.stiger.sms.aliyun.AliyunSms;

/**
 * @author BuHuaYang
 * 2020/9/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class AliyunSmsTest {

    @Autowired
    private AliyunSms aliyunSms;

    @Test
    public void test() {
        aliyunSms.send("xxx", "xxxx", "xxxx", MapUtil.of("code", "1124"));
    }
}
