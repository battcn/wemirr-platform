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

package com.wemirr.platform.suite.file.event;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.utils.InterceptorIgnoreUtils;
import com.wemirr.platform.suite.file.domain.constants.StorageConstants;
import com.wemirr.platform.suite.file.domain.entity.FileStorageSetting;
import com.wemirr.platform.suite.file.repository.FileStorageSettingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xiao1
 * @since 2024-12
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StorageSettingTemplate implements ApplicationRunner {

    private final AuthenticationContext context;
    private final FileStorageSettingMapper fileStorageSettingMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void run(ApplicationArguments args) {
        log.info("==================== 存储设置初始化-Begin ====================");
        List<FileStorageSetting> storageSettingList = InterceptorIgnoreUtils.withIgnoreStrategy(() -> fileStorageSettingMapper.selectList(FileStorageSetting::getStatus, true));
        if (CollUtil.isEmpty(storageSettingList)) {
            return;
        }
        for (FileStorageSetting setting : storageSettingList) {
            publish(setting, 1);
        }
        log.info("==================== 存储设置初始化-End ====================");
    }

    public void publish(FileStorageSetting setting, int eventType) {
        log.info("redis publish - {},type -> {}", setting, eventType);
        // 构建后台存储配置的平台名称（租户ID + 平台名称）
        Long tenantId = setting.getTenantId();
        String platform = setting.getPlatform();
        // 构建并发布存储配置更新事件
        StorageSettingEvent event = new StorageSettingEvent();
        event.setPlatform(platform);
        event.setTenantId(tenantId);
        event.setUpdateType(eventType);
        event.setBucketName(setting.getBucketName());
        event.setAccessKey(setting.getAccessKey());
        event.setSecretKey(setting.getSecretKey());
        event.setRegion(setting.getRegion());
        event.setBasePath(setting.getBasePath());
        event.setDomain(setting.getDomain());
        event.setEndPoint(setting.getEndPoint());
        if (setting.getStatus()) {
            redisTemplate.opsForHash().put(StorageConstants.STORAGE_SETTING_DEFAULT_SETTING, tenantId.toString(), JSONObject.toJSONString(setting));
        }
        redisTemplate.convertAndSend(StorageConstants.STORAGE_CONFIG_EVENT_TOPIC, event);
        SpringUtil.publishEvent(event);
    }

    public FileStorageSetting getDefaultStorageSetting() {
        String json = (String) redisTemplate.opsForHash().get(StorageConstants.STORAGE_SETTING_DEFAULT_SETTING, context.tenantId().toString());
        if (StrUtil.isBlank(json)) {
            FileStorageSetting setting = this.fileStorageSettingMapper.selectOne(Wraps.<FileStorageSetting>lbQ().eq(FileStorageSetting::getStatus, true)
                    .eq(FileStorageSetting::getTenantId, context.tenantId()));
            redisTemplate.opsForHash().put(StorageConstants.STORAGE_SETTING_DEFAULT_SETTING, context.tenantId().toString(), JSONObject.toJSONString(setting));
            return setting;
        }
        return JSON.parseObject(json, FileStorageSetting.class);
    }
}
