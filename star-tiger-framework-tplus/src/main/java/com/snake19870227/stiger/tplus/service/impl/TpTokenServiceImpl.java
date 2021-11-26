package com.snake19870227.stiger.tplus.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snake19870227.stiger.tplus.dao.base.TpTokenMapper;
import com.snake19870227.stiger.tplus.entity.po.TpToken;
import com.snake19870227.stiger.tplus.service.ITpTokenService;

/**
 * <p>
 * T+操作token 服务实现类
 * </p>
 *
 * @author buhuayang
 * @since 2021-08-25
 */
@Service
public class TpTokenServiceImpl extends ServiceImpl<TpTokenMapper, TpToken> implements ITpTokenService {

}
