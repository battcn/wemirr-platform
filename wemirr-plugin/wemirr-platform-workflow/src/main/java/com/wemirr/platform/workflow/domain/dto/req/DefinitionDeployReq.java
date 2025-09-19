package com.wemirr.platform.workflow.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 流程定义的请求体
 *
 * @author battcn
 * @since 2025/5/21
 **/
@Data
@Schema(name = "DefinitionSaveReq")
public class DefinitionDeployReq {


    @Schema(description = "流程定义 JSON 配置")
    @NotBlank(message = "流程定义 JSON 配置不能为空")
    private String defConfigJson;

}
