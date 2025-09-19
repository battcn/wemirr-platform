package com.wemirr.platform.workflow.domain.dto.resp;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private JSONObject formData;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FormDesign {

        @Schema(defaultValue = "脚本")
        private String script;

        @NotEmpty(message = "表单字段不能为空")
        private JSONArray schemas;
    }

}
