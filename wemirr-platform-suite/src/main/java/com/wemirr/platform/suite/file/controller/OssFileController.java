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

package com.wemirr.platform.suite.file.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.platform.suite.feign.domain.resp.OssFilePreviewResp;
import com.wemirr.platform.suite.file.domain.dto.req.FileStoragePageReq;
import com.wemirr.platform.suite.file.domain.dto.resp.OssFilePageResp;
import com.wemirr.platform.suite.file.domain.entity.OssFile;
import com.wemirr.platform.suite.file.service.OssFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author xiao1
 * @since 2024-12
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/oss-files")
@RequiredArgsConstructor
@Tag(name = "文件存储", description = "文件存储")
public class OssFileController {

    private final OssFileService ossFileService;

    @GetMapping("/token")
    @Parameter(description = "文件名", name = "originName", in = ParameterIn.QUERY)
    @Operation(summary = "上传Token获取 - [Levin] - [DONE]")
    public void getToken(String key, @RequestParam(defaultValue = "true") boolean random) {
        // return Result.ok(storageOperation.token(key, random));
    }

    @GetMapping("/preview")
    @Operation(summary = "文件预览", description = "根据oss文件地址和临时token获取可下载的地址")
    public Map.Entry<String, String> previewUrl(String path) {
        return ossFileService.preview(path);
    }

    @PostMapping("/preview-list")
    @Operation(summary = "批量预览-List")
    public Collection<String> previewList(@RequestBody Set<String> req) {
        return ossFileService.previewList(req);
    }

    @PostMapping("/preview-map")
    @Operation(summary = "批量预览-Map")
    public Map<String, OssFilePreviewResp> previewMap(@RequestBody Set<String> pathList) {
        return ossFileService.previewMap(pathList);
    }


    @PostMapping("/upload")
    @Operation(summary = "上传文件", description = "上传文件")
//    @AccessLog(module = "文件存储", description = "上传文件")
    public OssFile upload(@RequestParam("file") MultipartFile file) {
        return ossFileService.upload(file);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询")
    public IPage<OssFilePageResp> pageList(FileStoragePageReq req) {
        return ossFileService.pageList(req);
    }

    @PostMapping("/{id}")
    @Operation(summary = "删除文件", description = "删除文件")
    @AccessLog(module = "文件存储", description = "删除文件")
    public void delete(@PathVariable Long id) {
        ossFileService.delete(id);
    }

    @PutMapping("/rename/{id}/{originName}")
    @Operation(summary = "文件重命名", description = "文件重命名")
    @AccessLog(module = "文件存储", description = "文件重命名")
    public void rename(@PathVariable Long id, @PathVariable String originName) {
        ossFileService.rename(id, originName);
    }

    @PostMapping("/upload-image")
    @Operation(summary = "上传图片", description = "上传图片")
    public OssFile uploadImage(@RequestParam("file") MultipartFile file) {
        return ossFileService.uploadImage(file);
    }

}
