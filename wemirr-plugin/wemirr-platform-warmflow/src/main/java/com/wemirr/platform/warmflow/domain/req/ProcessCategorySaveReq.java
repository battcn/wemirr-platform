
package com.wemirr.platform.warmflow.domain.req;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 流程类别添加或修改
 *
 * @author Levin
 */
@Data
@Schema(name = "ProcessCategorySaveReq")
public class ProcessCategorySaveReq {


    @Schema(description = "类别编码(唯一)")
    @NotBlank(message = "类别编码(唯一)不能为空")
    private String code;

    @Schema(description = "类别名称")
    @NotBlank(message = "类别名称不能为空")
    private String name;

    @Schema(description = "类别状态:0-禁用,1-启用")
    @NotNull(message = "类别状态不能为空")
    private Integer status;

    @Schema(description = "类别描述")
    private String description;

    @Schema(description = "类别logo")
    private String icon;

}
