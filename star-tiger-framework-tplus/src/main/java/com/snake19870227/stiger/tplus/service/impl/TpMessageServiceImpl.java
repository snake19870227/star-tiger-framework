package com.snake19870227.stiger.tplus.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snake19870227.stiger.tplus.dao.base.TpMessageMapper;
import com.snake19870227.stiger.tplus.entity.po.TpMessage;
import com.snake19870227.stiger.tplus.service.ITpMessageService;

/**
 * <p>
 * T+消息推送日志 服务实现类
 * </p>
 *
 * @author buhuayang
 * @since 2021-05-25
 */
@Service
public class TpMessageServiceImpl extends ServiceImpl<TpMessageMapper, TpMessage> implements ITpMessageService {

}
