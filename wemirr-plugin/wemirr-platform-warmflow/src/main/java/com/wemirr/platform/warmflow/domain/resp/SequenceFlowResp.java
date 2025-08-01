/**
 * Copyright 2022 bejson.com
 */
package com.wemirr.platform.warmflow.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 流程连线信息
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SequenceFlowResp {

    @Schema(name = "sequenceFlowId", description = "连线ID")
    private String sequenceFlowId;
    @Schema(name = "sourceBpmnActivityId", description = "来源活动ID")
    private String sourceBpmnActivityId;
    @Schema(name = "targetBpmnActivityId", description = "目标活动节点活动ID")
    private String targetBpmnActivityId;
    @Schema(name = "targetActivityState", description = "目标活动节点状态")
    private int targetActivityState;
    @Schema(name = "processInstanceId", description = "流程实例ID")
    private String processInstanceId;
    @Schema(name = "bpmnSequenceFlowId", description = "连线ID")
    private String bpmnSequenceFlowId;
    @Schema(name = "sequenceFlowName", description = "连线名称")
    private String sequenceFlowName;
}