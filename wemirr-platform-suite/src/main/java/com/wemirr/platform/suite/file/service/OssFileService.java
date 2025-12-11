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
import com.wemirr.platform.suite.feign.domain.resp.OssFilePreviewResp;
import com.wemirr.platform.suite.file.domain.dto.req.FileStoragePageReq;
import com.wemirr.platform.suite.file.domain.dto.resp.OssFilePageResp;
import com.wemirr.platform.suite.file.domain.entity.OssFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author xiao1
 * @since 2024-12
 */
public interface OssFileService extends SuperService<OssFile> {

    /**
     * 文件上传
     *
     * @param file file
     * @return FileStorage
     */
    OssFile upload(MultipartFile file);

    /**
     * 图片上传
     *
     * @param file 文件
     * @return 上传结果
     */
    OssFile uploadImage(MultipartFile file);

    /**
     * 文件删除
     */
    void delete(Long id);

    void rename(Long id, String originName);

    IPage<OssFilePageResp> pageList(FileStoragePageReq req);


    /**
     * 根据阿里云oss的url生成一个可以访问的url(通过临时token)
     *
     * @param filePath 路径
     * @return 预览地址
     */
    Map.Entry<String, String> preview(String filePath);


    /**
     * 批量获取预下载的 url
     * @param req req
     * @return 预览结果
     */
    Collection<String> previewList(Set<String> req);

    /**
     * 批量获取预下载的url ,返回结果为一个map
     * key为fileUrl,value为对应的预下载url
     * @param pathList pathList
     * @return 预览结果
     */
    Map<String, OssFilePreviewResp> previewMap(Set<String> pathList);

}
