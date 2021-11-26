package com.snake19870227.stiger.tplus.server.server.svc.impl;

import cn.hutool.core.util.IdUtil;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snake19870227.stiger.tplus.entity.po.TpInventory;
import com.snake19870227.stiger.tplus.entity.po.TpPartner;
import com.snake19870227.stiger.tplus.entity.po.TpPerson;
import com.snake19870227.stiger.tplus.entity.po.TpWarehouse;
import com.snake19870227.stiger.tplus.openapi.ChanjetTplusOpenApiService;
import com.snake19870227.stiger.tplus.openapi.entity.dto.Inventory;
import com.snake19870227.stiger.tplus.openapi.entity.dto.Partner;
import com.snake19870227.stiger.tplus.openapi.entity.dto.Person;
import com.snake19870227.stiger.tplus.openapi.entity.dto.Warehouse;
import com.snake19870227.stiger.tplus.server.server.svc.ITplusSyncSvc;
import com.snake19870227.stiger.tplus.service.ITpInventoryService;
import com.snake19870227.stiger.tplus.service.ITpPartnerService;
import com.snake19870227.stiger.tplus.service.ITpPersonService;
import com.snake19870227.stiger.tplus.service.ITpWarehouseService;
import com.snake19870227.stiger.tplus.util.EntityUtil;

@Component
public class TplusSyncSvcImpl implements ITplusSyncSvc {

    private static final Logger logger = LoggerFactory.getLogger(TplusSyncSvcImpl.class);

    @Autowired
    private ChanjetTplusOpenApiService tplusOpenApiService;

    private final ITpWarehouseService tpWarehouseService;

    private final ITpInventoryService tpInventoryService;

    private final ITpPartnerService tpPartnerService;

    private final ITpPersonService tpPersonService;

    public TplusSyncSvcImpl(ITpWarehouseService tpWarehouseService,
                            ITpInventoryService tpInventoryService,
                            ITpPartnerService tpPartnerService,
                            ITpPersonService tpPersonService) {
        this.tpWarehouseService = tpWarehouseService;
        this.tpInventoryService = tpInventoryService;
        this.tpPartnerService = tpPartnerService;
        this.tpPersonService = tpPersonService;
    }

    @Override
    public void syncWarehouse() {
        logger.info("开始同步仓库");
        List<Warehouse> warehouses = tplusOpenApiService.queryWarehouse();
        warehouses.stream().map(EntityUtil::toTpWarehouse).forEach(tpWarehouse -> {
            QueryWrapper<TpWarehouse> wrapper = new QueryWrapper<>();
            wrapper.eq("tp_code", tpWarehouse.getTpCode());
            TpWarehouse existWarehouse = tpWarehouseService.getOne(wrapper);
            if (existWarehouse != null) {
                tpWarehouse.setFlow(existWarehouse.getFlow());
            } else {
                tpWarehouse.setFlow(IdUtil.fastSimpleUUID());
            }
            tpWarehouseService.saveOrUpdate(tpWarehouse);
        });
    }

    @Override
    public void syncInventory() {
        logger.info("开始同步存货");
        List<Inventory> warehouses = tplusOpenApiService.queryInventory();
        warehouses.stream().map(EntityUtil::toTpInventory).forEach(tpInventory -> {
            QueryWrapper<TpInventory> wrapper = new QueryWrapper<>();
            wrapper.eq("tp_code", tpInventory.getTpCode());
            TpInventory existData = tpInventoryService.getOne(wrapper);
            if (existData != null) {
                tpInventory.setFlow(existData.getFlow());
            } else {
                tpInventory.setFlow(IdUtil.fastSimpleUUID());
            }
            tpInventoryService.saveOrUpdate(tpInventory);
        });
    }

    @Override
    public void syncPartner() {
        logger.info("开始同步往来单位");
        List<Partner> partners = tplusOpenApiService.queryPartner(null);
        partners.stream().map(EntityUtil::toTpPartner).forEach(tpPartner -> {
            QueryWrapper<TpPartner> wrapper = new QueryWrapper<>();
            wrapper.eq("tp_code", tpPartner.getTpCode());
            TpPartner existData = tpPartnerService.getOne(wrapper);
            if (existData != null) {
                tpPartner.setFlow(existData.getFlow());
            } else {
                tpPartner.setFlow(IdUtil.fastSimpleUUID());
            }
            tpPartnerService.saveOrUpdate(tpPartner);
        });
    }

    @Override
    public void syncPerson() {
        logger.info("开始同步员工");
        List<Person> partners = tplusOpenApiService.queryPerson();
        partners.stream().map(EntityUtil::toTpPerson).forEach(tpPerson -> {
            QueryWrapper<TpPerson> wrapper = new QueryWrapper<>();
            wrapper.eq("tp_code", tpPerson.getTpCode());
            TpPerson existData = tpPersonService.getOne(wrapper);
            if (existData != null) {
                tpPerson.setFlow(existData.getFlow());
            } else {
                tpPerson.setFlow(IdUtil.fastSimpleUUID());
            }
            tpPersonService.saveOrUpdate(tpPerson);
        });
    }
}
