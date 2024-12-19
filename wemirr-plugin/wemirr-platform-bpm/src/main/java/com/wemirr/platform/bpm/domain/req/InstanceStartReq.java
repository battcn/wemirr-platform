package com.wemirr.platform.bpm.domain.req;

import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 启动流程实例需要的参数
 *
 * @author Levin
 */
@Data
public class InstanceStartReq {

    @NotBlank(message = "启动流程不能为空")
    @Schema(description = "实例扩展信息Id")
    private String designModelId;

    @NotBlank(message = "业务KEY不能为空")
    @Schema(description = "业务KEY（可以是ID,可以是单据）")
    private String businessKey;

    @Schema(description = "业务组")
    private String businessGroup;

    @NotBlank(message = "流程名称不能为空")
    @Schema(description = "流程名称")
    private String instanceName;

    @NotBlank(message = "表单数据不能为空")
    @Schema(description = "表单数据")
    private JSONObject formData;

}
