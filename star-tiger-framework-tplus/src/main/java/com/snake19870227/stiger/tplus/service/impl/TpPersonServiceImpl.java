package com.snake19870227.stiger.tplus.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snake19870227.stiger.tplus.dao.base.TpPersonMapper;
import com.snake19870227.stiger.tplus.entity.po.TpPerson;
import com.snake19870227.stiger.tplus.service.ITpPersonService;

/**
 * <p>
 * T+员工 服务实现类
 * </p>
 *
 * @author buhuayang
 * @since 2021-08-16
 */
@Service
public class TpPersonServiceImpl extends ServiceImpl<TpPersonMapper, TpPerson> implements ITpPersonService {

}
