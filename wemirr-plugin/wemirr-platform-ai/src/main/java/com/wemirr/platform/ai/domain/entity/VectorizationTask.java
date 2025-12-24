package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.ai.service.VectorService.VectorizationTaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * 向量化任务实体
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("ai_kb_vectorization_task")
public class VectorizationTask extends SuperEntity<Long> {

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
     * 处理进度（百分比）
     */
    @TableField("progress")
    private Integer progress;
    
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

    /**
     * 消耗的token
     */
    @TableField("token_usage")
    private Integer tokenUsage;

    @Schema(description = "租户ID")
    private String tenantId;

    /**
     * 获取任务状态枚举
     */
    public VectorizationTaskStatus getTaskStatus() {
        if (status == null) {
            return VectorizationTaskStatus.PENDING;
        }
        
        return switch (status) {
            case "PENDING" -> VectorizationTaskStatus.PENDING;
            case "PROCESSING" -> VectorizationTaskStatus.PROCESSING;
            case "COMPLETED" -> VectorizationTaskStatus.COMPLETED;
            case "FAILED" -> VectorizationTaskStatus.FAILED;
            default -> VectorizationTaskStatus.PENDING;
        };
    }
    
    /**
     * 设置任务状态
     */
    public void setTaskStatus(VectorizationTaskStatus taskStatus) {
        this.status = taskStatus.name();
    }
}
