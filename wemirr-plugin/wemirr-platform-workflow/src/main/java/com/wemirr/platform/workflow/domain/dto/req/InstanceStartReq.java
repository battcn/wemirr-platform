package com.wemirr.platform.workflow.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

/**
 * 启动流程实例需要的参数
 *
 * @author Levin
 */
@Data
public class InstanceStartReq {

    @NotBlank(message = "业务KEY不能为空")
    @Schema(description = "业务KEY（可以是ID,可以是单据）")
    private String businessKey;

    @Schema(description = "业务组")
    private String businessGroup;

    @NotBlank(message = "流程名称不能为空")
    @Schema(description = "流程名称")
    private String instanceName;

    @NotNull(message = "表单数据不能为空")
    @Schema(description = "表单数据")
    private Map<String, Object> formData;

}
