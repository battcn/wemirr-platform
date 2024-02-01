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
package com.wemirr.platform.tools.service.impl;

import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.OS;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.wemirr.framework.boot.RegionUtils;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.storage.StorageOperation;
import com.wemirr.framework.storage.domain.StorageRequest;
import com.wemirr.framework.storage.domain.StorageResponse;
import com.wemirr.platform.tools.domain.entity.FileEntity;
import com.wemirr.platform.tools.repository.FileMapper;
import com.wemirr.platform.tools.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.http.protocol.HTTP.USER_AGENT;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class FileServiceImpl extends SuperServiceImpl<FileMapper, FileEntity> implements FileService {
    
    private final StorageOperation storageOperation;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public StorageResponse upload(StorageRequest storage, HttpServletRequest request) {
        String ua = request.getHeader(USER_AGENT);
        String ip = JakartaServletUtil.getClientIP(request);
        String location = RegionUtils.getRegion(ip);
        final UserAgent userAgent = UserAgentUtil.parse(ua);
        final OS os = userAgent.getOs();
        final StorageResponse response = storageOperation.upload(storage);
        final FileEntity entity = FileEntity.builder().contentType(storage.getContentType()).location(location).ip(ip).engine(userAgent.getEngineVersion())
                .os(os.getName()).engineVersion(userAgent.getEngine().getName()).bucket(response.getBucket())
                .size(response.getSize())
                .originName(response.getOriginName()).targetName(response.getTargetName())
                .mappingPath(response.getMappingPath()).fullUrl(response.getFullUrl()).build();
        this.baseMapper.insert(entity);
        response.setFileId(entity.getId());
        return response;
    }
    
}
