package com.wemirr.platform.warmflow.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.platform.warmflow.domain.enums.TaskCommentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 流程任务扩展信息表
 *
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("wp_process_task_comment")
public class ProcessTaskComment extends Entity<Long> {

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "任务ID")
    private String procTaskId;

    @Schema(description = "流程实例ID")
    private String procInstId;

    @Schema(description = "任务定义Key")
    private String taskDefinitionKey;

    @Schema(description = "内容")
    private String remark;

    @Schema(description = "附件")
    private String attachment;

    @Schema(description = "0=评论;1=审批")
    private TaskCommentType type;


}
