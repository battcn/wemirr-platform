package com.wemirr.platform.workflow.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * 查询实例所有任务的返回体
 * @author battcn
 * @since 2025/6/4
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "流程任务审批记录返回体")
public class FlowTaskApproveListResp {

    @Schema(description = "流程实例Id")
    private String instanceId;

    @Schema(description = "任务Id")
    private String taskId;

    @Schema(description = "流程节点编码")
    private String nodeCode;

    @Schema(description = "流程节点名称")
    private String nodeName;

    @Schema(description = "流程节点类型")
    private Integer nodeType;

    @Schema(description = "流程状态")
    private String flowStatus;

    @Schema(description = "审批人（多个用逗号分隔）")
    private String approver;

    @Schema(description = "审批人名称")
    private String approverName;

    @Schema(description = "审批时间")
    private Instant approvalTime;

    @Schema(description = "流程变量")
    private String variable;

    @Schema(description = "审批意见")
    private String message;

}
