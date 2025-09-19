package com.wemirr.platform.workflow.feign.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 流程定义开启的请求体
 *
 * @author battcn
 * @since 2025/5/21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "流程定义开启的请求体")
public class WorkflowStartReq {

    @Schema(description = "流程编号")
    private String flowCode;

    @Schema(description = "业务编号")
    @NotNull(message = "业务编号不能为空")
    private String businessCode;

    @Schema(description = "业务ID")
    private String businessId;

    @Schema(description = "操作")
    private String action;

    @Schema(description = "流程变量")
    private Map<String, Object> variables;


    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "附件")
    private String attachment;



}
