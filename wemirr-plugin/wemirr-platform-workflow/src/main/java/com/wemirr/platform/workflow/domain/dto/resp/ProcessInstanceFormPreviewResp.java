package com.wemirr.platform.workflow.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 流程表单渲染返回参数
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessInstanceFormPreviewResp {

    @NotEmpty(message = "表单字段不能为空")
    private FormDesign formDesign;

    @Schema(description = "表单数据")
    private LinkedHashMap<String, Object> formData;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FormDesign {

        @Schema(defaultValue = "脚本")
        private String script;

        @NotEmpty(message = "表单字段不能为空")
        private ArrayList<Object> schemas;
    }

}
