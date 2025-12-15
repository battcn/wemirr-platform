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

package com.wemirr.platform.suite.file.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.suite.file.domain.dto.req.FileStorageSettingPageReq;
import com.wemirr.platform.suite.file.domain.dto.req.OssConfigSaveReq;
import com.wemirr.platform.suite.file.domain.dto.resp.OssConfigPageResp;
import com.wemirr.platform.suite.file.domain.entity.OssConfig;

/**
 * OSS 存储配置服务
 * <p>
 * 管理对象存储服务配置，支持多种存储后端
 *
 * @author Levin
 * @since 2024-12
 */
public interface OssConfigService extends SuperService<OssConfig> {

    /**
     * 分页查询存储配置
     *
     * @param req 查询条件
     * @return 分页结果
     */
    IPage<OssConfigPageResp> pageList(FileStorageSettingPageReq req);

    /**
     * 创建存储配置
     *
     * @param req 配置信息
     */
    void create(OssConfigSaveReq req);

    /**
     * 修改存储配置
     *
     * @param id  配置 ID
     * @param req 配置信息
     */
    void modify(Long id, OssConfigSaveReq req);

    /**
     * 删除存储配置
     *
     * @param id 配置 ID
     */
    void delete(Long id);
}
