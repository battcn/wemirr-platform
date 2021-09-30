package com.wemirr.platform;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.collect.Maps;
import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.tools.domain.entity.GenerateRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Slf4j
class CodeGeneratorTests {


    @Test
    void sys() {
        final GenerateRequest request = new GenerateRequest();
        request.setAuthor("Levin");
        request.setTableName("t_dynamic_datasource");
        request.setTablePrefix("t_");
        generate(request);
    }

    public String generate(GenerateRequest request) {
        Map<String, Object> customMap = Maps.newHashMap();
        customMap.put("apiUrlPrefix", request.getApiUrlPrefix());
        customMap.put("platformId", request.getPlatformId());
        customMap.put("now", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

        Map<String, String> customFiles = Maps.newHashMap();
//        customFiles.put("/templates/front/crud.js.ftl", "/crud.js");
//        customFiles.put("/templates/front/index.vue.ftl", "/index.vue");
//        customFiles.put("/templates/front/api.js.ftl", "/api.js");
//        customFiles.put("/templates/sql/resource.sql.ftl", "_menu.sql");

//        final String rootDir = StringUtils.defaultString(request.getRootDir(), System.getProperty("user.dir") + "/.generated/");
        String rootDir = "/Users/battcn/Development/opensource/wemirr-platform/wemirr-platform-tools/src/main/java/com/wemirr/platform/tools/logs";
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/wemirr-platform?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true", "root", "123456")
                .globalConfig(builder -> builder.author(request.getAuthor()).enableSwagger().fileOverride().outputDir(rootDir))
                .packageConfig(builder -> builder.parent(request.getParentPackage()).moduleName(request.getModuleName())
                        .entity("domain").mapper("mapper").xml("mapper.xml")
                        .service("service").serviceImpl("service.impl").controller("controller")
                        // 设置mapperXml生成路径
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, rootDir)))
                .strategyConfig((builder) -> builder.enableCapitalMode()
                        .enableSkipView().disableSqlFilter()
                        .addInclude(request.getTableName())
                        .addTablePrefix(request.getTablePrefix())
                        .entityBuilder()
                        .naming(NamingStrategy.underline_to_camel).columnNaming(NamingStrategy.underline_to_camel)
                        .logicDeleteColumnName(request.getLogicDeleteField())
                        .enableLombok().superClass(SuperEntity.class)
                        .serviceBuilder().superServiceClass(SuperService.class).superServiceImplClass(SuperServiceImpl.class)
                        .controllerBuilder().enableRestStyle())
                .templateConfig(builder -> builder
//                        .entity("/templates/backend/entity.java")
                        .service("/templates/backend/service.java")
                        .serviceImpl("/templates/backend/serviceImpl.java")
                        .mapper("/templates/backend/mapper.java")
                        .mapperXml("/templates/backend/mapper.xml")
                        .controller("/templates/backend/controller.java")
                        .build())
                .injectionConfig((builder) -> builder.beforeOutputFile((tableInfo, objectMap) ->
                                log.debug("tableInfo - {},objectMap - {}", tableInfo.getEntityName(), objectMap))
                        .customMap(customMap).customFile(customFiles).build())
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
        log.info("{}生成完成:{}", request.getTableName(), rootDir);
        return rootDir;
    }
}
