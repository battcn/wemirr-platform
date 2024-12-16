package com.wemirr.platform.suite.file.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
@Schema(name = "FileStorageSettingSaveReq", description = "存储配置上传")
public class FileStorageSettingSaveReq {

    @Schema(description = "存储类型")
    @NotBlank(message = "存储类型不能为空")
    private String type;
    /**
     * 是否启用存储 [true:启用;false:禁用]
     * 同个租户下只能开启一个配置
     */
    @Schema(description = "是否开启配置", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Boolean status;
    /**
     * AccessKey
     */
    @NotBlank(message = "AccessKey 不能为空")
    @Schema(description = "存储平台accessKey", requiredMode = Schema.RequiredMode.REQUIRED)
    private String accessKey;
    /**
     * SecretKey
     */
    @NotBlank(message = "SecretKey 不能为空")
    @Schema(description = "存储平台secretKey", requiredMode = Schema.RequiredMode.REQUIRED)
    private String secretKey;
    /**
     * 区域
     */
    @Schema(description = "存储平台region")
    private String region;
    /**
     * 访问域名
     */
    @NotBlank(message = "域名不能为空")
    @Schema(description = "访问域名,需要以/结尾", requiredMode = Schema.RequiredMode.REQUIRED, example = "http://192.168.241.130:9000/test/")
    private String domain;
    /**
     * 桶名称
     */
    @NotBlank(message = "桶名称不能为空")
    @Schema(description = "桶名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "test")
    private String bucketName;
    /**
     * 基础路径
     */
    @NotBlank(message = "基础路径不能为空")
    @Schema(description = "基础路径", requiredMode = Schema.RequiredMode.REQUIRED, example = "test/")
    private String basePath;
    /**
     * 连接地址
     */
    @NotBlank(message = "连接地址不能为空")
    @Schema(description = "连接地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "http://192.168.241.131:9000")
    private String endPoint;
}
