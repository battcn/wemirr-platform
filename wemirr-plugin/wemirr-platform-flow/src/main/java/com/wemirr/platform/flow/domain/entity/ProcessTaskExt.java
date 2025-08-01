package com.wemirr.platform.flow.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
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
@TableName("wp_process_task_ext")
public class ProcessTaskExt extends SuperEntity<Long> {

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "任务ID")
    private String procTaskId;

    @Schema(description = "流程定义key")
    private String procTaskDefKey;

    @Schema(description = "任务名称")
    private String procTaskName;

    @Schema(description = "任务执行人;多个逗号分隔")
    private String assignee;

    @Schema(description = "发起人ID")
    private Long initiatorId;

    @Schema(description = "发起人名字")
    private String initiatorName;

    @Schema(description = "流程类型ID")
    private Long procCategoryId;

    @Schema(description = "流程类型名字")
    private String procCategoryName;

    @Schema(description = "流程实例ID")
    private String procInstId;

    @Schema(description = "流程实例名称")
    private String procInstName;

    @Schema(description = "流程定义ID")
    private String procDefId;

    @Schema(description = "流程定义key")
    private String procDefKey;

    @Schema(description = "流程定义名称")
    private String procDefName;






}
