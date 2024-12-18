package com.wemirr.platform.suite.file.domain.dto.resp;

import lombok.Data;

import java.time.Instant;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
public class FileStoragePageResp {

    private Long id;

    /**
     * 文件访问地址
     */
    private String url;


    /**
     * 文件大小，单位字节
     */
    private String formatSize;

    /**
     * 原始文件名
     */
    private String originalFilename;

    /**
     * 基础存储路径
     */
    private String basePath;

    /**
     * 存储路径
     */
    private String path;

    /**
     * 文件扩展名
     */
    private String ext;
    /**
     * 存储平台
     */
    private String platform;
    /**
     * 文件类型
     */
    private String category;
    /**
     * 上传者
     */
    private String createdName;
    /**
     * 上传时间
     */
    private Instant createdTime;

}
