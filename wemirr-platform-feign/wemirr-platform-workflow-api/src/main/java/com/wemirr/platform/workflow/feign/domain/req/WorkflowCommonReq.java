package com.wemirr.platform.workflow.feign.domain.req;

import com.wemirr.platform.workflow.feign.domain.enums.FlowActionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工作流 传输业务数据dto
 *
 * @author battcn
 * @since 2025/5/28
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowCommonReq {

    @Schema(description = "原始业务ID")
    private String originalId;

    @Schema(description = "更新数据的id")
    private String updateId;

    @Schema(description = "操作类型 更新/创建")
    private FlowActionType action;

}
