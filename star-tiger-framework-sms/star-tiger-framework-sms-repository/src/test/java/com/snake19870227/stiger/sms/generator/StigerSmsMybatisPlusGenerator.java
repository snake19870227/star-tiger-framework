package com.snake19870227.stiger.sms.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.mysql.cj.jdbc.Driver;

/**
 * @author Bu HuaYang
 */
public class StigerSmsMybatisPlusGenerator {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/stigeradmin?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName(Driver.class.getName());
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("D:\\mycode\\star-tiger-framework\\star-tiger-framework-sms\\star-tiger-framework-sms-repository\\src\\main\\java");
        gc.setAuthor("buhuayang");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setIdType(IdType.ASSIGN_UUID);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        PackageConfig pc = new PackageConfig();
        pc.setParent("com.snake19870227.stiger.sms");
        pc.setEntity("entity.po");
        pc.setMapper("dao.base");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
//        pc.setXml("dao.base");
        mpg.setPackageInfo(pc);

        TemplateConfig tc = new TemplateConfig();
        tc
//                .setService(null)
//                .setServiceImpl(null)
                .setController(null)
//                .setMapper(null)
                .setXml(null)
        ;
        mpg.setTemplate(tc);

        StrategyConfig strategy = new StrategyConfig();
        strategy.setChainModel(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setLogicDeleteFieldName("record_status");

        strategy.setInclude(
                "sms_log",
                "sms_template"
        );

        mpg.setStrategy(strategy);

        mpg.execute();
    }
}
