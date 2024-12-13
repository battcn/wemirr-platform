package com.wemirr.platform.suite.file.event;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xiao1
 * @date 2024-12
 * 存储策略更新事件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RedisStorageConfigEvent {
    /**
     * 租户id
     */
    private  Long tenantId;
    /**
     * 更新类型 1:新增 2：修改 3：删除
     */
    private  Integer updateType;
    /**
     * 是否开启配置
     */

    private Boolean enableStorage;

    /**
     * 配置key
     */
    private  String configKey;

    /**
     * 平台名称
     */
    private String platform;
    /**
     * 旧平台名称[更新时用]
     */
    private String oldPlatform;
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
