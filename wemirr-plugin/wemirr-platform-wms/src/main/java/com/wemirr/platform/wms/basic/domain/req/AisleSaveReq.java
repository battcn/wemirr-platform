package com.wemirr.platform.wms.basic.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 巷道保存请求数据
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@Schema(name = "AisleSaveReq", description = "巷道保存请求数据")
public class AisleSaveReq {

    @NotBlank(message = "巷道编号不能为空")
    @Schema(description = "巷道编号")
    private String code;

    @NotBlank(message = "巷道名称不能为空")
    @Schema(description = "巷道名称")
    private String name;

    @Schema(description = "是否启用")
    private String status;

    @NotNull(message = "巷道长度不能为空")
    @Schema(description = "长度")
    private BigDecimal length;

    @NotNull(message = "巷道宽度不能为空")
    @Schema(description = "宽度")
    private BigDecimal width;

    @NotNull(message = "巷道高度不能为空")
    @Schema(description = "高度")
    private BigDecimal height;
}
