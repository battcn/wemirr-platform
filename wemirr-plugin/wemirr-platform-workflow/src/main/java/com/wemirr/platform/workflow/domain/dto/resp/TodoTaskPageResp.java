package com.wemirr.platform.workflow.domain.dto.resp;

import com.wemirr.platform.workflow.feign.domain.enums.ApprovalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * 待办任务分页返回体
 *
 * @author battcn
 * @since 2025/6/04
 **/
@Data
@Schema(description = "待办任务返回体")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoTaskPageResp {

    @Schema(description = "任务ID")
    private String id;

    @Schema(description = "流程节点编码")
    private String nodeCode;

    @Schema(description = "流程节点名称")
    private String nodeName;

    @Schema(description = "流程节点类型")
    private Integer nodeType;

    @Schema(description = "流程定义ID")
    private Long definitionId;

    @Schema(description = "流程实例ID")
    private String instanceId;

    @Schema(description = "流程状态")
    private ApprovalStatus flowStatus;

    @Schema(description = "流程状态")
    private String flowStatusName;

    @Schema(description = "创建时间")
    private Instant createTime;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "业务ID")
    private String businessId;

    @Schema(description = "业务ID")
    private String businessCode;

    @Schema(description = "活动状态")
    private String activityStatus;

    @Schema(description = "流程名称")
    private String flowName;

    @Schema(description = "流程编码")
    private String flowCode;

    @Schema(description = "创建人ID")
    private String createBy;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "附件")
    private String attachment;

    @Schema(description = "业务类型")
    private String businessType;

    @Schema(description = "修改时间")
    private Instant updateTime;

    public String getFlowStatusName(){
        return flowStatus == null ? null :flowStatus.getLabel();
    }
}
