package com.snake19870227.stiger.tplus.server.server.config;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.snake19870227.stiger.tplus.entity.po.HpryDwInventory;
import com.snake19870227.stiger.tplus.entity.po.HpryDwPartner;
import com.snake19870227.stiger.tplus.service.IHpryDwInventoryService;
import com.snake19870227.stiger.tplus.service.IHpryDwPartnerService;

@Component
public class Init implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(Init.class);

    public static Map<String, String> kaSystems = new HashMap<>();
    public static Map<String, String> kaCars = new HashMap<>();
    public static Map<String, String> dwcps = new HashMap<>();
    public static Map<String, String> dwwldws = new HashMap<>();

    @Value("${hpry.tools.tmpdir}")
    public String tmpdir;

    private final IHpryDwPartnerService hpryDwPartnerService;

    private final IHpryDwInventoryService hpryDwInventoryService;

    public Init(IHpryDwPartnerService hpryDwPartnerService, IHpryDwInventoryService hpryDwInventoryService) {
        this.hpryDwPartnerService = hpryDwPartnerService;
        this.hpryDwInventoryService = hpryDwInventoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ClassPathResource kaSystemResource = new ClassPathResource("ka系统.xlsx");
        ClassPathResource kaCarResource = new ClassPathResource("ka车辆.xlsx");
//        ClassPathResource dwcpResource = new ClassPathResource("低温产品.xlsx");
//        ClassPathResource dwwldwResource = new ClassPathResource("低温往来单位.xlsx");

        ExcelReader kaSystemReader = ExcelUtil.getReader(kaSystemResource.getStream());
        List<Map<String, Object>> kaSystemMaps = kaSystemReader.readAll();
        for (Map<String, Object> kaSystemMap : kaSystemMaps) {
            kaSystems.put((String) kaSystemMap.get("ka系统"), (String) kaSystemMap.get("编码"));
        }

        ExcelReader carReader = ExcelUtil.getReader(kaCarResource.getStream());
        List<Map<String, Object>> carMaps = carReader.readAll();
        for (Map<String, Object> carMap : carMaps) {
            kaCars.put((String) carMap.get("车辆"), (String) carMap.get("仓库编码"));
        }

        reloadDwInventory();
        reloadDwPartner();
    }

    public void reloadDwInventory() {
        List<HpryDwInventory> dwInventories = hpryDwInventoryService.list();
        for (HpryDwInventory dwInventory : dwInventories) {
            dwcps.put(dwInventory.getTemplateName(), dwInventory.getTpCode());
        }
    }

    public void reloadDwPartner() {
        List<HpryDwPartner> dwPartners = hpryDwPartnerService.list();
        for (HpryDwPartner dwPartner : dwPartners) {
            dwwldws.put(dwPartner.getTemplateName(), dwPartner.getTpCode());
        }
    }
}
