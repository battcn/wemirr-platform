package com.wemirr.platform.wms.basic.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Levin
 */
@Data
public class DockSaveReq {

    @NotBlank(message = "编号不能为空")
    @Schema(description = "月台编号")
    private String code;

    @NotBlank(message = "名称不能为空")
    @Schema(description = "月台名称")
    private String name;

    @NotBlank(message = "规格不能为空")
    @Schema(description = "月台规格")
    private String spec;

    @NotBlank(message = "类型不能为空")
    @Schema(description = "类型")
    private String type;

    @NotBlank(message = "状态不能为空")
    @Schema(description = "状态")
    private String status;

}
