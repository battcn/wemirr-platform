package com.wemirr.platform.tools.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.StringUtils;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.framework.db.mybatis.SuperMapper;
import com.wemirr.framework.db.mybatis.SuperService;
import com.wemirr.framework.db.mybatis.SuperServiceImpl;
import com.wemirr.platform.tools.domain.entity.GenerateEntity;
import com.wemirr.platform.tools.mapper.GenerateMapper;
import com.wemirr.platform.tools.service.GenerateService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GenerateServiceImpl extends SuperServiceImpl<GenerateMapper, GenerateEntity> implements GenerateService {

    private final DataSource dataSource;

    @SneakyThrows
    @Override
    @DSTransactional
    public String generate(GenerateEntity request) {
        Map<String, Object> customMap = Maps.newHashMap();
        customMap.put("apiUrlPrefix", request.getApiUrlPrefix());
        customMap.put("platformId", request.getPlatformId());
        customMap.put("now", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

        Map<String, String> customFiles = Maps.newHashMap();
        // todo 还得重新写支持 vue 3.0 的
//        customFiles.put("/templates/front/crud.js.ftl", "/crud.js");
//        customFiles.put("/templates/front/index.vue.ftl", "/index.vue");
//        customFiles.put("/templates/front/api.js.ftl", "/api.js");
//        customFiles.put("/templates/sql/resource.sql.ftl", "_menu.sql");
        final String peek = DynamicDataSourceContextHolder.peek();
        final DataSource ds = ((DynamicRoutingDataSource) dataSource).getDataSource(peek);
        final String rootDir = StringUtils.defaultString(request.getRootDir(), System.getProperty("user.dir") + "/.generated/");
        FastAutoGenerator.create(new DataSourceConfig.Builder(ds))
                .globalConfig(builder -> builder.author(request.getAuthor()).fileOverride().outputDir(rootDir))
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
                        .mapperBuilder().superClass(SuperMapper.class)
                        .serviceBuilder().superServiceClass(SuperService.class)
                        .convertServiceFileName((entityName -> entityName + ConstVal.SERVICE))
                        .superServiceImplClass(SuperServiceImpl.class)
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
