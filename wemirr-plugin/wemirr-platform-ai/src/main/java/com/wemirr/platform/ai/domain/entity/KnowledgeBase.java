package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
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
 * 知识库配置
 *
 * @author xJh
 * @since 2025/10/17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_knowledge_base")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "知识库配置")
public class KnowledgeBase extends SuperEntity<Long> {

    @Schema(description = "知识库名称")
    private String name;


    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "向量集合名称(物理存储表名/索引名)", hidden = true)
    private String collectionName;

    @Schema(description = "Embedding模型ID（创建后不可修改）")
    private Long embedModelId;

    /**
     * 合并 maxResults 和 retrievalLimit，使用行业术语
     */
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

    @Version
    @Schema(description = "乐观锁版本号")
    private Integer version;

    @Schema(description = "扩展元数据")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> metadata;

    @Schema(description = "描述")
    private String description;
}