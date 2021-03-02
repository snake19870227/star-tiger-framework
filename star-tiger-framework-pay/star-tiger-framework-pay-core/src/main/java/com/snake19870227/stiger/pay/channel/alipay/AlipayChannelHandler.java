package com.snake19870227.stiger.pay.channel.alipay;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import com.alipay.easysdk.payment.app.models.AlipayTradeAppPayResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeCloseResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.core.exception.BusinessException;
import com.snake19870227.stiger.pay.PayConstant;
import com.snake19870227.stiger.pay.channel.BasePayChannelHandler;
import com.snake19870227.stiger.pay.channel.ChannelClientParam;
import com.snake19870227.stiger.pay.channel.IPayStorage;
import com.snake19870227.stiger.pay.config.AlipayConfig;
import com.snake19870227.stiger.pay.entity.dto.RefundInfo;
import com.snake19870227.stiger.pay.entity.dto.TradeInfo;
import com.snake19870227.stiger.pay.entity.po.PayNotify;
import com.snake19870227.stiger.pay.entity.po.PayRefund;
import com.snake19870227.stiger.pay.entity.po.PayTrade;
import com.snake19870227.stiger.pay.enums.PayChannelEnum;
import com.snake19870227.stiger.pay.enums.PaymentMethodEnum;
import com.snake19870227.stiger.pay.enums.RefundStatusEnum;
import com.snake19870227.stiger.pay.enums.TradeStatusEnum;
import com.snake19870227.stiger.pay.event.PaySuccessEvent;
import com.snake19870227.stiger.pay.exception.PayChannelRemoteException;
import com.snake19870227.stiger.pay.exception.PayChannelRemoteTradeNotExistException;
import com.snake19870227.stiger.pay.exception.PaySdkBeanNotFoundException;
import com.snake19870227.stiger.pay.properties.AlipayProperties;
import com.snake19870227.stiger.pay.properties.StarTigerPayProperties;
import com.snake19870227.stiger.pay.util.PayDtoUtil;

import static com.snake19870227.stiger.pay.PayConstant.Alipay.SUB_CODE_ACQ_TRADE_NOT_EXIST;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/27
 */
public class AlipayChannelHandler extends BasePayChannelHandler<Map<String, String>> {

    private static final Logger logger = LoggerFactory.getLogger(AlipayChannelHandler.class);

    protected AlipayChannelHandler(StarTigerPayProperties starTigerPayProperties,
                                   IPayStorage payStorage) {
        super(starTigerPayProperties, payStorage);
    }

    @Override
    public TradeInfo<?> create(String bizType, String bizFlow, PaymentMethodEnum paymentMethodEnum, BigDecimal tradePrice, Map<String, Object> metadataMap) {

        String payWay = getPayWayByBizType(bizType, PayChannelEnum.Alipay);

        String appId = getAppIdByPayWay(payWay, PayChannelEnum.Alipay);

        AlipayProperties alipayProperties = getProperties(payWay, PayChannelEnum.Alipay);

        String outTradeNo;
        // 加锁获取唯一订单号
        synchronized (TRADE_NO_LOCK) {
            outTradeNo = payStorage.createOutTradeNo();
        }
        try {
            LocalDateTime now = LocalDateTime.now();
            String createDateTime = LocalDateTimeUtil.format(now, "yyyyMMddHHmmss");
            String expireDateTime = LocalDateTimeUtil.format(now.plusMinutes(15), "yyyyMMddHHmmss");
            String expireDateTimeAlipay = LocalDateTimeUtil.format(now.plusMinutes(15), "yyyy-MM-dd HH:mm:ss");

            // 组织passback_params参数
            JSONObject passbackParams = JSONUtil.createObj();
            passbackParams.set("bizType", bizType).set("bizFlow", bizFlow);

            Map<String, Object> paramMap =
                    MapUtil.builder(new HashMap<String, Object>())
                            .put(PayConstant.Alipay.PARAM_NAME_PASSBACK_PARAMS, URLUtil.encode(passbackParams.toString(), StandardCharsets.UTF_8))
                            .put(PayConstant.Alipay.PARAM_NAME_TIME_EXPIRE, expireDateTimeAlipay)
                            .put(PayConstant.Alipay.PARAM_NAME_NOTIFY_URL, alipayProperties.getNotifyUrl())
                            .build();

            // 组织APP请求参数
            ChannelClientParam clientParam = handleResponse(appId, getBizName(bizType), outTradeNo, tradePrice, paymentMethodEnum, paramMap);

            PayTrade payTrade = new PayTrade();
            payTrade.setBizType(bizType);
            payTrade.setBizFlow(bizFlow);
            payTrade.setStatusId(TradeStatusEnum.Create.getId());
            payTrade.setStatusName(TradeStatusEnum.Create.getName());
            payTrade.setPayChannelId(PayChannelEnum.Alipay.getId());
            payTrade.setPayChannelName(PayChannelEnum.Alipay.getName());
            payTrade.setPayAppId(appId);
            payTrade.setOutTradeNo(outTradeNo);
            payTrade.setPaymentMethod(paymentMethodEnum.getId());
            payTrade.setTradePrice(tradePrice);
            payTrade.setTradeCreateTime(createDateTime);
            payTrade.setTradeExpireTime(expireDateTime);
            payTrade.setReqContent(clientParam.toJson());
            payStorage.saveTrade(payTrade);

            return PayDtoUtil.createTradeInfo(payTrade, clientParam);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("支付宝订单创建失败.[" + bizType + "|" + paymentMethodEnum.getId() + "|" + tradePrice + "]", e);
        }
    }

    @Override
    public void query(PayTrade payTrade) {
        String appId = getAppIdByBizType(payTrade.getBizType(), PayChannelEnum.Alipay);
        com.alipay.easysdk.payment.common.Client client;
        try {
            client = StarTigerContext.getBean(
                    PayConstant.Alipay.COMMON_PREFIX + appId,
                    com.alipay.easysdk.payment.common.Client.class
            );
        } catch (BeansException e) {
            throw new PaySdkBeanNotFoundException(PayConstant.Alipay.COMMON_PREFIX + appId, e);
        }
        try {
            AlipayTradeQueryResponse tradeQueryResponse = client.query(payTrade.getOutTradeNo());
            if (StrUtil.equals(PayConstant.Alipay.CODE_10000, tradeQueryResponse.code)
                    && !StrUtil.equals(tradeQueryResponse.tradeStatus, payTrade.getTradeStatus())) {
                payTrade.setTradeStatus(tradeQueryResponse.tradeStatus);
                payTrade.setTradeNo(tradeQueryResponse.tradeNo);
                if (StrUtil.equals(PayConstant.Alipay.TRADE_SUCCESS, tradeQueryResponse.tradeStatus)) {
                    payTrade.setStatusId(TradeStatusEnum.Success.getId());
                    payTrade.setStatusName(TradeStatusEnum.Success.getName());
                    payTrade.setTradeBuyerAccount(tradeQueryResponse.buyerLogonId);
                    payTrade.setTradeBuyerId(tradeQueryResponse.buyerUserId);
                    payTrade.setTradeSuccessTime(LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));

                    payStorage.updateTrade(payTrade);

                    StarTigerContext.publishEvent(new PaySuccessEvent(new PaySuccessEvent.PaySuccessEventSource(payTrade)));
                }
                if (StrUtil.equals(PayConstant.Alipay.TRADE_CLOSED, tradeQueryResponse.tradeStatus)) {
                    payTrade.setStatusId(TradeStatusEnum.Closed.getId());
                    payTrade.setStatusName(TradeStatusEnum.Closed.getName());
                    payStorage.updateTrade(payTrade);
                }
            } else {
                if (StrUtil.equals(tradeQueryResponse.subCode, SUB_CODE_ACQ_TRADE_NOT_EXIST)) {
                    throw new PayChannelRemoteTradeNotExistException(tradeQueryResponse.subCode, tradeQueryResponse.subMsg);
                } else {
                    throw new PayChannelRemoteException(tradeQueryResponse.subCode, tradeQueryResponse.subMsg);
                }
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("支付宝订单查询失败.[" + payTrade.getOutTradeNo() + "]", e);
        }
    }

    @Override
    public boolean close(PayTrade payTrade) {
        String appId = getAppIdByBizType(payTrade.getBizType(), PayChannelEnum.Alipay);
        com.alipay.easysdk.payment.common.Client client;
        try {
            client = StarTigerContext.getBean(
                    PayConstant.Alipay.COMMON_PREFIX + appId,
                    com.alipay.easysdk.payment.common.Client.class
            );
        } catch (BeansException e) {
            throw new PaySdkBeanNotFoundException(PayConstant.Alipay.COMMON_PREFIX + appId, e);
        }
        try {
            AlipayTradeCloseResponse closeResponse = client.close(payTrade.getOutTradeNo());
            if (StrUtil.equals(PayConstant.Alipay.CODE_10000, closeResponse.code)) {
                payTrade.setTradeStatus(PayConstant.Alipay.TRADE_CLOSED);
                payTrade.setStatusId(TradeStatusEnum.Closed.getId());
                payTrade.setStatusName(TradeStatusEnum.Closed.getName());
                payStorage.updateTrade(payTrade);
                return true;
            } else {
                throw new BusinessException("支付宝订单关闭失败.[" + closeResponse.subCode + "][" + closeResponse.subMsg + "][" + payTrade.getOutTradeNo() + "]");
            }
        } catch (Exception e) {
            throw new BusinessException("支付宝订单关闭失败.[" + payTrade.getOutTradeNo() + "]", e);
        }
    }

    @Override
    public void doNotify(String payWay, Map<String, String> notifyData) {
        String notifyId = notifyData.get(PayConstant.Alipay.PARAM_NAME_NOTIFY_ID);
        String appId = notifyData.get(PayConstant.Alipay.PARAM_NAME_APP_ID);
        String tradeStatus = notifyData.get(PayConstant.Alipay.PARAM_NAME_TRADE_STATUS);
        String outTradeNo = notifyData.get(PayConstant.Alipay.PARAM_NAME_OUT_TRADE_NO);
        String passbackParams = notifyData.get(PayConstant.Alipay.PARAM_NAME_PASSBACK_PARAMS);
        String tradeNo = notifyData.get(PayConstant.Alipay.PARAM_NAME_TRADE_NO);
        String notifyTime = notifyData.get(PayConstant.Alipay.PARAM_NAME_NOTIFY_TIME);
        String buyerId = notifyData.get(PayConstant.Alipay.PARAM_NAME_BUYER_ID);
        String buyerLogonId = notifyData.get(PayConstant.Alipay.PARAM_NAME_BUYER_LOGON_ID);

        PayNotify payNotify = new PayNotify();

        if (StrUtil.isBlank(passbackParams)) {
            logger.warn("支付宝通知请求不包含passbackParams信息.");
        } else {
            String passbackStr = URLUtil.decode(passbackParams, StandardCharsets.UTF_8);
            JSONObject jsonObject = JSONUtil.parseObj(passbackStr);
            String bizType = jsonObject.getStr("bizType");
            String bizFlow = jsonObject.getStr("bizFlow");
            payNotify.setPayWay(getPayWayByBizType(bizType, PayChannelEnum.Alipay));
        }
        payNotify.setNotifyTime(LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));
        payNotify.setPayChannelId(PayChannelEnum.Alipay.getId());
        payNotify.setPayChannelName(PayChannelEnum.Alipay.getName());
        payNotify.setNotifyId(notifyId);
        payNotify.setOutTradeNo(outTradeNo);
        payNotify.setTradeNo(tradeNo);
        payNotify.setNotifyContent(JSONUtil.toJsonPrettyStr(notifyData));
        payStorage.saveNotify(payNotify);

        if (StrUtil.isBlank(appId)) {
            throw new BusinessException("支付宝通知请求不包含AppId信息.");
        }

        if (StrUtil.isBlank(tradeStatus)) {
            throw new BusinessException("支付宝通知请求不包含tradeStatus信息.");
        }

        if (StrUtil.isBlank(outTradeNo)) {
            throw new BusinessException("支付宝通知请求不包含outTradeNo信息.");
        }

        if (StrUtil.isBlank(tradeNo)) {
            throw new BusinessException("支付宝通知请求不包含tradeNo信息.");
        }

        if (StrUtil.isBlank(notifyTime)) {
            throw new BusinessException("支付宝通知请求不包含notifyTime信息.");
        }

        if (StrUtil.isBlank(buyerId)) {
            logger.warn("支付宝通知请求不包含buyerId信息.");
        }

        if (StrUtil.isBlank(buyerLogonId)) {
            logger.warn("支付宝通知请求不包含buyerLogonId信息.");
        }

        com.alipay.easysdk.payment.common.Client client;
        try {
            client = StarTigerContext.getBean(
                    AlipayConfig.COMMON_PREFIX + appId,
                    com.alipay.easysdk.payment.common.Client.class
            );
        } catch (BeansException e) {
            throw new PaySdkBeanNotFoundException(AlipayConfig.COMMON_PREFIX + appId, e);
        }

        try {

            boolean flag = client.verifyNotify(notifyData);

            if (!flag) {
                throw new BusinessException("支付宝通知请求验证失败.[" + HttpUtil.toParams(notifyData) + "]");
            }

            PayTrade payTrade = payStorage.getTradeByOutTradeNo(outTradeNo);

            payTrade.setTradeStatus(tradeStatus);
            payTrade.setTradeNo(tradeNo);

            if (StrUtil.equals(PayConstant.Alipay.TRADE_SUCCESS, tradeStatus)) {
                payTrade.setStatusId(TradeStatusEnum.Success.getId());
                payTrade.setStatusName(TradeStatusEnum.Success.getName());
                payTrade.setTradeBuyerAccount(buyerLogonId);
                payTrade.setTradeBuyerId(buyerId);
                payTrade.setTradeSuccessTime(LocalDateTimeUtil.format(LocalDateTimeUtil.parse(notifyTime, "yyyy-MM-dd HH:mm:ss"), "yyyyMMddHHmmss"));

                payStorage.updateTrade(payTrade);

                StarTigerContext.publishEvent(new PaySuccessEvent(new PaySuccessEvent.PaySuccessEventSource(payTrade)));
            }

            if (StrUtil.equals(PayConstant.Alipay.TRADE_CLOSED, tradeStatus)) {
                payTrade.setStatusId(TradeStatusEnum.Closed.getId());
                payTrade.setStatusName(TradeStatusEnum.Closed.getName());

                payStorage.updateTrade(payTrade);
            }

        } catch (Exception e) {
            throw new BusinessException("支付宝通知请求验证失败.", e);
        }
    }

    @Override
    public RefundInfo refund(PayTrade payTrade, BigDecimal refundPrice) {
        String appId = payTrade.getPayAppId();
        com.alipay.easysdk.payment.common.Client client;
        try {
            client = StarTigerContext.getBean(
                    PayConstant.Alipay.COMMON_PREFIX + appId,
                    com.alipay.easysdk.payment.common.Client.class
            );
        } catch (BeansException e) {
            throw new PaySdkBeanNotFoundException(PayConstant.Alipay.COMMON_PREFIX + appId, e);
        }
//        String outRefundNo = null;
        // 加锁获取唯一订单号
//        synchronized (TRADE_NO_LOCK) {
//            outRefundNo = payService.createOutRefundNo();
//        }
        try {


            AlipayTradeRefundResponse refundResponse = client.refund(payTrade.getOutTradeNo(), refundPrice.toString());
            if (StrUtil.equals(PayConstant.Alipay.CODE_10000, refundResponse.code)) {

                PayRefund payRefund = new PayRefund();
                payRefund.setTradeFlow(payTrade.getTradeFlow());
                payRefund.setStatusId(RefundStatusEnum.Success.getId());
                payRefund.setStatusName(RefundStatusEnum.Success.getName());
                payRefund.setPayChannelId(payTrade.getPayChannelId());
                payRefund.setPayChannelName(payTrade.getPayChannelName());
                payRefund.setPayAppId(payTrade.getPayAppId());
                payRefund.setOutTradeNo(payTrade.getOutTradeNo());
                payRefund.setPaymentMethod(payTrade.getPaymentMethod());
//                payRefund.setOutRefundNo(outRefundNo);
                payRefund.setTradePrice(payTrade.getTradePrice());
                payRefund.setRefundPrice(refundPrice);
                payRefund.setRefundCreateTime(LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));

//                payRefund.setRefundNo(refundResult.getRefundId());
                payStorage.saveRefund(payRefund);

                payTrade.setIsRefund(StarTigerConstant.FLAG_Y);
                payStorage.updateTrade(payTrade);

                return PayDtoUtil.createRefundInfo(payRefund);
            } else {
                throw new BusinessException("支付宝订单退款失败.[" + refundResponse.subCode + "][" + refundResponse.subMsg + "][" + payTrade.getOutTradeNo() + "]");
            }

        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("支付宝订单退款失败.[" + payTrade.getTradeFlow() + "|" + payTrade.getOutTradeNo() + "|" + refundPrice + "]", e);
        }
    }

    @Override
    public RefundInfo queryRefund(PayTrade payTrade) {
        return null;
    }

    @Override
    public void doRefundNotify(String payWay, String notifyData) {

    }

    protected ChannelClientParam handleResponse(String appId,
                                                String subject,
                                                String outTradeNo,
                                                BigDecimal tradePrice,
                                                PaymentMethodEnum paymentMethodEnum,
                                                Map<String, Object> paramMap) throws Exception {
        switch (paymentMethodEnum) {
            case AlipayTradeAppPay: {

                com.alipay.easysdk.payment.app.Client client;
                try {
                    client = StarTigerContext.getBean(
                            PayConstant.Alipay.APP_PREFIX + appId,
                            com.alipay.easysdk.payment.app.Client.class
                    );
                } catch (BeansException e) {
                    throw new PaySdkBeanNotFoundException(PayConstant.Alipay.APP_PREFIX + appId, e);
                }

                for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                    client.optional(entry.getKey(), entry.getValue());
                }

                AlipayTradeAppPayResponse payResponse =
                        client.pay(subject, outTradeNo, tradePrice.toString());

                AlipayAppClientParam clientParam = new AlipayAppClientParam();
                clientParam.setParamStr(payResponse.body);
                return clientParam;
            }
            case AlipayTradePagePay: {
                com.alipay.easysdk.payment.page.Client client;
                try {
                    client = StarTigerContext.getBean(
                            PayConstant.Alipay.PAGE_PREFIX + appId,
                            com.alipay.easysdk.payment.page.Client.class
                    );
                } catch (BeansException e) {
                    throw new PaySdkBeanNotFoundException(PayConstant.Alipay.PAGE_PREFIX + appId, e);
                }

                for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                    client.optional(entry.getKey(), entry.getValue());
                }

                AlipayTradePagePayResponse payResponse =
                        client.pay(subject, outTradeNo, tradePrice.toString(), "");

                AlipayPageClientParam clientParam = new AlipayPageClientParam();
                clientParam.setParamStr(payResponse.body);
                return clientParam;
            }
            default:
                throw new BusinessException("未知的支付方式");
        }
    }
}
