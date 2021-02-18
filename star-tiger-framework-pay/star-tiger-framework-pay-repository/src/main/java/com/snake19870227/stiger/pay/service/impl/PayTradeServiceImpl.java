package com.snake19870227.stiger.pay.service.impl;

import com.snake19870227.stiger.pay.entity.po.PayTrade;
import com.snake19870227.stiger.pay.dao.base.PayTradeMapper;
import com.snake19870227.stiger.pay.service.IPayTradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付订单 服务实现类
 * </p>
 *
 * @author buhuayang
 * @since 2021-02-10
 */
@Service
public class PayTradeServiceImpl extends ServiceImpl<PayTradeMapper, PayTrade> implements IPayTradeService {

}
