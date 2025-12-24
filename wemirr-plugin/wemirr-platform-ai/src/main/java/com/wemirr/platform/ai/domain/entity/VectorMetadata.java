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
 * 向量元数据实体
 * 用于存储向量在向量数据库中的元数据信息
 *
 * @author xJh
 * @since 2025/10/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_kb_vector_metadata")
public class VectorMetadata extends SuperEntity<Long> {

    /**
     * 向量ID（在向量数据库中的唯一标识）
     */
    @TableField("vector_id")
    private String vectorId;

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
     * 关联的知识分片ID
     */
    @TableField("chunk_id")
    private Long chunkId;

    /**
     * 分片类型
     */
    @TableField("chunk_type")
    private ChunkType chunkType;


    /**
     * 向量数据库集合名称
     */
    @TableField("collection_name")
    private String collectionName;

    /**
     * 文本内容（用于检索时的显示）
     */
    @TableField("text_content")
    private String textContent;

    /**
     * 文本哈希值
     */
    @TableField("text_hash")
    private String textHash;

    /**
     * 相似度分数（用于缓存搜索结果）
     */
    @TableField("similarity_score")
    private Double similarityScore;


    /**
     * 扩展元数据
     */
    @TableField(value = "metadata", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> metadata;

    @Schema(description = "租户ID")
    private String tenantId;
}
