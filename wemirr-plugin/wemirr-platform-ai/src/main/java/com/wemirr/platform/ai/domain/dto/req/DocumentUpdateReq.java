package com.wemirr.platform.ai.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 文档更新请求
 *
 * @author xJh
 * @since 2025/12/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "文档更新请求")
public class DocumentUpdateReq {

    @NotNull(message = "文档ID不能为空")
    @Schema(description = "文档ID")
    private Long id;

    @Schema(description = "文档标题")
    private String title;

    @Schema(description = "文档内容")
    private String content;

    @Schema(description = "内容类型：text/pdf/docx/html等")
    private String contentType;

    @Schema(description = "文件路径")
    private String filePath;

    @Schema(description = "文件大小(字节)")
    private Long fileSize;

    @Schema(description = "文档元数据")
    private Map<String, Object> metadata;
}
