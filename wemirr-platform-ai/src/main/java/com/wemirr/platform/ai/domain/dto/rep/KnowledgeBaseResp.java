package com.wemirr.platform.ai.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author xJh
 * @date 2025/10/20
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "知识库响应")
public class KnowledgeBaseResp {

    @Schema(description = "知识库ID")
    private Long id;

    @Schema(description = "知识库名称")
    private String name;

    @Schema(description = "知识库描述")
    private String description;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "版本号")
    private Integer version;

    @Schema(description = "文档数量")
    private Integer documentCount;

    @Schema(description = "FAQ数量")
    private Integer faqCount;

    @Schema(description = "结构化数据数量")
    private Integer structuredDataCount;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "扩展元数据")
    private Map<String, Object> metadata;
}
