package com.wemirr.platform.workflow.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 转办对象
 *
 * @author battcn
 * @since 2025/5/22
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskTransferReq {

    @NotBlank(message = "任务处理意见不能为空")
    @Schema(description = "任务处理意见")
    private String message;

    @Schema(description = "处理对象", example = "1,role:1,role:2")
    private String handlerObject;

    @Builder.Default
    @Schema(description = "流程变量")
    private Map<String, Object> variable = new HashMap<>();

}
