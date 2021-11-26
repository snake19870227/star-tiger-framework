package com.snake19870227.stiger.tplus.openapi;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.snake19870227.stiger.core.exception.BusinessException;
import com.snake19870227.stiger.core.utils.JsonUtil;
import com.snake19870227.stiger.tplus.entity.po.TpToken;
import com.snake19870227.stiger.tplus.openapi.entity.dto.ExpenseVoucher;
import com.snake19870227.stiger.tplus.openapi.entity.dto.Inventory;
import com.snake19870227.stiger.tplus.openapi.entity.dto.Partner;
import com.snake19870227.stiger.tplus.openapi.entity.dto.Person;
import com.snake19870227.stiger.tplus.openapi.entity.dto.SaleDelivery;
import com.snake19870227.stiger.tplus.openapi.entity.dto.Warehouse;

/**
 * @author BuHuaYang
 * 8/10 010
 */
public class ChanjetTplusOpenApiService {

    private static final Logger logger = LoggerFactory.getLogger(ChanjetTplusOpenApiService.class);

    public static final String WAREHOUSE_SELECT_FIELDS = "ID,Code,Name,WarehouseType";

    public static final String INVENTORY_SELECT_FIELDS = "ID,Code,Name,Specification,InventoryClass.Code,InventoryClass.Name,Unit.Code,Unit.Name,BaseUnitCode,BaseUnitName,Disabled,UnitByPurchase.Code,UnitByPurchase.Name,UnitBySale.Code,UnitBySale.Name,UnitByStock.Code,UnitByStock.Name,UnitByRetail.Code,UnitByRetail.Name,UnitByManufacture.Code,UnitByManufacture.Name";

    private final ObjectMapper objectMapper;

    private final ChanjetClient chanjetClient;

    @Autowired
    private AccessTokenStorage accessTokenStorage;

    public ChanjetTplusOpenApiService(ChanjetClient chanjetClient) {
        this.chanjetClient = chanjetClient;
        this.objectMapper = JsonUtil.buildJacksonObjectMapper();
    }

    public List<Warehouse> queryWarehouse() {
        try {
            HashMap<String, HashMap<String, String>> paramMap = MapUtil.of("param", MapUtil.of("SelectFields", WAREHOUSE_SELECT_FIELDS));
            String jsonString = chanjetClient.tplusPost("/tplus/api/v2/warehouse/Query", paramMap, accessTokenStorage.getAccessToken().getAccessToken());
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            if (jsonNode instanceof ArrayNode) {
                List<Warehouse> results = new ArrayList<>();
                Iterator<JsonNode> elements = jsonNode.elements();
                while (elements.hasNext()) {
                    JsonNode element = elements.next();
                    Warehouse warehouse = objectMapper.readValue(element.toString(), Warehouse.class);
                    results.add(warehouse);
                }
                return results;
            }
            throw new BusinessException("查询仓库失败");
        } catch (Exception e) {
            logger.error("查询仓库失败", e);
            throw new BusinessException("查询仓库失败", e);
        }
    }

    public List<Inventory> queryInventory() {
        try {
            HashMap<String, HashMap<String, String>> paramMap = MapUtil.of("param", MapUtil.of("SelectFields", INVENTORY_SELECT_FIELDS));
            String jsonString = chanjetClient.tplusPost("/tplus/api/v2/inventory/Query", paramMap, accessTokenStorage.getAccessToken().getAccessToken());
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            if (jsonNode instanceof ArrayNode) {
                List<Inventory> results = new ArrayList<>();
                Iterator<JsonNode> elements = jsonNode.elements();
                while (elements.hasNext()) {
                    JsonNode element = elements.next();
                    Inventory inventory = objectMapper.readValue(element.toString(), Inventory.class);
                    results.add(inventory);
                }
                return results;
            }
            throw new BusinessException("查询存货失败");
        } catch (Exception e) {
            logger.error("查询存货失败", e);
            throw new BusinessException("查询存货失败", e);
        }
    }

    public List<Partner> queryPartner(String name) {
        try {
            String paramString = "{\"param\":{}}";
            if (StrUtil.isNotBlank(name)) {
                HashMap<String, HashMap<String, String>> paramMap = MapUtil.of("param", MapUtil.of("Name", name));
                paramString = JSONUtil.toJsonStr(paramMap);
            }
            String jsonString = chanjetClient.tplusPost("/tplus/api/v2/partner/Query", paramString, accessTokenStorage.getAccessToken().getAccessToken());
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            if (jsonNode instanceof ArrayNode) {
                List<Partner> results = new ArrayList<>();
                Iterator<JsonNode> elements = jsonNode.elements();
                while (elements.hasNext()) {
                    JsonNode element = elements.next();
                    Partner partner = objectMapper.readValue(element.toString(), Partner.class);
                    results.add(partner);
                }
                return results;
            }
            throw new BusinessException("查询往来单位失败");
        } catch (Exception e) {
            logger.error("查询往来单位失败", e);
            throw new BusinessException("查询往来单位失败", e);
        }
    }

    public List<Person> queryPerson() {
        try {
            String jsonString = chanjetClient.tplusPost("/tplus/api/v2/person/Query", "{\"dto\":{}}", accessTokenStorage.getAccessToken().getAccessToken());
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            if (jsonNode instanceof ArrayNode) {
                List<Person> results = new ArrayList<>();
                Iterator<JsonNode> elements = jsonNode.elements();
                while (elements.hasNext()) {
                    JsonNode element = elements.next();
                    Person person = objectMapper.readValue(element.toString(), Person.class);
                    results.add(person);
                }
                return results;
            }
            throw new BusinessException("查询员工失败");
        } catch (Exception e) {
            logger.error("查询员工失败", e);
            throw new BusinessException("查询员工失败", e);
        }
    }

    public String createSaleDelivery(SaleDelivery saleDelivery, String userId) {
        HashMap<String, SaleDelivery> dto = MapUtil.of("dto", saleDelivery);
        TpToken accessToken;
        if (StrUtil.isNotBlank(userId)) {
            accessToken = accessTokenStorage.getAccessToken(userId);
        } else {
            accessToken = accessTokenStorage.getAccessToken();
        }
        if (accessToken == null) {
            throw new BusinessException("未找到授权令牌");
        }
        return chanjetClient.tplusPost("/tplus/api/v2/saleDelivery/Create", dto, accessToken.getAccessToken());
    }

    public String createExpenseVoucher(ExpenseVoucher expenseVoucher, String userId) {
        HashMap<String, ExpenseVoucher> dto = MapUtil.of("dto", expenseVoucher);
        TpToken accessToken;
        if (StrUtil.isNotBlank(userId)) {
            accessToken = accessTokenStorage.getAccessToken(userId);
        } else {
            accessToken = accessTokenStorage.getAccessToken();
        }
        if (accessToken == null) {
            throw new BusinessException("未找到授权令牌");
        }
        return chanjetClient.tplusPost("/tplus/api/v2/expenseVoucher/CreateExpenseVoucher", dto, accessToken.getAccessToken());
    }

}
