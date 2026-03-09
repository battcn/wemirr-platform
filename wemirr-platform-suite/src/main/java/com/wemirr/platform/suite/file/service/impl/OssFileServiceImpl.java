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
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.feign.domain.resp.OssFilePreviewResp;
import com.wemirr.platform.suite.file.domain.dto.req.FileStoragePageReq;
import com.wemirr.platform.suite.file.domain.dto.resp.OssFilePageResp;
import com.wemirr.platform.suite.file.domain.entity.OssConfig;
import com.wemirr.platform.suite.file.domain.entity.OssFile;
import com.wemirr.platform.suite.file.domain.enums.MineType;
import com.wemirr.platform.suite.file.event.OssConfigTemplate;
import com.wemirr.platform.suite.file.repository.OssFileMapper;
import com.wemirr.platform.suite.file.service.OssFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.hash.HashInfo;
import org.dromara.x.file.storage.core.platform.FileStorage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author xiao1
 * @since 2024-12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OssFileServiceImpl extends SuperServiceImpl<OssFileMapper, OssFile> implements OssFileService {

    private final FileStorageService fileStorageService;
    private final OssConfigTemplate ossConfigTemplate;

    @Override
    public OssFile upload(MultipartFile file) {
        OssConfig setting = ossConfigTemplate.getDefaultStorageSetting();
        String platform = setting.getPlatform();
        if (fileStorageService.getFileStorage(platform) == null) {
            throw CheckedException.badRequest("未找到对应的存储平台，请检查配置");
        }
        FileInfo info = fileStorageService.of(file).setPlatform(platform).upload();
        OssFile ossFile = toFileInfoRecord(info);
        ossFile.setCategory(MineType.ofName(info.getContentType()));
        ossFile.setPlatform(platform);
        this.baseMapper.insert(ossFile);
        return ossFile;
    }

    @Override
    public OssFile uploadImage(MultipartFile file) {
        FileInfo info = fileStorageService.of(file)
                // .setThumbnailSuffix() //指定缩略图后缀，必须是 thumbnailator 支持的图片格式，默认使用全局的
                // .setSaveThFilename() //指定缩略图的保存文件名，注意此文件名不含后缀，默认自动生成
                // 将图片大小调整到 1000*1000
                .image(img -> img.size(1000, 1000))
                // 再生成一张 200*200 的缩略图
                .thumbnail(th -> th.size(200, 200))
                .upload();
        return toFileInfoRecord(info);
    }

    @Override
    public void delete(Long id) {
        OssFile storage = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("文件不存在"));
        FileStorage fileStorage = fileStorageService.getFileStorage(storage.getPlatform());
        if (fileStorage == null) {
            throw CheckedException.badRequest("未找到对应的存储平台或对应平台未开启");
        }
        FileInfo fileInfo = toFileInfo(storage);
        if (fileStorageService.delete(fileInfo)) {
            this.baseMapper.deleteById(id);
        }
    }

    @Override
    public void rename(Long id, String originName) {
        this.baseMapper.updateById(OssFile.builder().id(id).originalFilename(originName).build());
    }

    @Override
    public IPage<OssFilePageResp> pageList(FileStoragePageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<OssFile>lbQ()
                        .eq(OssFile::getCategory, req.getCategory())
                        .like(OssFile::getOriginalFilename, req.getOriginalFilename())
                        .like(OssFile::getCreateName, req.getCreateName()))
                .convert(x -> BeanUtil.toBean(x, OssFilePageResp.class));
    }

    @Override
    public Map.Entry<String, String> preview(String filePath) {
        return null;
    }

    @Override
    public Collection<String> previewList(Set<String> req) {
        return List.of();
    }

    @Override
    public Map<String, OssFilePreviewResp> previewMap(Set<String> pathList) {
        return Map.of();
    }

    /**
     * 将 FileInfo 转为 FileInfoRecord
     */
    public OssFile toFileInfoRecord(FileInfo info) {
        OssFile detail = BeanUtil.copyProperties(info, OssFile.class, "metadata", "userMetadata", "thMetadata", "thUserMetadata", "attr", "hashInfo");
        if (StrUtil.isBlank(detail.getThFilename())) {
            detail.setThFilename(null);
        }
        detail.setMetadata(JacksonUtils.toJson(info.getMetadata()));
        detail.setUserMetadata(JacksonUtils.toJson(info.getUserMetadata()));
        detail.setThMetadata(JacksonUtils.toJson(info.getThMetadata()));
        detail.setThUserMetadata(JacksonUtils.toJson(info.getThUserMetadata()));
        detail.setAttr(JacksonUtils.toJson(info.getAttr()));
        detail.setHashInfo(JacksonUtils.toJson(info.getHashInfo()));
        return detail;
    }

    /**
     * 将 FileInfoRecord 转为 FileInfo
     */
    public FileInfo toFileInfo(OssFile detail) {
        FileInfo info = BeanUtil.copyProperties(detail, FileInfo.class, "metadata", "userMetadata", "thMetadata", "thUserMetadata", "attr", "hashInfo");
        if (StrUtil.isBlank(info.getThFilename())) {
            info.setThFilename(null);
        }
        // 这里手动获取数据库中的 json 字符串 并转成 元数据，方便使用
        info.setMetadata(jsonToMetadata(detail.getMetadata()));
        info.setUserMetadata(jsonToMetadata(detail.getUserMetadata()));
        info.setThMetadata(jsonToMetadata(detail.getThMetadata()));
        info.setThUserMetadata(jsonToMetadata(detail.getThUserMetadata()));
        // 这里手动获取数据库中的 json 字符串 并转成 附加属性字典，方便使用
        info.setAttr(JacksonUtils.toBean(detail.getAttr(), Dict.class));
        // 这里手动获取数据库中的 json 字符串 并转成 哈希信息，方便使用
        info.setHashInfo(JacksonUtils.toBean(detail.getHashInfo(), HashInfo.class));
        return info;
    }

    /**
     * 将 json 字符串转换成元数据对象
     */
    public Map<String, String> jsonToMetadata(String json) {
        if (StrUtil.isBlank(json)) {
            return null;
        }
        return JacksonUtils.readValue(json.replaceAll("^\"|\"$", ""), new TypeReference<>() {
        });
    }

}
