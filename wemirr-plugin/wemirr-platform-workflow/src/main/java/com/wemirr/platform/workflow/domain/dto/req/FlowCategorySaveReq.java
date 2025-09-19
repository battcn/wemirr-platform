package com.wemirr.platform.workflow.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author battcn
 * @since 2025/8/15
 **/
@Data
public class FlowCategorySaveReq {

    @NotBlank(message = "ICON不能为空")
    @Schema(description = "ICON")
    private String icon;

    @NotBlank(message = "分类名称不能为空")
    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "分类状态")
    private Boolean status;

    @Schema(description = "分类描述")
    private String description;
}
