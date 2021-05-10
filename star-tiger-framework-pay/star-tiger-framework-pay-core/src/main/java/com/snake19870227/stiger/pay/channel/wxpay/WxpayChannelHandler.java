package com.snake19870227.stiger.pay.channel.wxpay;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.BeansException;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderCloseResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.core.context.StarTigerContext;
import com.snake19870227.stiger.core.exception.BusinessException;
import com.snake19870227.stiger.pay.PayConstant;
import com.snake19870227.stiger.pay.channel.BasePayChannelHandler;
import com.snake19870227.stiger.pay.channel.ChannelClientParam;
import com.snake19870227.stiger.pay.entity.dto.RefundInfo;
import com.snake19870227.stiger.pay.entity.dto.TradeInfo;
import com.snake19870227.stiger.pay.entity.po.PayNotify;
import com.snake19870227.stiger.pay.entity.po.PayRefund;
import com.snake19870227.stiger.pay.entity.po.PayTrade;
import com.snake19870227.stiger.pay.enums.NotifyTypeEnum;
import com.snake19870227.stiger.pay.enums.PayChannelEnum;
import com.snake19870227.stiger.pay.enums.PaymentMethodEnum;
import com.snake19870227.stiger.pay.enums.RefundStatusEnum;
import com.snake19870227.stiger.pay.enums.TradeStatusEnum;
import com.snake19870227.stiger.pay.event.PaySuccessEvent;
import com.snake19870227.stiger.pay.exception.PayChannelRemoteException;
import com.snake19870227.stiger.pay.exception.PayChannelRemoteTradeNotExistException;
import com.snake19870227.stiger.pay.exception.PaySdkBeanNotFoundException;
import com.snake19870227.stiger.pay.properties.StarTigerPayProperties;
import com.snake19870227.stiger.pay.properties.WxpayProperties;
import com.snake19870227.stiger.pay.service.IPayNotifyService;
import com.snake19870227.stiger.pay.service.IPayRefundService;
import com.snake19870227.stiger.pay.channel.IPayStorage;
import com.snake19870227.stiger.pay.service.IPayTradeService;
import com.snake19870227.stiger.pay.util.PayDtoUtil;

import static com.snake19870227.stiger.pay.PayConstant.Wxpay.SUB_CODE_ORDERNOTEXIST;

/**
 * @author BuHuaYang
 * 2020/9/23
 */
public class WxpayChannelHandler extends BasePayChannelHandler<String> {

    protected WxpayChannelHandler(StarTigerPayProperties starTigerPayProperties,
                                  IPayStorage payStorage) {
        super(starTigerPayProperties, payStorage);
    }

    @Override
    public TradeInfo<?> create(String bizType, String bizFlow, PaymentMethodEnum paymentMethodEnum, BigDecimal tradePrice, Map<String, Object> metadataMap) {
        PayChannelEnum wxpayChannelEnum = PayChannelEnum.Wxpay;

        String openid = (String) metadataMap.get("openid");

        String payWay = getPayWayByBizType(bizType, wxpayChannelEnum);
        WxpayProperties wxpayProperties = getProperties(payWay, wxpayChannelEnum);
        String appId = wxpayProperties.getAppId();
        WxPayServiceImpl wxPayService = null;
        try {
            wxPayService = StarTigerContext.getBean(PayConstant.Wxpay.BEAN_PREFIX + appId, WxPayServiceImpl.class);
        } catch (BeansException e) {
            throw new PaySdkBeanNotFoundException(PayConstant.Wxpay.BEAN_PREFIX + appId, e);
        }
        String outTradeNo = null;
        // 加锁获取唯一订单号
        synchronized (TRADE_NO_LOCK) {
            outTradeNo = payStorage.createOutTradeNo();
        }
        try {
            LocalDateTime now = LocalDateTime.now();
            String createDateTime = LocalDateTimeUtil.format(now, "yyyyMMddHHmmss");
            String expireDateTime = LocalDateTimeUtil.format(now.plusMinutes(15), "yyyyMMddHHmmss");

            WxPayUnifiedOrderRequest.WxPayUnifiedOrderRequestBuilder requestBuilder = WxPayUnifiedOrderRequest.newBuilder().body(bizType)
                    .outTradeNo(outTradeNo)
                    .openid(openid)
                    .totalFee(BaseWxPayRequest.yuanToFen(tradePrice.toString()))
                    .tradeType(paymentMethodEnum.getId())
                    .timeStart(createDateTime).timeExpire(expireDateTime)
                    .spbillCreateIp(wxpayProperties.getSpbillCreateIp());
            if (StrUtil.isNotBlank(openid)) {
                requestBuilder.openid(openid);
            }
            WxPayUnifiedOrderRequest request = requestBuilder.build();
            ChannelClientParam clientParam = handleResponse(wxPayService, request);

            PayTrade payTrade = new PayTrade();
            payTrade.setBizType(bizType);
            payTrade.setBizFlow(bizFlow);
            payTrade.setStatusId(TradeStatusEnum.Create.getId());
            payTrade.setStatusName(TradeStatusEnum.Create.getName());
            payTrade.setPayChannelId(wxpayChannelEnum.getId());
            payTrade.setPayChannelName(wxpayChannelEnum.getName());
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
            throw new BusinessException("微信订单创建失败.[" + bizType + "|" + paymentMethodEnum.getId() + "|" + tradePrice + "]", e);
        }
    }

    @Override
    public void query(PayTrade payTrade) {
        String appId = getAppIdByBizType(payTrade.getBizType(), PayChannelEnum.Wxpay);
        WxPayServiceImpl wxPayService;
        try {
            wxPayService = StarTigerContext.getBean(PayConstant.Wxpay.BEAN_PREFIX + appId, WxPayServiceImpl.class);
        } catch (BeansException e) {
            throw new PaySdkBeanNotFoundException(PayConstant.Wxpay.BEAN_PREFIX + appId, e);
        }
        try {
            WxPayOrderQueryResult result = wxPayService.queryOrder(null, payTrade.getOutTradeNo());
            if (StrUtil.equals(PayConstant.Wxpay.STATUS_SUCCESS, result.getReturnCode())
                    && StrUtil.equals(PayConstant.Wxpay.STATUS_SUCCESS, result.getResultCode())) {
                payTrade.setTradeStatus(result.getTradeState());
                if (StrUtil.equals(PayConstant.Wxpay.TRADE_STATUS_SUCCESS, result.getTradeState())) {
                    payTrade.setStatusId(TradeStatusEnum.Success.getId());
                    payTrade.setStatusName(TradeStatusEnum.Success.getName());
                    payTrade.setTradeBuyerId(result.getOpenid());
                    payTrade.setTradeSuccessTime(LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));
                    payTrade.setTradeNo(result.getTransactionId());

                    payStorage.updateTrade(payTrade);

                    StarTigerContext.publishEvent(new PaySuccessEvent(new PaySuccessEvent.PaySuccessEventSource(payTrade)));
                }
                if (StrUtil.equals(PayConstant.Wxpay.TRADE_STATUS_CLOSED, result.getTradeState())
                        || StrUtil.equals(PayConstant.Wxpay.TRADE_STATUS_PAYERROR, result.getTradeState())) {
                    payTrade.setStatusId(TradeStatusEnum.Closed.getId());
                    payTrade.setStatusName(TradeStatusEnum.Closed.getName());
                    payStorage.updateTrade(payTrade);
                }
            } else {
                if (StrUtil.equals(result.getErrCode(), SUB_CODE_ORDERNOTEXIST)) {
                    throw new PayChannelRemoteTradeNotExistException(result.getErrCode(), result.getErrCodeDes());
                } else {
                    throw new PayChannelRemoteException(result.getErrCode(), result.getErrCodeDes());
                }
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("微信订单查询失败.[" + payTrade.getOutTradeNo() + "]", e);
        }
    }

    @Override
    public boolean close(PayTrade payTrade) {
        String appId = getAppIdByBizType(payTrade.getBizType(), PayChannelEnum.Wxpay);
        WxPayServiceImpl wxPayService;
        try {
            wxPayService = StarTigerContext.getBean(PayConstant.Wxpay.BEAN_PREFIX + appId, WxPayServiceImpl.class);
        } catch (BeansException e) {
            throw new PaySdkBeanNotFoundException(PayConstant.Wxpay.BEAN_PREFIX + appId, e);
        }
        try {
            WxPayOrderCloseResult result = wxPayService.closeOrder(payTrade.getOutTradeNo());
            if (StrUtil.equals(PayConstant.Wxpay.STATUS_SUCCESS, result.getReturnCode())
                    && StrUtil.equals(PayConstant.Wxpay.STATUS_SUCCESS, result.getResultCode())) {
                payTrade.setTradeStatus(PayConstant.Wxpay.TRADE_STATUS_CLOSED);
                payTrade.setStatusId(TradeStatusEnum.Closed.getId());
                payTrade.setStatusName(TradeStatusEnum.Closed.getName());
                payStorage.updateTrade(payTrade);
                return true;
            } else {
                throw new BusinessException("微信订单关闭失败.[" + result.getReturnCode() + "|" + result.getReturnMsg() + "|" + result.getResultCode() + "|" + result.getErrCode() + "|" + result.getErrCodeDes() + "|" + payTrade.getOutTradeNo() + "]");
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("微信订单关闭失败.[" + payTrade.getOutTradeNo() + "]", e);
        }
    }

    @Override
    public void doNotify(String payWay, String notifyData) {
        String appId = getAppIdByPayWay(payWay, PayChannelEnum.Wxpay);
        PayNotify payNotify = new PayNotify();
        payNotify.setNotifyTime(LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));
        payNotify.setNotifyTypeId(NotifyTypeEnum.PayNotify.getId());
        payNotify.setNotifyTypeName(NotifyTypeEnum.PayNotify.getName());
        payNotify.setPayChannelId(PayChannelEnum.Wxpay.getId());
        payNotify.setPayChannelName(PayChannelEnum.Wxpay.getName());
        payNotify.setPayWay(payWay);
        payNotify.setNotifyContent(notifyData);
        payStorage.saveNotify(payNotify);
        WxPayServiceImpl wxPayService;
        try {
            wxPayService = StarTigerContext.getBean(PayConstant.Wxpay.BEAN_PREFIX + appId, WxPayServiceImpl.class);
        } catch (BeansException e) {
            throw new PaySdkBeanNotFoundException(PayConstant.Wxpay.BEAN_PREFIX + appId, e);
        }
        try {
            WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(notifyData);
            PayTrade payTrade = payStorage.getTradeByOutTradeNo(notifyResult.getOutTradeNo());
            payTrade.setTradeStatus(notifyResult.getResultCode());
            payTrade.setTradeNo(notifyResult.getTransactionId());
            if (StrUtil.equals(PayConstant.Wxpay.STATUS_SUCCESS, notifyResult.getReturnCode())
                    && StrUtil.equals(PayConstant.Wxpay.STATUS_SUCCESS, notifyResult.getResultCode())) {
                payTrade.setStatusId(TradeStatusEnum.Success.getId());
                payTrade.setStatusName(TradeStatusEnum.Success.getName());
                payTrade.setTradeBuyerId(notifyResult.getOpenid());
                payTrade.setTradeSuccessTime(notifyResult.getTimeEnd());
            }

            payStorage.updateTrade(payTrade);

            StarTigerContext.publishEvent(new PaySuccessEvent(new PaySuccessEvent.PaySuccessEventSource(payTrade)));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("微信订单通知处理失败.[" + notifyData + "]", e);
        }
    }

    @Override
    public RefundInfo refund(PayTrade payTrade, BigDecimal refundPrice) {
        String appId = payTrade.getPayAppId();
        PayChannelEnum wxpayChannelEnum = PayChannelEnum.Wxpay;
        String payWay = getPayWayByBizType(payTrade.getBizType(), wxpayChannelEnum);
        WxpayProperties wxpayProperties = getProperties(payWay, wxpayChannelEnum);
        WxPayServiceImpl wxPayService;
        try {
            wxPayService = StarTigerContext.getBean(PayConstant.Wxpay.BEAN_PREFIX + appId, WxPayServiceImpl.class);
        } catch (BeansException e) {
            throw new PaySdkBeanNotFoundException(PayConstant.Wxpay.BEAN_PREFIX + appId, e);
        }
        String outRefundNo;
        // 加锁获取唯一订单号
        synchronized (TRADE_NO_LOCK) {
            outRefundNo = payStorage.createOutRefundNo();
        }
        try {
            PayRefund payRefund = new PayRefund();
            payRefund.setTradeFlow(payTrade.getTradeFlow());
            payRefund.setStatusId(RefundStatusEnum.Success.getId());
            payRefund.setStatusName(RefundStatusEnum.Success.getName());
            payRefund.setPayChannelId(payTrade.getPayChannelId());
            payRefund.setPayChannelName(payTrade.getPayChannelName());
            payRefund.setPayAppId(payTrade.getPayAppId());
            payRefund.setOutTradeNo(payTrade.getOutTradeNo());
            payRefund.setPaymentMethod(payTrade.getPaymentMethod());
            payRefund.setOutRefundNo(outRefundNo);
            payRefund.setTradePrice(payTrade.getTradePrice());
            payRefund.setRefundPrice(refundPrice);
            payRefund.setRefundCreateTime(LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));

            WxPayRefundRequest refundRequest = WxPayRefundRequest.newBuilder()
                    .outTradeNo(payTrade.getOutTradeNo())
                    .outRefundNo(outRefundNo)
                    .totalFee(BaseWxPayRequest.yuanToFen(payTrade.getTradePrice().toString()))
                    .refundFee(BaseWxPayRequest.yuanToFen(refundPrice.toString()))
                    .notifyUrl(wxpayProperties.getRefundNotifyUrl())
                    .build();
            WxPayRefundResult refundResult = wxPayService.refund(refundRequest);

            payRefund.setRefundNo(refundResult.getRefundId());
            payStorage.saveRefund(payRefund);

            payTrade.setIsRefund(StarTigerConstant.FLAG_Y);
            payStorage.updateTrade(payTrade);

            return PayDtoUtil.createRefundInfo(payRefund);

        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("微信订单退款失败.[" + payTrade.getTradeFlow() + "|" + payTrade.getOutTradeNo() + "|" + refundPrice + "]", e);
        }
    }

    @Override
    public RefundInfo queryRefund(PayTrade payTrade) {
        return null;
    }

    @Override
    public void doRefundNotify(String payWay, String notifyData) {

    }

    private ChannelClientParam handleResponse(WxPayService wxpayService, WxPayUnifiedOrderRequest request) throws WxPayException {
        switch (request.getTradeType()) {
            case WxPayConstants.TradeType.JSAPI: {
                WxpayJsapiClientParam clientParam = new WxpayJsapiClientParam();
                WxPayMpOrderResult result = wxpayService.createOrder(request);
                BeanUtil.copyProperties(result, clientParam);
                return clientParam;
            }
            default:
                throw new BusinessException("未知的支付方式");
        }
    }
}
