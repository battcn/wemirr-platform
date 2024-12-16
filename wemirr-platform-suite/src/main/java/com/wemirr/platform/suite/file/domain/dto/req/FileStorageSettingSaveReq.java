package com.wemirr.platform.suite.file.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiao1
 * @date 2024-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "StorageConfigSave", description = "存储配置上传")
public class FileStorageSettingSaveReq {

    private Long id;

    /**
     * 平台名称
     */
    @NotBlank(message = "平台名称不能为空")
    @Schema(description = "存储平台",required = true,example="amazon-s3-1")
    private String platformV;
    /**
     * 是否启用存储 [true:启用;false:禁用]
     * 同个租户下只能开启一个配置
     */
    @Schema(description = "是否开启配置",required = true,example="1")
    private Boolean enableStorage;
    /**
     * AccessKey
     */
    @NotBlank(message = "AccessKey不能为空")
    @Schema(description = "存储平台accessKey",required = true)
    private String accessKey;
    /**
     * SecretKey
     */
    @NotBlank(message = "SecretKey不能为空")
    @Schema(description = "存储平台secretKey",required = true)
    private String secretKey;
    /**
     * 区域
     */
    @Schema(description = "存储平台region",required = false)
    private String region;
    /**
     * 访问域名
     */
    @NotBlank(message ="域名不能为空")
    @Schema(description = "访问域名,需要以/结尾",required = true,example="http://192.168.241.130:9000/test/")
    private String domain;
    /**
     * 桶名称
     */
    @NotBlank(message ="桶名称不能为空")
    @Schema(description = "桶名称",required = true,example="test")
    private String bucketName;
    /**
     * 基础路径
     */
    @NotBlank(message ="基础路径不能为空")
    @Schema(description = "基础路径",required = true,example="test/")
    private String basePath;
    /**
     * 连接地址
     */
    @NotBlank(message ="连接地址不能为空")
    @Schema(description = "连接地址",required = true,example="http://192.168.241.131:9000")
    private String endPoint;
}
