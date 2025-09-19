package com.wemirr.platform.workflow.feign.domain.resp;

import com.wemirr.platform.workflow.feign.domain.enums.ApprovalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 流程实例返回体
 * @author battcn
 * @since 2025/5/27
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "启动流程实例相应对象")
public class InstanceStartResp {

    @Schema(description = "流程实例ID")
    private Long instanceId;

    @Schema(description = "流程实例状态")
    private ApprovalStatus flowStatus;
}
