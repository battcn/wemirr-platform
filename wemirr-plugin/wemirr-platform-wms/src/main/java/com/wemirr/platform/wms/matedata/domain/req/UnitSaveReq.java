package com.wemirr.platform.wms.matedata.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 基本计量单位保存请求对象
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@Schema(name = "UnitSaveReq", description = "基本计量单位保存请求对象")
public class UnitSaveReq {

    @NotBlank(message = "单位中文名称不能为空")
    @Schema(description = "计量单位中文名称")
    private String name;

    @NotBlank(message = "单位英文名称不能为空")
    @Schema(description = "计量单位英文名称")
    private String nameEn;

    @NotBlank(message = "单位符号不能为空")
    @Schema(description = "计量单位符号")
    private String symbol;

    @NotBlank(message = "单位类型不能为空")
    @Schema(description = "计量单位类型")
    private String type;

    @NotBlank(message = "单位状态不能为空")
    @Schema(description = "计量单位状态")
    private String status;

    @Schema(description = "描述")
    private String description;
}
