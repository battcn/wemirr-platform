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
 * 向量化任务
 * <p>
 * 记录知识条目的向量化处理状态和结果
 *
 * @author xJh
 * @since 2025/10/20
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("ai_kb_vectorization_task")
@Schema(description = "向量化任务")
public class VectorizationTask extends SuperEntity<Long> {

    @Schema(description = "任务ID（业务唯一标识）")
    private String taskId;

    @Schema(description = "知识库ID")
    private Long kbId;

    @Schema(description = "知识条目ID")
    private Long itemId;

    @Schema(description = "任务类型（DOCUMENT/QA_PAIR/STRUCTURED/BATCH）")
    private String taskType;

    @Schema(description = "任务状态（PENDING/PROCESSING/COMPLETED/FAILED）")
    private String status;

    @Schema(description = "处理进度（0-100）")
    private Integer progress;

    @Schema(description = "结果向量ID列表")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> vectorIds;

    @Schema(description = "错误信息")
    private String errorMessage;

    @Schema(description = "消耗的Token数")
    private Integer tokenUsage;

    @Schema(description = "租户ID")
    private Long tenantId;

    /**
     * 获取任务状态枚举
     */
    public VectorizationTaskStatus getTaskStatus() {
        if (status == null) {
            return VectorizationTaskStatus.PENDING;
        }
        return switch (status) {
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

    /**
     * 判断是否已完成向量化
     */
    public boolean isVectorized() {
        return "COMPLETED".equals(status);
    }

    /**
     * 判断是否失败
     */
    public boolean isFailed() {
        return "FAILED".equals(status);
    }

    /**
     * 判断是否处理中
     */
    public boolean isProcessing() {
        return "PROCESSING".equals(status);
    }
}
