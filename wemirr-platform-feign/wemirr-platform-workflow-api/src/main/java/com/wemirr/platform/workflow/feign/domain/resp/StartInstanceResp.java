package com.wemirr.platform.workflow.feign.domain.resp;

import com.wemirr.platform.workflow.feign.domain.enums.ProcessModelStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartInstanceResp {

    @Schema(defaultValue = "流程实例ID")
    private String processInstanceId;

    @Schema(defaultValue = "模型状态")
    private ProcessModelStatus modelStatus;

    @Schema(defaultValue = "业务KEY")
    private String businessKey;

}
