package com.wemirr.platform.workflow.feign.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 流程实例终止请求体
 * @author battcn
 * @since 2025/7/28
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowInstanceTerminationReq {

    @NotBlank(message = "任务处理意见不能为空")
    @Schema(description = "任务处理意见")
    private String message;

    /**
     * 用于业务单据取消或者作废的时候,控制只有审批人才可以操作.
     */
    @Builder.Default
    @Schema(description = "是否忽略权限,默认false")
    private Boolean ignore = Boolean.FALSE;

    @Schema(description = "任务扩展信息")
    private String taskExt;
}
