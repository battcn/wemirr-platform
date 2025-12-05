package com.wemirr.platform.ai.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author xJh
 * @since 2025/10/20
 **/
@Data
@Builder
@Schema(description = "文档保存请求")
public class DocumentSaveReq {

    @NotBlank(message = "知识库ID不能为空")
    @Schema(description = "所属知识库ID")
    private Long kbId;

    @NotBlank(message = "文档标题不能为空")
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
