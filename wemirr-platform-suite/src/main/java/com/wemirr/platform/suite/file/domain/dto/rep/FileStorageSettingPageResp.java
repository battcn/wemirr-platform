package com.wemirr.platform.suite.file.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
public class FileStorageSettingPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "存储类型")
    private String type;

    @Schema(description = "是否开启配置")
    private Boolean status;

    @Schema(description = "存储平台accessKey")
    private String accessKey;

    @Schema(description = "存储平台secretKey")
    private String secretKey;

    @Schema(description = "访问域名,需要以/结尾")
    private String domain;

    @Schema(description = "桶名称")
    private String bucketName;

    @Schema(description = "基础路径")
    private String basePath;

    @Schema(description = "连接地址")
    private String endPoint;

    @Schema(description = "创建时间")
    private Instant createdTime;
}
