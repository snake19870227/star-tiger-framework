package com.snake19870227.stiger.pay.service.impl;

import com.snake19870227.stiger.pay.entity.po.PayNotify;
import com.snake19870227.stiger.pay.dao.base.PayNotifyMapper;
import com.snake19870227.stiger.pay.service.IPayNotifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 异步通知 服务实现类
 * </p>
 *
 * @author buhuayang
 * @since 2021-02-10
 */
@Service
public class PayNotifyServiceImpl extends ServiceImpl<PayNotifyMapper, PayNotify> implements IPayNotifyService {

}
