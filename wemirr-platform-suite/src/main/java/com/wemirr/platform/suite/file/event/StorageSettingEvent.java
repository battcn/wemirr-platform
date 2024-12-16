package com.wemirr.platform.suite.file.event;

import lombok.Data;

/**
 * 存储策略更新事件
 *
 * @author xiao1
 * @since 2024-12
 */
@Data
public class StorageSettingEvent {

    /**
     * 租户id
     */
    private Long tenantId;

    private Boolean status;
    /**
     * 更新类型 1:新增 2：修改 3：删除
     */
    private Integer updateType;

    /**
     * 平台名称
     */
    private String platform;
    /**
     * 访问key
     */
    private String accessKey;
    /**
     * 密钥
     */
    private String secretKey;
    /**
     * 区域
     */
    private String region;
    /**
     * 访问域名
     */
    private String domain;
    /**
     * 桶名称
     */
    private String bucketName;
    /**
     * 基础路径
     */
    private String basePath;
    /**
     * 连接地址
     */
    private String endPoint;
}
