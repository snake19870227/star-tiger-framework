package com.snake19870227.stiger.pay.channel;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import com.snake19870227.stiger.pay.entity.po.PayNotify;
import com.snake19870227.stiger.pay.entity.po.PayRefund;
import com.snake19870227.stiger.pay.entity.po.PayTrade;

/**
 * @author BuHuaYang
 * 2021/2/15
 */
@Service
public class SamplePayStorageImpl implements IPayStorage {

    @Override
    public String createOutTradeNo() {
        String datetime = LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMdd");
        return null;
    }

    @Override
    public String createOutRefundNo() {
        return null;
    }

    @Override
    public PayTrade getTradeByOutTradeNo(String outTradeNo) {
        return null;
    }

    @Override
    public void saveTrade(PayTrade trade) {

    }

    @Override
    public void updateTrade(PayTrade trade) {

    }

    @Override
    public void saveRefund(PayRefund refund) {

    }

    @Override
    public void saveNotify(PayNotify notify) {

    }
}
