package com.snake19870227.stiger.pay.server;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.admin.dao.base.SysCfgMapper;
import com.snake19870227.stiger.admin.entity.po.SysCfg;
import com.snake19870227.stiger.pay.PayConstant;
import com.snake19870227.stiger.pay.channel.IPayStorage;
import com.snake19870227.stiger.pay.dao.base.PayTradeMapper;
import com.snake19870227.stiger.pay.entity.po.PayNotify;
import com.snake19870227.stiger.pay.entity.po.PayRefund;
import com.snake19870227.stiger.pay.entity.po.PayTrade;

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

    public DatabasePayStorageImpl(SysCfgMapper sysCfgMapper, PayTradeMapper payTradeMapper) {
        this.sysCfgMapper = sysCfgMapper;
        this.payTradeMapper = payTradeMapper;
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
