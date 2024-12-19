package com.wemirr.platform.bpm.domain.req;

import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author Levin
 */
@Data
@Schema(name = "FormDesignSaveReq")
public class FormDesignSaveReq {

    @NotBlank(message = "表单编码不能为空")
    private String tableId;
    @NotNull(message = "表单配置不能为空")
    private JSONObject formConfig;
    @NotEmpty(message = "表单字段不能为空")
    private List<JSONObject> formFields;
    @NotNull(message = "模型ID不能为空")
    private Long modelId;
}
