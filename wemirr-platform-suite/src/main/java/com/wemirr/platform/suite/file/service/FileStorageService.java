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
import com.wemirr.platform.suite.file.domain.dto.req.FileStoragePageReq;
import com.wemirr.platform.suite.file.domain.dto.resp.FileStoragePageResp;
import com.wemirr.platform.suite.file.domain.entity.FileStorage;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xiao1
 * @since 2024-12
 */
public interface FileStorageService extends SuperService<FileStorage> {
    
    /**
     * 文件上传
     *
     * @param file file
     * @return FileStorage
     */
    FileStorage upload(MultipartFile file);
    
    /**
     * 图片上传
     *
     * @param file 文件
     * @return 上传结果
     */
    FileStorage uploadImage(MultipartFile file);
    
    /**
     * 文件删除
     */
    void delete(Long id);
    
    void rename(Long id, String originName);
    
    IPage<FileStoragePageResp> pageList(FileStoragePageReq req);
    
}
