package com.wemirr.platform.tools.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.tools.domain.entity.GenerateRequest;
import com.wemirr.platform.tools.mapper.GenerateMapper;
import com.wemirr.platform.tools.service.GenerateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GenerateServiceImpl extends SuperServiceImpl<GenerateMapper, GenerateRequest> implements GenerateService {

    private final DataSourceProperties dataSourceProperties;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String generate(GenerateRequest request) {
        String rootDir = request.getRootDir();
        if (rootDir == null) {
            rootDir = System.getProperty("user.dir") + "/.generated/";
        }
        String outputDir = rootDir + request.getModuleName() + "/backend/";
        AutoGenerator generator = new AutoGenerator();
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(outputDir);
        globalConfig.setAuthor(request.getAuthor());
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setServiceName("%sService");
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setBaseResultMap(true);
        //  实体属性 Swagger2 注解
        globalConfig.setSwagger2(request.isSwagger2());
        generator.setGlobalConfig(globalConfig);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(dataSourceProperties.getUrl());
        dataSourceConfig.setDriverName(dataSourceProperties.getDriverClassName());
        dataSourceConfig.setUsername(dataSourceProperties.getUsername());
        dataSourceConfig.setPassword(dataSourceProperties.getPassword());
        generator.setDataSource(dataSourceConfig);


        TemplateConfig templateConfig = new TemplateConfig()
                .setEntity("templates/backend/entity.java")
                .setController("templates/backend/controller.java")
                .setMapper("templates/backend/mapper.java")
                .setService("templates/backend/service.java")
                .setServiceImpl("templates/backend/serviceImpl.java")
                .setXml("templates/backend/mapper.xml");

        //配置自定义模板
        generator.setTemplate(templateConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(request.getModuleName());
        pc.setParent(request.getParentPackage());
        pc.setServiceImpl("service");
        pc.setXml("mapper");
        pc.setController("controller");
        generator.setPackageInfo(pc);

        //自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = Maps.newHashMap();
                map.put("apiUrlPrefix", request.getApiUrlPrefix());
                map.put("platformId", request.getPlatformId());
                map.put("now", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                this.setMap(map);
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = Lists.newArrayList();
        Map<String, String> customFiles = Maps.newHashMap();
        customFiles.put("/templates/front/crud.js.ftl", "/crud.js");
        customFiles.put("/templates/front/index.vue.ftl", "/index.vue");
        customFiles.put("/templates/front/api.js.ftl", "/api.js");
        customFiles.put("/templates/front/router.js.ftl", "/router.js");

        customFiles.put("/templates/sql/resource.sql.ftl", "_menu.sql");
        // 自定义配置会被优先输出
        String finalRootDir = rootDir;
        for (Map.Entry<String, String> entry : customFiles.entrySet()) {

            focList.add(new FileOutConfig(entry.getKey()) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    String value = entry.getValue();
                    String key = entry.getKey();
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    String dir = request.getModuleName() + "/front";
                    if (key.endsWith("sql.ftl")) {
                        dir = request.getModuleName() + "/sql";
                    }
                    return finalRootDir + dir + "/" + request.getModuleName() + "/views/" + tableInfo.getEntityPath() + value;
                }
            });
        }

        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);
        generator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(SuperEntity.class);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperServiceImplClass(SuperServiceImpl.class);
        strategy.setSuperServiceClass(SuperService.class);
        strategy.setLogicDeleteFieldName(request.getLogicDeleteField());
        strategy.setInclude(request.getTableName());

        strategy.setTableFillList(request.getFillList());

        //是否驼峰转连接字符
        strategy.setControllerMappingHyphenStyle(false);
        strategy.setTablePrefix(request.getTablePrefix());
        generator.setStrategy(strategy);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
        log.info("{}生成完成:{}", request.getTableName(), rootDir);
        return rootDir;

    }
}
