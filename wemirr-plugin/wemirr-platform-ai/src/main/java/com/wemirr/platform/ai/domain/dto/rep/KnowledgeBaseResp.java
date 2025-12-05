package com.wemirr.platform.ai.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author xJh
 * @since 2025/10/20
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

    @Schema(description = "聊天模型ID")
    private Long chatModelId;


    @Schema(description = "相似结果数量")
    private Integer topK;

    @Schema(description = "相似度分数阈值，仅返回分数高于此值的结果。[-1, 1]，一般 >0.5 表示有一定相关性")
    private Double minScore;

    @Schema(description = "向量模型ID,一旦选定默认不允许修改")
    private Long embeddingModelId;

    @Schema(title = "文档切割时重叠数量(根据token计算)")
    private Integer ingestMaxOverlap;

    @Schema(title = "文档切割时最大长度(根据token计算)")
    private Integer ingestMaxLength;

    @Schema(title = "文档召回最大数量")
    private Integer retrieveMaxResults;

    @Schema(description = "文档数量")
    private Integer documentCount;

    @Schema(description = "FAQ数量")
    private Integer faqCount;

    @Schema(description = "结构化数据数量")
    private Integer structuredDataCount;

    @Schema(description = "扩展元数据")
    private Map<String, Object> metadata;
}
