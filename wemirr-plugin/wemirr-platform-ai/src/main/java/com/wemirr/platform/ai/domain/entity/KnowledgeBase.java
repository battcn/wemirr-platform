package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * @author xJh
 * @since 2025/10/17
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_kb_knowledge_base")
@EqualsAndHashCode(callSuper = true)
public class KnowledgeBase extends SuperEntity<Long> {

    @Schema(description = "知识库名称")
    private String name;

    @Schema(description = "知识库描述")
    private String description;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "相似结果数量")
    private Integer topK;

    @Schema(description = "相似度分数阈值，仅返回分数高于此值的结果。[-1, 1]，一般 >0.5 表示有一定相关性")
    private Double minScore;

    @Schema(title = "文档切割时重叠数量(根据token计算)")
    @TableField("ingest_max_overlap")
    private Integer ingestMaxOverlap;

    @Schema(title = "文档切割时最大长度(根据token计算)")
    @TableField("ingest_max_length")
    private Integer ingestMaxLength;


    @Schema(title = "文档召回最大数量")
    @TableField("retrieve_max_results")
    private Integer retrieveMaxResults;


    @Schema(description = "聊天模型ID，仅对纯知识库问答生效")
    private Long chatModelId;

    @Schema(description = "向量模型ID,一旦选定默认不允许修改")
    private Long embeddingModelId;

    @Schema(description = "重排序模型ID，用于多路召回结果排序")
    private Long rerankModelId;

    @Schema(description = "是否启用图谱")
    private Boolean enableGraph;

    @Schema(description = "版本号")
    @TableField("version")
    private Integer version;

    @Schema(description = "元数据")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> metadata;
}
