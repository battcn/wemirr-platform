package com.wemirr.platform.suite.file.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 文件存储配置
 *
 * @author xiao1
 * @since 2024-12
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("t_file_storage_setting")
public class FileStorageSetting extends SuperEntity<Long> {

    @Schema(description = "存储类型")
    private String type;

    @Schema(description = "自动生成;由 type 与 bucket 组成")
    private String platform;

    /**
     * 是否启用存储 [true:启用;false:禁用]
     * 同个租户下只能开启一个配置
     */
    private Boolean status;
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
    @Schema(description = "访问域名")
    private String domain;

    @Schema(description = "存储桶名称")
    private String bucketName;
    /**
     * 基础路径
     */
    private String basePath;
    /**
     * 连接地址
     */
    private String endPoint;

    @Schema(description = "租户ID")
    private Long tenantId;

}
