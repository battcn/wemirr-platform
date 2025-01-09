package com.wemirr.platform.suite.online.domain.req;

import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Levin
 */
@Data
public class OnlineFormDataSaveReq {

    @NotBlank(message = "定义KEY不能为空")
    @Schema(defaultValue = "定义KEY")
    private String definitionKey;

    @NotNull(message = "表单数据不能为空")
    @Schema(defaultValue = "表单数据")
    private JSONObject formData;

}
