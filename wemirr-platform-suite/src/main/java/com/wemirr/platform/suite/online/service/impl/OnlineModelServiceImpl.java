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

package com.wemirr.platform.suite.online.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.online.dialect.EpicDesignerField;
import com.wemirr.platform.suite.online.dialect.FastCrudDialect;
import com.wemirr.platform.suite.online.domain.entity.OnlineModel;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDesignSaveReq;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDesignerPageReq;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDesignerSaveReq;
import com.wemirr.platform.suite.online.domain.resp.OnlineFormDesignerDetailResp;
import com.wemirr.platform.suite.online.domain.resp.OnlineFormDesignerPageResp;
import com.wemirr.platform.suite.online.repository.OnlineModelMapper;
import com.wemirr.platform.suite.online.service.OnlineModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OnlineModelServiceImpl extends SuperServiceImpl<OnlineModelMapper, OnlineModel> implements OnlineModelService {

    @Override
    public IPage<OnlineFormDesignerPageResp> pageList(OnlineFormDesignerPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<OnlineModel>lbQ()
                        .eq(OnlineModel::getDefinitionKey, req.getDefinitionKey())
                        .eq(OnlineModel::getTitle, req.getTitle()))
                .convert(x -> BeanUtilPlus.toBean(x, OnlineFormDesignerPageResp.class));
    }

    @Override
    public void create(OnlineFormDesignerSaveReq req) {
        var bean = BeanUtilPlus.toBean(req, OnlineModel.class);
        this.baseMapper.insert(bean);

    }

    @Override
    public void modify(Long id, OnlineFormDesignerSaveReq req) {
        var bean = BeanUtilPlus.toBean(id, req, OnlineModel.class);
        this.baseMapper.updateById(bean);
    }

    @Override
    public OnlineFormDesignerDetailResp detail(Long id) {
        var entity = this.baseMapper.selectById(id);
        var bean = BeanUtilPlus.toBean(entity, OnlineFormDesignerDetailResp.class);
        if (StrUtil.isNotBlank(entity.getFormSchemas())) {
            bean.setFormSchemas(JacksonUtils.readValue(entity.getFormSchemas(), new TypeReference<>() {
            }));
        }
        return bean;
    }

    @Override
    public OnlineFormDesignerDetailResp detail(String definitionKey) {
        var entity = Optional.ofNullable(this.baseMapper.selectOne(OnlineModel::getDefinitionKey, definitionKey))
                .orElseThrow(() -> CheckedException.notFound("模型不存在"));
        var bean = BeanUtilPlus.toBean(entity, OnlineFormDesignerDetailResp.class);
        if (StrUtil.isNotBlank(entity.getFormSchemas())) {
            bean.setFormSchemas(JacksonUtils.readValue(entity.getFormSchemas(), new TypeReference<>() {
            }));
        }
        return bean;
    }

    @Override
    public void formDesign(Long id, OnlineFormDesignSaveReq req) {
        OnlineModel model = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("模型不存在"));
        if (model.getStatus() != null && !model.getStatus()) {
            throw CheckedException.badRequest("模型已禁用");
        }
        Map<String, Object> fastCrud = FastCrudDialect.toFastCrud(JacksonUtils.readValue(JacksonUtils.toJson(req.getSchemas()), new TypeReference<List<EpicDesignerField>>() {
        }));
        if (fastCrud == null) {
            throw CheckedException.notFound("未解析出 CRUD 模板");
        }
        this.baseMapper.updateById(OnlineModel.builder().id(id)
                .formScript(req.getScript())
                .formSchemas(JacksonUtils.toJson(req.getSchemas()))
                .formCrudConfig(JacksonUtils.toJson(fastCrud))
                .build());
    }
}
