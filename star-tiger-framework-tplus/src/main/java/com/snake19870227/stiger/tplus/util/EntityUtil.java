package com.snake19870227.stiger.tplus.util;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDateTime;

import com.snake19870227.stiger.tplus.entity.po.TpInventory;
import com.snake19870227.stiger.tplus.entity.po.TpPartner;
import com.snake19870227.stiger.tplus.entity.po.TpPerson;
import com.snake19870227.stiger.tplus.entity.po.TpToken;
import com.snake19870227.stiger.tplus.entity.po.TpWarehouse;
import com.snake19870227.stiger.tplus.openapi.entity.dto.AccessToken;
import com.snake19870227.stiger.tplus.openapi.entity.dto.Inventory;
import com.snake19870227.stiger.tplus.openapi.entity.dto.Partner;
import com.snake19870227.stiger.tplus.openapi.entity.dto.Person;
import com.snake19870227.stiger.tplus.openapi.entity.dto.Warehouse;

/**
 * @author BuHuaYang
 * 8/7 007
 */
public class EntityUtil {

    public static TpToken accessTokenToPo(AccessToken accessToken) {
        TpToken token = new TpToken();
        LocalDateTime now = LocalDateTime.now();
        String datetime = LocalDateTimeUtil.format(now, "yyyyMMddHHmmss");
        String expiresDatetime = LocalDateTimeUtil.format(now.plusSeconds(accessToken.getExpiresIn()), "yyyyMMddHHmmss");
        String refreshExpiresDatetime = LocalDateTimeUtil.format(now.plusSeconds(accessToken.getRefreshExpiresIn()), "yyyyMMddHHmmss");
        token.setDatetime(datetime)
                .setAccessToken(accessToken.getAccessToken())
                .setExpiresIn(accessToken.getExpiresIn())
                .setExpiresDatetime(expiresDatetime)
                .setRefreshToken(accessToken.getRefreshToken())
                .setRefreshExpiresIn(accessToken.getRefreshExpiresIn())
                .setRefreshExpiresDatetime(refreshExpiresDatetime)
                .setScope(accessToken.getScope())
                .setUserId(accessToken.getUserId())
                .setOrgId(accessToken.getOrgId())
                .setAppName(accessToken.getAppName())
                .setSid(accessToken.getSid())
                .setUserAuthPermanentCode(accessToken.getUserAuthPermanentCode());
        return token;
    }

    public static TpWarehouse toTpWarehouse(Warehouse warehouse) {
        TpWarehouse tpWarehouse = new TpWarehouse();
        tpWarehouse.setTpId(String.valueOf(warehouse.getId()))
                .setTpCode(warehouse.getCode())
                .setTpName(warehouse.getName());
        return tpWarehouse;
    }

    public static TpInventory toTpInventory(Inventory inventory) {
        TpInventory tpInventory = new TpInventory();
        tpInventory.setTpId(String.valueOf(inventory.getId()))
                .setTpCode(inventory.getCode())
                .setTpName(inventory.getName())
                .setInventoryClassCode(inventory.getInventoryClassCode())
                .setInventoryClassName(inventory.getInventoryClassName())
                .setUnitCode(inventory.getUnitCode())
                .setUnitName(inventory.getUnitName())
                .setBaseUnitCode(inventory.getBaseUnitCode())
                .setBaseUnitName(inventory.getBaseUnitName())
                .setTpDisabled(inventory.getDisabled())
                .setUnitPurchaseCode(inventory.getUnitByPurchaseCode())
                .setUnitPurchaseName(inventory.getUnitByPurchaseName())
                .setUnitSaleCode(inventory.getUnitBySaleCode())
                .setUnitSaleName(inventory.getUnitBySaleName())
                .setUnitStockCode(inventory.getUnitByStockCode())
                .setUnitStockName(inventory.getUnitByStockName())
                .setUnitRetailCode(inventory.getUnitByRetailCode())
                .setUnitRetailName(inventory.getUnitByRetailName())
                .setUnitManufactureCode(inventory.getUnitByManufactureCode())
                .setUnitManufactureName(inventory.getUnitByManufactureName());
        return tpInventory;
    }

    public static TpPartner toTpPartner(Partner partner) {
        TpPartner tpPartner = new TpPartner();
        tpPartner.setTpCode(partner.getCode())
                .setTpName(partner.getName())
                .setPartnerTypeName(partner.getPartnerType().getName());
        return tpPartner;
    }

    public static TpPerson toTpPerson(Person person) {
        TpPerson tpPerson = new TpPerson();
        tpPerson.setTpCode(person.getCode())
                .setTpId(String.valueOf(person.getId()))
                .setTpName(person.getName())
                .setTpDeptId(String.valueOf(person.getDepartment().getId()))
                .setTpDeptCode(person.getDepartment().getCode())
                .setTpDeptName(person.getDepartment().getName());
        return tpPerson;
    }
}
