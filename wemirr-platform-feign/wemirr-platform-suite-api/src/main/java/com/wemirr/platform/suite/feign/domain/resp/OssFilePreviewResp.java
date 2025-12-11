package com.wemirr.platform.suite.feign.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OssFilePreviewResp {

    @Schema(description = "文件路径")
    private String path;

    @Schema(description = "原始文件名称")
    private String originalName;

    @Schema(description = "预览地址")
    private String previewUrl;

}
