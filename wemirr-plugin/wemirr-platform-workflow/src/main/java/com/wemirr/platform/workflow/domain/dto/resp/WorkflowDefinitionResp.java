package com.wemirr.platform.workflow.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 工作流定义返回体
 * @author battcn
 * @since 2025/5/21
 **/
@Data
@Builder
@Schema(description = "工作流定义返回体")
public class WorkflowDefinitionResp {

    /**
     * 流程定义Id
     */
    @Schema(description = "流程定义Id")
    private Long definitionId;

    @Schema(description = "流程定义编码")
    private String flowCode;
}
