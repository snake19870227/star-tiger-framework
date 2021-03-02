package com.snake19870227.stiger.pay.server;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.admin.dao.base.SysCfgMapper;
import com.snake19870227.stiger.admin.entity.po.SysCfg;
import com.snake19870227.stiger.pay.PayConstant;
import com.snake19870227.stiger.pay.channel.IPayStorage;
import com.snake19870227.stiger.pay.dao.base.PayNotifyMapper;
import com.snake19870227.stiger.pay.dao.base.PayRefundMapper;
import com.snake19870227.stiger.pay.dao.base.PayTradeMapper;
import com.snake19870227.stiger.pay.entity.po.PayNotify;
import com.snake19870227.stiger.pay.entity.po.PayRefund;
import com.snake19870227.stiger.pay.entity.po.PayTrade;
import com.snake19870227.stiger.pay.enums.TradeStatusEnum;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DatabasePayStorageImpl implements IPayStorage {

    private static final Logger logger = LoggerFactory.getLogger(DatabasePayStorageImpl.class);

    private final SysCfgMapper sysCfgMapper;

    private final PayTradeMapper payTradeMapper;

    private final PayRefundMapper payRefundMapper;

    private final PayNotifyMapper payNotifyMapper;

    public DatabasePayStorageImpl(SysCfgMapper sysCfgMapper,
                                  PayTradeMapper payTradeMapper,
                                  PayRefundMapper payRefundMapper,
                                  PayNotifyMapper payNotifyMapper) {
        this.sysCfgMapper = sysCfgMapper;
        this.payTradeMapper = payTradeMapper;
        this.payRefundMapper = payRefundMapper;
        this.payNotifyMapper = payNotifyMapper;
    }

    @Override
    public String createOutTradeNo() {

        String currMonth = LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMM");

        String cfgCode = PayConstant.OUT_TRADE_NO_CFG_PREFIX + currMonth;

        SysCfg cfg = sysCfgMapper.selectById(cfgCode);

        int i = 1;
        if (cfg == null) {
            cfg = new SysCfg();
            cfg.setCfgCode(cfgCode);
            cfg.setCfgValue(String.valueOf(i));
            sysCfgMapper.insert(cfg);
        } else {
            i = Integer.parseInt(cfg.getCfgValue());
            i++;
            cfg.setCfgValue(String.valueOf(i));
            sysCfgMapper.updateById(cfg);
        }

        String no = NumberUtil.decimalFormat("0000000000", i);

        return currMonth + no;
    }

    @Override
    public String createOutRefundNo() {

        String currMonth = LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMM");

        String cfgCode = PayConstant.OUT_REFUND_NO_CFG_PREFIX + currMonth;

        SysCfg cfg = sysCfgMapper.selectById(cfgCode);

        int i = 1;
        if (cfg == null) {
            cfg = new SysCfg();
            cfg.setCfgCode(cfgCode);
            cfg.setCfgValue(String.valueOf(i));
            sysCfgMapper.insert(cfg);
        } else {
            i = Integer.parseInt(cfg.getCfgValue());
            i++;
            cfg.setCfgValue(String.valueOf(i));
            sysCfgMapper.updateById(cfg);
        }

        String no = NumberUtil.decimalFormat("0000000000", i);

        return currMonth + no;
    }

    @Override
    public PayTrade getTradeByOutTradeNo(String outTradeNo) {
        QueryWrapper<PayTrade> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("out_trade_no", outTradeNo);
        return payTradeMapper.selectOne(queryWrapper);
    }

    @Override
    public void saveTrade(PayTrade trade) {
        payTradeMapper.insert(trade);
    }

    @Override
    public void updateTrade(PayTrade trade) {
        payTradeMapper.updateById(trade);
    }

    @Override
    public void saveRefund(PayRefund refund) {
        payRefundMapper.insert(refund);
    }

    @Override
    public void saveNotify(PayNotify notify) {
        payNotifyMapper.insert(notify);
    }

    @Override
    public PayTrade loadExistsTrade(String bizFlow) {
        QueryWrapper<PayTrade> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("biz_flow", bizFlow)
                .ne("status_id", TradeStatusEnum.Closed.getId())
        ;
        List<PayTrade> payTrades = payTradeMapper.selectList(queryWrapper);
        return payTrades.isEmpty() ? null : payTrades.get(0);
    }
}
