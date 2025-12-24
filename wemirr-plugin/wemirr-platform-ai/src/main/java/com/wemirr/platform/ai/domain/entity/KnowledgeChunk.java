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
 * 知识分片实体类
 * 用于统一管理不同类型知识的向量化分片
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_kb_knowledge_chunk")
@EqualsAndHashCode(callSuper = true)
public class KnowledgeChunk extends SuperEntity<Long> {
    
    /**
     * 所属知识库ID
     */
    @TableField("kb_id")
    private Long kbId;
    
    /**
     * 关联的知识条目ID
     */
    @TableField("item_id")
    private Long itemId;
    
    /**
     * 分片类型（枚举）
     */
    @TableField("chunk_type")
    private ChunkType chunkType;
    
    /**
     * 分片内容
     * 用于embedding的文本
     */
    @TableField("content")
    private String content;
    
    /**
     * 内容哈希值
     */
    @TableField("content_hash")
    private String contentHash;
    
    /**
     * 外部向量库引用（如 milvus:12345 / pgvector:67890）
     */
    @TableField("vector_ref")
    private String vectorRef;
    
    /**
     * 分片序号
     * 对于文档分片，表示在原文中的顺序
     */
    @TableField("chunk_index")
    private Integer chunkIndex;
    
    /**
     * 在原文中的起始位置
     */
    @TableField("start_position")
    private Integer startPosition;
    
    /**
     * 在原文中的结束位置
     */
    @TableField("end_position")
    private Integer endPosition;

    
    /**
     * 分片元数据
     */
    @TableField(value = "metadata", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> metadata;

    @Schema(description = "租户ID")
    private String tenantId;
}
