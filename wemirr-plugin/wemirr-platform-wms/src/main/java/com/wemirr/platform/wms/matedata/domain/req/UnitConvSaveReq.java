package com.wemirr.platform.wms.matedata.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 基本计量单位转换保存请求对象
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@Schema(name = "UnitConvSaveReq", description = "基本计量单位转换保存请求对象")
public class UnitConvSaveReq {

    @NotNull(message = "计量单位ID不能为空")
    @Schema(description = "计量单位ID")
    private Long unitId;

    @NotNull(message = "转换的计量单位ID不能为空")
    @Schema(description = "转换的计量单位ID")
    private Long convUnitId;

    @Schema(description = "转换为基本单位的分子")
    private BigDecimal numerator;

    @Schema(description = "转换为基本单位的分母")
    private BigDecimal denominator;

    @Schema(description = "描述")
    private String description;
}
