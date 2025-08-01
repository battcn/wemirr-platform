package com.wemirr.platform.flow.domain.resp;

import com.wemirr.platform.flow.domain.enums.ProcInstStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * 流程任务信息
 *
 * @author Levin
 */
@Data
@Schema(name = "ProcessTaskExtResp")
public class ProcessTaskExtResp {

    @Schema(description = "任务扩展ID")
    private String id;

    @Schema(description = "任务ID")
    private String procTaskId;

    @Schema(description = "任务名称")
    private String procTaskName;

    @Schema(description = "任务执行人ID")
    private String approverId;

    @Schema(description = "任务执行人名字")
    private String approverName;

    @Schema(description = "发起人ID")
    private Long initiatorId;

    @Schema(description = "发起人名字")
    private String initiatorName;

    @Schema(description = "流程类型ID")
    private Long processCategoryId;

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

    @Schema(description = "流程状态")
    private ProcInstStatus procInstStatus;

    @Schema(description = "执行人")
    private String createdName;

    @Schema(description = "申请时间")
    private Instant createdTime;
}
