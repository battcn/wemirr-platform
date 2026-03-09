package com.wemirr.platform.ai.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Map;

/**
 * @author xJh
 * @since 2025/10/20
 **/
@Data
@Schema(description = "知识库保存请求")
public class KnowledgeBaseSaveReq {

    @NotBlank(message = "知识库名称不能为空")
    @Schema(description = "知识库名称")
    private String name;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "向量集合名称(物理存储表名/索引名)", hidden = true)
    private String collectionName;

    @Schema(description = "Embedding模型ID（创建后不可修改）")
    private Long embedModelId;

    @Schema(description = "单次召回数量(TopK)", defaultValue = "5")
    private Integer topK;

    @Schema(description = "相似度阈值 (0.0-1.0)", defaultValue = "0.6")
    private Double scoreThreshold;

    @Schema(description = "重排序模型ID (Rerank)", nullable = true)
    private Long rerankModelId;

    @Schema(description = "分片大小 (Token)", defaultValue = "512")
    private Integer chunkSize;

    @Schema(description = "分片重叠 (Token)", defaultValue = "64")
    private Integer chunkOverlap;

    @Schema(description = "是否启用知识图谱")
    private Boolean enableGraph;

    @Schema(description = "默认预览对话模型ID")
    private Long chatModelId;

    @Schema(description = "扩展元数据")
    private Map<String, Object> metadata;
}
