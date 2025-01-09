package com.wemirr.platform.suite.online.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Levin
 */
@Data
public class OnlineFormDesignerSaveReq {

    @NotBlank(message = "定义KEY不能为空")
    @Schema(description = "定义KEY")
    private String definitionKey;

    @NotBlank(message = "模型标题不能为空")
    @Schema(description = "标题")
    private String title;

    @NotBlank(message = "描述不能为空")
    @Schema(description = "描述")
    private String description;

    @NotNull(message = "状态不能为空")
    @Schema(description = "状态")
    private Boolean status;


}
