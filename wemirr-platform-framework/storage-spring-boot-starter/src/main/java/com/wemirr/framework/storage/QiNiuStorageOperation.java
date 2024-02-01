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
package com.wemirr.framework.storage;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.URLUtil;
import com.google.common.collect.Maps;
import com.qiniu.cdn.CdnManager;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.DownloadUrl;
import com.qiniu.storage.UploadManager;
import com.wemirr.framework.storage.cloud.qiniu.QiNiuScope;
import com.wemirr.framework.storage.cloud.qiniu.connection.QiNiuConnectionFactory;
import com.wemirr.framework.storage.domain.DownloadResponse;
import com.wemirr.framework.storage.domain.StorageItem;
import com.wemirr.framework.storage.domain.StorageRequest;
import com.wemirr.framework.storage.domain.StorageResponse;
import com.wemirr.framework.storage.properties.BaseStorageProperties;
import com.wemirr.framework.storage.properties.QiNiuStorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Levin
 */
@Slf4j
public class QiNiuStorageOperation implements StorageOperation {
    
    private final UploadManager uploadManager;
    private final BucketManager bucketManager;
    private final CdnManager cdnManager;
    private final QiNiuStorageProperties properties;
    private final QiNiuConnectionFactory connectionFactory;
    
    @Autowired
    public QiNiuStorageOperation(QiNiuStorageProperties properties, QiNiuConnectionFactory connectionFactory) {
        this.properties = properties;
        this.connectionFactory = connectionFactory;
        this.uploadManager = this.connectionFactory.getUploadManager();
        this.bucketManager = this.connectionFactory.getBucketManager();
        this.cdnManager = this.connectionFactory.getCdnManager();
    }
    
    @Override
    public String token(String originName, boolean random) {
        return token(properties.getBucket(), originName, random);
    }
    
    @Override
    public String token(String bucket, String originName, boolean random) {
        String targetName = null;
        if (random && StringUtils.isNoneBlank(originName)) {
            targetName = getTargetName(StorageRequest.builder().originName(originName).build());
        }
        return getUploadToken(StringUtils.defaultIfBlank(bucket, properties.getBucket()), targetName);
    }
    
    @Override
    public DownloadResponse download(String fileName) {
        String domainOfBucket = this.connectionFactory.getDomain(properties.getBucket());
        final String path = StringUtils.defaultIfBlank(this.properties.getTmpDir(), this.getClass().getResource("/").getPath());
        final File file = new File(path + File.separator + fileName);
        log.debug("[文件目录] - [{}]", file.getPath());
        try {
            DownloadUrl url = new DownloadUrl(domainOfBucket, true, fileName);
            String urlString = url.buildURL();
            log.debug(urlString);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public DownloadResponse download(String bucketName, String fileName) {
        return null;
    }
    
    @Override
    public void download(String bucketName, String fileName, File file) {
        String domainOfBucket = this.connectionFactory.getDomain(bucketName);
        log.debug("[文件目录] - [{}]", file.getPath());
        try {
            DownloadUrl url = new DownloadUrl(domainOfBucket, true, fileName);
            FileUtil.writeFromStream(URLUtil.getStream(URLUtil.url(url.buildURL())), file);
        } catch (QiniuException e) {
            log.error("七牛云下载失败", e);
            throw downloadError(BaseStorageProperties.StorageType.QINIU, e);
        }
    }
    
    @Override
    public void download(String fileName, File file) {
        download(properties.getBucket(), fileName, file);
    }
    
    @Override
    public List<StorageItem> list() {
        return null;
    }
    
    @Override
    public void rename(String oldName, String newName) {
        
    }
    
    @Override
    public void rename(String bucketName, String oldName, String newName) {
        
    }
    
    @Override
    public StorageResponse upload(String fileName, byte[] content) {
        return upload(properties.getBucket(), fileName, content);
    }
    
    @Override
    public StorageResponse upload(String bucketName, String fileName, InputStream content) {
        try {
            String upToken = getUploadToken(bucketName, fileName);
            Response response = uploadManager.put(content, fileName, upToken, null, null);
            return getStorageResponse(fileName, response);
        } catch (QiniuException e) {
            log.error("[文件上传异常]", e);
            throw uploadError(BaseStorageProperties.StorageType.QINIU, e);
        }
    }
    
    @Override
    public StorageResponse upload(String bucketName, String fileName, byte[] content) {
        try {
            String upToken = getUploadToken(bucketName, fileName);
            Response response = uploadManager.put(content, fileName, upToken);
            return getStorageResponse(fileName, response);
        } catch (QiniuException e) {
            log.error("[文件上传异常]", e);
            throw uploadError(BaseStorageProperties.StorageType.QINIU, e);
        }
    }
    
    @Override
    public StorageResponse upload(StorageRequest request) {
        if (request.getInputStream() == null && request.getContent() == null) {
            throw uploadError(BaseStorageProperties.StorageType.QINIU, "文件上传失败,InputStream 与 Content 不能全部为空");
        }
        try {
            final String bucket = StringUtils.defaultIfBlank(request.getBucket(), properties.getBucket());
            final String targetName = getTargetName(request);
            String upToken = getUploadToken(bucket, targetName);
            Response response;
            if (Objects.nonNull(request.getInputStream())) {
                response = uploadManager.put(request.getInputStream(), targetName, upToken, null, null);
            } else {
                response = uploadManager.put(request.getContent(), targetName, upToken);
            }
            log.debug("七牛上传响应结果 - {}", response);
            if (!response.isOK()) {
                throw uploadError(BaseStorageProperties.StorageType.QINIU, response.error);
            }
            Map<String, Object> extend = Maps.newLinkedHashMap();
            extend.put("reqId", response.reqId);
            return StorageResponse.builder().originName(request.getOriginName())
                    .targetName(targetName).size(response.body().length)
                    .mappingPath(properties.getMappingPath())
                    .bucket(bucket).extend(extend).build();
        } catch (QiniuException e) {
            log.error("[文件上传异常]", e);
            throw uploadError(BaseStorageProperties.StorageType.QINIU, e);
        }
    }
    
    private StorageResponse getStorageResponse(String fileName, Response response) throws QiniuException {
        log.debug("七牛上传响应结果 - {}", response);
        if (!response.isOK()) {
            throw uploadError(BaseStorageProperties.StorageType.QINIU, response.error);
        }
        Map<String, Object> extend = Maps.newLinkedHashMap();
        extend.put("reqId", response.reqId);
        return StorageResponse.builder().originName(fileName).targetName(fileName).size(response.body().length)
                .extend(extend).fullUrl(properties.getMappingPath() + fileName).build();
    }
    
    @Override
    public void remove(String fileName) {
        remove(properties.getBucket(), fileName);
    }
    
    @Override
    public void remove(String bucketName, String fileName) {
        try {
            final Response response = bucketManager.delete(bucketName, fileName);
            log.debug("文件删除成功 - {}", response);
        } catch (QiniuException e) {
            log.error("[文件移除异常]", e);
        }
    }
    
    @Override
    public void remove(String bucketName, Path path) {
        remove(bucketName, path.toString());
    }
    
    private String getUploadToken(String bucket, String key) {
        return this.connectionFactory.getUploadToken(bucket, key, QiNiuScope.DEFAULT);
    }
}
