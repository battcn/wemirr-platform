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

package com.wemirr.platform.suite.file.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.file.domain.dto.req.FileStorageSettingPageReq;
import com.wemirr.platform.suite.file.domain.dto.req.OssConfigSaveReq;
import com.wemirr.platform.suite.file.domain.dto.resp.OssConfigPageResp;
import com.wemirr.platform.suite.file.domain.entity.OssConfig;
import com.wemirr.platform.suite.file.event.OssConfigTemplate;
import com.wemirr.platform.suite.file.repository.OssConfigMapper;
import com.wemirr.platform.suite.file.service.OssConfigService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author xiao1
 * @since 2024-12
 */
@Slf4j
@Service
@AllArgsConstructor
public class OssConfigServiceImpl extends SuperServiceImpl<OssConfigMapper, OssConfig> implements OssConfigService {

    private final AuthenticationContext context;
    private final OssConfigTemplate ossConfigTemplate;

    @Override
    public void create(OssConfigSaveReq req) {
        // 获取当前租户ID
        Long tenantId = context.tenantId();
        // 检查平台名称是否已存在
        Long count = this.baseMapper.selectCount(Wraps.<OssConfig>lbQ().eq(OssConfig::getType, req.getType())
                .eq(OssConfig::getBucketName, req.getBucketName()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("令牌桶已存在");
        }
        // 复制属性并设置租户ID与平台名称
        OssConfig setting = BeanUtil.toBean(req, OssConfig.class);
        setting.setPlatform(StrUtil.join("-", req.getType(), req.getBucketName()));
        // 保存存储配置
        this.baseMapper.insert(setting);
        // 更新存储配置状态
        if (setting.getStatus()) {
            this.baseMapper.update(OssConfig.builder().status(false).build(), Wraps.<OssConfig>lbQ()
                    .ne(OssConfig::getId, setting.getId()).eq(OssConfig::getTenantId, tenantId));
        }
        this.ossConfigTemplate.publish(setting, 1);
    }

    @Override
    public void delete(Long id) {
        OssConfig setting = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("配置不存在"));
        if (setting.getStatus()) {
            throw CheckedException.badRequest("该平台名称已启用，无法删除");
        } else {
            this.removeById(id);
        }
        this.ossConfigTemplate.publish(setting, 3);
    }

    @Override
    public void modify(Long id, OssConfigSaveReq req) {
        Optional.ofNullable(baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("配置不存在"));
        // 获取当前租户ID
        Long tenantId = context.tenantId();
        // 检查平台名称是否已存在
        Long count = this.baseMapper.selectCount(Wraps.<OssConfig>lbQ().ne(OssConfig::getId, id)
                .eq(OssConfig::getType, req.getType()).eq(OssConfig::getBucketName, req.getBucketName()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("令牌桶已存在");
        }
        OssConfig bean = BeanUtilPlus.toBean(id, req, OssConfig.class);
        bean.setAccessKey(null);
        bean.setSecretKey(null);
        bean.setTenantId(tenantId);
        bean.setPlatform(StrUtil.join("-", req.getType(), req.getBucketName()));
        // 更新存储配置状态
        if (req.getStatus()) {
            baseMapper.update(OssConfig.builder().status(false).build(), Wraps.<OssConfig>lbQ()
                    .ne(OssConfig::getId, id).eq(OssConfig::getTenantId, tenantId));
        }
        // 保存存储配置
        this.baseMapper.updateById(bean);
        this.ossConfigTemplate.publish(bean, 2);
    }

    @Override
    public IPage<OssConfigPageResp> pageList(FileStorageSettingPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<OssConfig>lbQ()
                        .eq(OssConfig::getStatus, req.getStatus())
                        .eq(OssConfig::getType, req.getType()))
                .convert(x -> BeanUtil.toBean(x, OssConfigPageResp.class));
    }
}
