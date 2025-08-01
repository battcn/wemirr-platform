package com.wemirr.platform.warmflow.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
@Schema(name = "ProcessTaskHistoryResp")
public class ProcessTaskHistoryResp {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "任务ID")
    private String procTaskId;

    @Schema(description = "任务名称")
    private String procTaskName;

    @Schema(description = "任务状态")
    private String procTaskStatus;

    @Schema(description = "任务发起人ID")
    private String initiatorId;

    @Schema(description = "任务发起人名字")
    private String initiatorName;

    @Schema(description = "流程类型ID")
    private String procCategoryId;

    @Schema(description = "流程类型编码")
    private String procInstCategoryCode;

    @Schema(description = "流程类型名字")
    private String procInstCategoryName;

    @Schema(description = "流程实例版本")
    private Integer procInstVersion;

    @Schema(description = "流程定义ID")
    private String procDefId;

    @Schema(description = "流程定义名称")
    private String procDefName;

    @Schema(description = "流程实例ID")
    private String procInstId;

    @Schema(description = "流程实例名称")
    private String procInstName;

    @Schema(description = "流程实例状态")
    private String procInstStatus;

    @Schema(description = "审批内容")
    private String remark;

    @Schema(description = "任务描述")
    private String description;

    @Schema(description = "任务实例开始时间")
    private Instant taskInstStartTime;

    @Schema(description = "任务实例结束时间")
    private Instant taskInstEndTime;

    @Schema(description = "审批人/创建人")
    private String approverName;




}
