package com.wemirr.platform.ai.domain.dto.rep;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.util.Map;

/**
 * @author xiao1
 * @since  2025-12
 */
@Data
public class VectorizationRep {

    /**
     * 任务ID
     */
    @TableField("task_id")
    private String taskId;

    /**
     * 知识库ID
     */
    @TableField("kb_id")
    private Long kbId;

    /**
     * 知识条目ID
     */
    @TableField("item_id")
    private Long itemId;

    /**
     * 任务类型
     * SINGLE: 单条文本向量化
     * BATCH: 批量文本向量化
     * DOCUMENT: 文档向量化
     * FAQ: FAQ向量化
     * STRUCTURED: 结构化数据向量化
     * KNOWLEDGE_ITEM: 知识条目向量化
     */
    @TableField("task_type")
    private String taskType;

    /**
     * 是否已向量化
     */
    @TableField("vectorized")
    private Boolean vectorized;

    /**
     * 任务状态
     */
    @TableField("status")
    private String status;

    /**
     * 结果向量ID列表（JSON格式）
     */
    @TableField(value = "vector_ids", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> vectorIds;

    /**
     * 错误信息
     */
    @TableField("error_message")
    private String errorMessage;
}
