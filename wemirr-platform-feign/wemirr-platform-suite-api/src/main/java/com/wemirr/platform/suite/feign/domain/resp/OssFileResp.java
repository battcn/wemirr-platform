package com.wemirr.platform.suite.feign.domain.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xJh
 * @since 2025/11/6
 **/
@Data
public class OssFileResp {

    @Schema(description = "文件访问地址")
    private String url;

    @Schema(description = "文件大小，单位字节")
    private Long size;

    @Schema(description = "格式化后的文件大小")
    private String formatSize;

    @Schema(description = "文件名称")
    private String filename;

    @Schema(description = "原始文件名")
    private String originalFilename;



    /**
     * 存储路径
     */
    private String path;

    /**
     * 文件扩展名
     */
    @TableField(value = "ext")
    private String ext;

    /**
     * MIME类型
     */
    @TableField(value = "content_type")
    private String contentType;

    /**
     * 存储平台
     */
    @TableField(value = "platform")
    private String platform;



    @Schema(description = "租户ID")
    private Long tenantId;

}
