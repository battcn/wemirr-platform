package com.wemirr.platform.workflow.feign.domain.req;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * 启动流程实例需要的参数
 *
 * @author Levin
 */
@Data
public class StartInstanceReq {

    @NotBlank(message = "流程模型编号不能为空")
    @Schema(description = "流程模型编号", example = "DEMO")
    private String modelCode;

    @NotBlank(message = "流程实例名称不能为空")
    @Schema(description = "流程实例名", example = "测试 FEIGN 启动流程实例")
    private String processInstName;

    @NotBlank(message = "业务KEY不能为空")
    @Schema(description = "业务KEY（可以是ID,可以是单据）", example = "1")
    private String businessKey;

    @Schema(description = "业务组(非必填,业务KEY的补充)", example = "group-1")
    private String businessGroup;

    @Schema(description = "业务TAG;比如 订单,用户,合同")
    private List<String> businessTag;

    @NotNull(message = "发起时间不能为空")
    @Schema(description = "发起时间")
    private Instant startTime;

    @Schema(description = "备注", example = "测试一下")
    private String remark;

    @NotNull(message = "表单参数不能为空")
    @Schema(description = "表单参数")
    private JsonNode variables;

}
