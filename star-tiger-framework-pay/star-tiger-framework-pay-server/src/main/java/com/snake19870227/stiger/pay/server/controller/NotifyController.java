package com.snake19870227.stiger.pay.server.controller;

import cn.hutool.extra.servlet.ServletUtil;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.snake19870227.stiger.core.exception.BusinessException;
import com.snake19870227.stiger.pay.channel.PayChannelFactory;
import com.snake19870227.stiger.pay.channel.PayChannelHandler;
import com.snake19870227.stiger.pay.enums.PayChannelEnum;
import com.snake19870227.stiger.pay.properties.AlipayProperties;
import com.snake19870227.stiger.pay.properties.StarTigerPayProperties;
import com.snake19870227.stiger.pay.properties.WxpayProperties;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/29
 */
@Controller
public class NotifyController {

    private static final Logger logger = LoggerFactory.getLogger(NotifyController.class);

    private final StarTigerPayProperties starTigerPayProperties;

    public NotifyController(StarTigerPayProperties starTigerPayProperties) {
        this.starTigerPayProperties = starTigerPayProperties;
    }

    @PostMapping(path = "/alipayNotify/{payWay}")
    @ResponseBody
    public String alipayNotify(@PathVariable(name = "payWay") String payWay,
                               HttpServletRequest request) {

        try {

            AlipayProperties alipayProperties = starTigerPayProperties.getAlipayMerchants().get(payWay);

            PayChannelHandler<Map<String, String>> payChannelHandler = PayChannelFactory.createPayChannelHandler(PayChannelEnum.Alipay);

            payChannelHandler.doNotify(payWay, ServletUtil.getParamMap(request));

            return "success";
        } catch (Exception e) {
            logger.error("处理支付宝通知失败.", e);
            return "fail";
        }
    }

    @PostMapping(path = "/wxpayNotify/{payWay}")
    @ResponseBody
    public String wxpayNotify(@PathVariable(name = "payWay") String payWay,
                              @RequestBody String notifyData,
                              HttpServletRequest request) {

        logger.debug("收到微信退款通知:{}", notifyData);

        try {

            WxpayProperties wxpayProperties = starTigerPayProperties.getWxpayMerchants().get(payWay);

            if (wxpayProperties == null) {
                throw new BusinessException("收到未知商户的退款通知.[" + payWay + "]");
            }

            PayChannelHandler<String> payChannelHandler = PayChannelFactory.createPayChannelHandler(PayChannelEnum.Wxpay);

            payChannelHandler.doNotify(payWay, notifyData);

            return WxPayNotifyResponse.success("成功");
        } catch (Exception e) {
            logger.error("处理支付宝通知失败.", e);
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }

    @PostMapping(path = "/wxpayNotify/refund/{payWay}")
    @ResponseBody
    public String wxpayRefundNotify(@PathVariable(name = "payWay") String payWay,
                                    @RequestBody String notifyData,
                                    HttpServletRequest request) {

        logger.debug("收到微信支付通知:{}", notifyData);

        try {

            WxpayProperties wxpayProperties = starTigerPayProperties.getWxpayMerchants().get(payWay);

            if (wxpayProperties == null) {
                throw new BusinessException("收到未知商户的支付通知.[" + payWay + "]");
            }

            PayChannelHandler<String> payChannelHandler = PayChannelFactory.createPayChannelHandler(PayChannelEnum.Wxpay);

            payChannelHandler.doRefundNotify(payWay, notifyData);

            return WxPayNotifyResponse.success("成功");
        } catch (Exception e) {
            logger.error("处理支付宝通知失败.", e);
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }
}
