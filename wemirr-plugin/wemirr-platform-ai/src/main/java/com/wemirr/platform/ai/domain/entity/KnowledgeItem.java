package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.ai.core.enums.KnowledgeItemStatus;
import com.wemirr.platform.ai.core.enums.KnowledgeItemType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * 知识条目实体类
 * 统一管理不同类型的知识，包括文档、问答对、结构化数据等
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_kb_knowledge_item")
@EqualsAndHashCode(callSuper = true)
public class KnowledgeItem extends SuperEntity<Long> {
    
    /**
     * 所属知识库ID
     */
    @TableField("kb_id")
    private Long kbId;

    /**
     * 知识条目类型（枚举）
     */
    @TableField("item_type")
    private KnowledgeItemType type;

    /**
     * 标题（文档标题或FAQ的展示文本）
     */
    @TableField("title")
    private String title;

    /**
     * 问题，仅当 type = QA_PAIR 有效
     */
    @TableField("question")
    private String question;

    /**
     * 答案，仅当 type = QA_PAIR 有效
     */
    @TableField("answer")
    private String answer;

    /**
     * 原始内容（用于分片与向量化）
     */
    @TableField("content")
    private String content;

    /**
     * 内容类型（如 pdf、text、html），仅对文档类有效
     */
    @TableField("content_type")
    private String contentType;

    /**
     * 文件路径，仅对文档类有效
     */
    @TableField("file_path")
    private String filePath;

    /**
     * 文件大小，仅对文档类有效
     */
    @TableField("file_size")
    private Long fileSize;

    /**
     * 内容哈希，用于去重与变更检测
     */
    @TableField("content_hash")
    private String contentHash;

    /**
     * 处理状态（枚举）
     */
    @TableField("status")
    private KnowledgeItemStatus status;

    /**
     * 是否已向量化
     */
    @TableField("vectorized")
    private Boolean vectorized;

    /**
     * 是否已图谱化
     */
    @TableField("graphized")
    private Boolean graphized;

    /**
     * 乐观锁版本
     */
    @TableField("version")
    private Integer version;

    /**
     * 扩展元数据（JSON）
     */
    @TableField(value = "metadata", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> metadata;

    @Schema(description = "租户ID")
    private String tenantId;
}