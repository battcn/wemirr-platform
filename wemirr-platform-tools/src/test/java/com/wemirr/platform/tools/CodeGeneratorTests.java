/*
 * Copyright (c) 2023 WEMIRR-PLATFORM Authors. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wemirr.platform.tools;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.tools.domain.entity.GenerateEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Slf4j
class CodeGeneratorTests {
    
    @Test
    void sys() {
        final GenerateEntity request = new GenerateEntity();
        request.setAuthor("Levin");
        request.setTableName("t_user");
        request.setTablePrefix("t_");
        request.setParentPackage("com.wemirr.platform.tools.gen");
        generate(request);
    }
    
    public String generate(GenerateEntity request) {
        Map<String, Object> customMap = Maps.newHashMap();
        customMap.put("apiUrlPrefix", request.getApiUrlPrefix());
        customMap.put("platformId", request.getPlatformId());
        customMap.put("now", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        
        Map<String, String> customFiles = Maps.newHashMap();
        String rootDir = "/Users/battcn/Development/opensource/wemirr-platform/wemirr-platform-tools/src/main/java/";
        FastAutoGenerator
                .create("jdbc:mysql://localhost:3306/wemirr-platform?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true",
                        "root", "123456")
                .globalConfig(builder -> builder.author(request.getAuthor()).fileOverride().outputDir(rootDir))
                .packageConfig(builder -> builder.parent(request.getParentPackage()).moduleName(request.getModuleName())
                        .entity("domain").mapper("mapper").xml("mapper.xml")
                        .service("service").serviceImpl("service.impl").controller("controller")
                        // 设置mapperXml生成路径
                        .pathInfo(Collections.singletonMap(OutputFile.xml, rootDir)))
                .strategyConfig(builder -> builder.enableCapitalMode()
                        .enableSkipView().disableSqlFilter()
                        .addInclude(request.getTableName())
                        .addTablePrefix(request.getTablePrefix())
                        .entityBuilder()
                        .naming(NamingStrategy.underline_to_camel).columnNaming(NamingStrategy.underline_to_camel)
                        .logicDeleteColumnName(request.getLogicDeleteField())
                        .enableLombok().superClass(SuperEntity.class)
                        .mapperBuilder().superClass(SuperMapper.class)
                        .serviceBuilder().superServiceClass(SuperService.class)
                        .convertServiceFileName(entityName -> entityName + ConstVal.SERVICE)
                        .superServiceImplClass(SuperServiceImpl.class)
                        .controllerBuilder().enableRestStyle())
                .templateConfig(builder -> builder
                        // .entity("/templates/backend/entity.java")
                        .service("/templates/backend/service.java")
                        .serviceImpl("/templates/backend/serviceImpl.java")
                        .mapper("/templates/backend/mapper.java")
                        .xml("/templates/backend/mapper.xml")
                        .controller("/templates/backend/controller.java")
                        .build())
                .injectionConfig(builder -> builder.beforeOutputFile((tableInfo, objectMap) -> log.debug("tableInfo - {},objectMap - {}", tableInfo.getEntityName(), objectMap))
                        .customMap(customMap).customFile(customFiles).build())
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
        log.info("{}生成完成:{}", request.getTableName(), rootDir);
        return rootDir;
    }
}
