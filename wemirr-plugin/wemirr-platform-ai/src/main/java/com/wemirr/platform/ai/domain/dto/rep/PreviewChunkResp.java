package com.wemirr.platform.ai.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文档分块预览响应
 *
 * @author xiao1
 * @since 2025-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "文档分块预览")
public class PreviewChunkResp {

    @Schema(description = "分块ID")
    private Long id;

    @Schema(description = "分块内容")
     private String content;

}
