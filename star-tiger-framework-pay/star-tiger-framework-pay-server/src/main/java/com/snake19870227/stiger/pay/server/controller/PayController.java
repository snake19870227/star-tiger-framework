package com.snake19870227.stiger.pay.server.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.core.exception.BusinessException;
import com.snake19870227.stiger.core.utils.EnumUtil;
import com.snake19870227.stiger.pay.channel.PayChannelFactory;
import com.snake19870227.stiger.pay.channel.PayChannelHandler;
import com.snake19870227.stiger.pay.entity.dto.CreateTradeReq;
import com.snake19870227.stiger.pay.entity.dto.RefundInfo;
import com.snake19870227.stiger.pay.entity.dto.RefundTradeReq;
import com.snake19870227.stiger.pay.entity.dto.TradeInfo;
import com.snake19870227.stiger.pay.entity.po.PayTrade;
import com.snake19870227.stiger.pay.enums.PayChannelEnum;
import com.snake19870227.stiger.pay.enums.PaymentMethodEnum;
import com.snake19870227.stiger.pay.enums.TradeStatusEnum;
import com.snake19870227.stiger.pay.exception.PayChannelRemoteTradeNotExistException;
import com.snake19870227.stiger.pay.service.IPayService;
import com.snake19870227.stiger.pay.service.IPayTradeService;
import com.snake19870227.stiger.pay.util.PayDtoUtil;
import com.snake19870227.stiger.web.exception.MvcException;
import com.snake19870227.stiger.web.restful.RestResp;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/26
 */
@Api(tags = "订单支付服务")
@RestController
@RequestMapping(path = "/pay/v1")
public class PayController {

    private static final Logger logger = LoggerFactory.getLogger(PayController.class);

    private final IPayTradeService payTradeService;

    private final IPayService payService;

    public PayController(IPayTradeService payTradeService, IPayService payService) {
        this.payTradeService = payTradeService;
        this.payService = payService;
    }

    @ApiOperation("创建订单")
    @PostMapping(path = "/createTrade")
    public RestResp<TradeInfo<?>> createTrade(@RequestBody CreateTradeReq createTradeReq) {

        String bizType = createTradeReq.getBizType();
        String bizFlow = createTradeReq.getBizFlow();
        String payChannelId = createTradeReq.getPayChannelId();
        String paymentMethod = createTradeReq.getPaymentMethod();
        BigDecimal tradePrice = createTradeReq.getTradePrice();
        Map<String, Object> metadataMap = createTradeReq.getMetadataMap();

        try {

            PayChannelEnum payChannelEnum = EnumUtil.getById(payChannelId, PayChannelEnum.class);

            if (payChannelEnum == null) {
                throw new BusinessException("无效的支付渠道标识");
            }

            PaymentMethodEnum paymentMethodEnum = EnumUtil.getById(paymentMethod, PaymentMethodEnum.class);

            if (paymentMethodEnum == null) {
                throw new BusinessException("无效的支付方式");
            }

            PayChannelHandler payChannelHandler = PayChannelFactory.createPayChannelHandler(payChannelEnum);

            PayTrade existsTrade = loadExistsTrade(bizFlow);

            if (existsTrade != null) {

                if (StrUtil.equals(TradeStatusEnum.Success.getId(), existsTrade.getStatusId())) {

                    logger.info("订单已支付成功.[{}|{}]", existsTrade.getTradeFlow(), existsTrade.getOutTradeNo());

                    return RestResp.buildResp("10001", PayDtoUtil.createTradeInfo(existsTrade));
                }

                try {

                    payChannelHandler.query(existsTrade);

                    if (StrUtil.equals(TradeStatusEnum.Success.getId(), existsTrade.getStatusId())) {

                        logger.info("再次查询订单支付成功.[{}|{}]", existsTrade.getTradeFlow(), existsTrade.getOutTradeNo());

                        return RestResp.buildResp("10001", PayDtoUtil.createTradeInfo(existsTrade));
                    }

                    if (StrUtil.equals(TradeStatusEnum.Create.getId(), existsTrade.getStatusId())) {

                        logger.info("再次查询订单未进行支付,关闭并重新创建订单.[{}|{}]", existsTrade.getTradeFlow(), existsTrade.getOutTradeNo());

                        payChannelHandler.close(existsTrade);
                    }

                } catch (PayChannelRemoteTradeNotExistException e) {
                    logger.warn("已存在的本地订单未在渠道方创建远程订单,关闭本地订单并继续业务", e);
                    existsTrade.setStatusId(TradeStatusEnum.Closed.getId());
                    existsTrade.setStatusName(TradeStatusEnum.Closed.getName());
                    payTradeService.updateById(existsTrade);
                }
            }

            TradeInfo<?> tradeInfo = payChannelHandler.create(bizType, bizFlow, paymentMethodEnum, tradePrice, metadataMap);

            return RestResp.buildResp(StarTigerConstant.StatusCode.CODE_0000, tradeInfo);

        } catch (Exception e) {
            throw new MvcException(e);
        }
    }

    @ApiOperation("查询订单")
    @GetMapping(path = "/queryTrade")
    public RestResp<TradeInfo<?>> queryTrade(@ApiParam("商户订单号") @RequestParam(name = "outTradeNo") String outTradeNo) {

        try {

            PayTrade payTrade = payService.getTradeByOutTradeNo(outTradeNo);

            if (payTrade == null) {
                throw new BusinessException("未找到商户订单号对应的订单记录.[" + outTradeNo + "]");
            }

            if (StrUtil.equals(payTrade.getStatusId(), TradeStatusEnum.Create.getId())) {

                logger.info("订单当前未支付,通过支付渠道查询.[{}|{}]", payTrade.getTradeFlow(), payTrade.getOutTradeNo());

                PayChannelEnum payChannelEnum = EnumUtil.getById(payTrade.getPayChannelId(), PayChannelEnum.class);

                if (payChannelEnum == null) {
                    throw new BusinessException("无效的支付渠道标识");
                }

                PayChannelHandler payChannelHandler = PayChannelFactory.createPayChannelHandler(payChannelEnum);

                try {
                    payChannelHandler.query(payTrade);
                } catch (PayChannelRemoteTradeNotExistException e) {
                    logger.warn("已存在的本地订单未在渠道方创建远程订单", e);
                    String tradeExpireTime = payTrade.getTradeExpireTime();
                    LocalDateTime expireDateTime = LocalDateTimeUtil.parse(tradeExpireTime, "yyyyMMddHHmmss");
                    if (LocalDateTime.now().isAfter(expireDateTime)) {
                        payTrade.setStatusId(TradeStatusEnum.Closed.getId());
                        payTrade.setStatusName(TradeStatusEnum.Closed.getName());
                        payTradeService.updateById(payTrade);
                    }
                }
            }

            return RestResp.buildResp(StarTigerConstant.StatusCode.CODE_0000, PayDtoUtil.createTradeInfo(payTrade));
        } catch (Exception e) {
            throw new MvcException(e);
        }
    }

    @ApiOperation("退款")
    @PostMapping(path = "/refund")
    public RestResp<RefundInfo> refund(@RequestBody RefundTradeReq refundTradeReq) {

        try {

            PayTrade payTrade = payService.getTradeByOutTradeNo(refundTradeReq.getOutTradeNo());

            if (payTrade == null) {
                throw new BusinessException("未找到支付订单");
            }

            PayChannelEnum payChannelEnum = EnumUtil.getById(payTrade.getPayChannelId(), PayChannelEnum.class);

            if (payChannelEnum == null) {
                throw new BusinessException("无效的支付渠道标识");
            }

            PayChannelHandler payChannelHandler = PayChannelFactory.createPayChannelHandler(payChannelEnum);

            RefundInfo refundInfo = payChannelHandler.refund(payTrade, refundTradeReq.getRefundPrice());

            return RestResp.buildResp(StarTigerConstant.StatusCode.CODE_0000, refundInfo);

        } catch (Exception e) {
            throw new MvcException(e);
        }
    }

    private PayTrade loadExistsTrade(String bizFlow) {
        QueryWrapper<PayTrade> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("biz_flow", bizFlow)
                .ne("status_id", TradeStatusEnum.Closed.getId())
        ;
        List<PayTrade> payTrades = payTradeService.list(queryWrapper);
        return payTrades.isEmpty() ? null : payTrades.get(0);
    }
}
