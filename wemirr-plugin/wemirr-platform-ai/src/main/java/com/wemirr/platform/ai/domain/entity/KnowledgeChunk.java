package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.ai.core.enums.ChunkType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * 知识分片
 * 用于统一管理不同类型知识的向量化分片
 *
 * @author xJh
 * @since 2025/10/20
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_knowledge_chunk")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "知识分片")
public class KnowledgeChunk extends SuperEntity<Long> {
    
    @Schema(description = "所属知识库ID")
    private Long kbId;
    
    @Schema(description = "关联的知识条目ID")
    private Long itemId;
    
    @Schema(description = "分片类型")
    private ChunkType chunkType;
    
    @Schema(description = "分片内容")
    private String content;
    
    @Schema(description = "内容哈希值")
    private String contentHash;
    
    @Schema(description = "外部向量库引用（如 milvus:12345）")
    private String vectorRef;
    
    @Schema(description = "分片序号")
    private Integer chunkIndex;
    
    @Schema(description = "在原文中的起始位置")
    private Integer startPosition;
    
    @Schema(description = "在原文中的结束位置")
    private Integer endPosition;

    @Schema(description = "分片元数据")
    @TableField(value = "metadata", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> metadata;

    @Schema(description = "租户ID")
    private Long tenantId;
}