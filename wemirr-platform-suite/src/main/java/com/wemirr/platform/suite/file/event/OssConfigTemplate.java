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
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.utils.TenantHelper;
import com.wemirr.platform.suite.file.domain.constants.StorageConstants;
import com.wemirr.platform.suite.file.domain.entity.OssConfig;
import com.wemirr.platform.suite.file.repository.OssConfigMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileStorageProperties;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.FileStorageServiceBuilder;
import org.dromara.x.file.storage.core.platform.FileStorage;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.wemirr.platform.suite.file.domain.constants.StorageConstants.STORAGE_SETTING_DEFAULT_SETTING;

/**
 * @author xiao1
 * @since 2024-12
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OssConfigTemplate implements ApplicationRunner {

    private final AuthenticationContext context;
    private final OssConfigMapper ossConfigMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final FileStorageService fileStorageService;

    @Override
    public void run(ApplicationArguments args) {
        log.info("==================== 存储设置初始化-Begin ====================");
        List<OssConfig> storageSettingList = TenantHelper.withIgnoreStrategy(() -> ossConfigMapper.selectList(OssConfig::getStatus, true));
        if (CollUtil.isEmpty(storageSettingList)) {
            return;
        }
        for (OssConfig setting : storageSettingList) {
            publish(setting, 1);
        }
        CopyOnWriteArrayList<FileStorage> fileStorageList = fileStorageService.getFileStorageList();
        List<FileStorageProperties.AmazonS3Config> amazonS3ConfigList = new ArrayList<>();
        for (OssConfig setting : storageSettingList) {
            FileStorageProperties.AmazonS3Config s3Config = new FileStorageProperties.AmazonS3Config();
            String platform = setting.getPlatform();
            s3Config.setPlatform(platform);
            s3Config.setAccessKey(setting.getAccessKey());
            s3Config.setSecretKey(setting.getSecretKey());
            s3Config.setRegion(setting.getRegion());
            s3Config.setEndPoint(setting.getEndPoint());
            s3Config.setBucketName(setting.getBucketName());
            s3Config.setDomain(setting.getDomain());
            s3Config.setBasePath(setting.getBasePath());
            amazonS3ConfigList.add(s3Config);
            redisTemplate.opsForHash().put(STORAGE_SETTING_DEFAULT_SETTING, setting.getTenantId().toString(), JacksonUtils.toJson(setting));
        }
        fileStorageList.addAll(FileStorageServiceBuilder.buildAmazonS3FileStorage(amazonS3ConfigList, null));
        log.info("==================== 存储设置初始化-End ====================");
    }

    public void publish(OssConfig setting, int eventType) {
        log.info("redis publish - {},type -> {}", setting, eventType);
        // 构建后台存储配置的平台名称（租户ID + 平台名称）
        Long tenantId = setting.getTenantId();
        String platform = setting.getPlatform();
        // 构建并发布存储配置更新事件
        OssConfigEvent event = new OssConfigEvent();
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
            redisTemplate.opsForHash().put(StorageConstants.STORAGE_SETTING_DEFAULT_SETTING, tenantId.toString(), JacksonUtils.toJson(setting));
        }
        redisTemplate.convertAndSend(StorageConstants.STORAGE_CONFIG_EVENT_TOPIC, event);
        SpringUtil.publishEvent(event);
    }

    public OssConfig getDefaultStorageSetting() {
        String json = (String) redisTemplate.opsForHash().get(StorageConstants.STORAGE_SETTING_DEFAULT_SETTING, context.tenantId().toString());
        if (StrUtil.isBlank(json)) {
            OssConfig setting = this.ossConfigMapper.selectOne(Wraps.<OssConfig>lbQ().eq(OssConfig::getStatus, true)
                    .eq(OssConfig::getTenantId, context.tenantId()));
            redisTemplate.opsForHash().put(StorageConstants.STORAGE_SETTING_DEFAULT_SETTING, context.tenantId().toString(), JacksonUtils.toJson(setting));
            return setting;
        }
        return JacksonUtils.toBean(json, OssConfig.class);
    }
}
