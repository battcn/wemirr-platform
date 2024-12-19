package com.wemirr.platform.bpm.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.bpm.domain.enums.ProcTaskStatus;
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
@TableName("wp_process_task_his")
public class ProcessTaskHistory extends SuperEntity<Long> {

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "任务ID")
    private String procTaskId;

    @Schema(description = "流程定义key")
    private String procTaskDefKey;

    @Schema(description = "任务状态")
    private ProcTaskStatus procTaskStatus;

    @Schema(description = "任务名称")
    private String procTaskName;

    @Schema(description = "流程实例ID")
    private String procInstId;

    @Schema(description = "激活的节点")
    private String activityId;

    @Schema(description = "审核内容")
    private String remark;

    @Schema(description = "审核附件（暂时没有）")
    private String attachment;

    @Schema(description = "审核任务描述")
    private String description;

}
