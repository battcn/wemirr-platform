package com.wemirr.platform.wms.matedata.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * <p>
 * 基本计量单位转换表
 * </p>
 *
 * @author ddCat
 * @since 2024-06-24
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_unit_conv")
@Schema(name = "UnitConv", description = "基本计量单位转换")
@EqualsAndHashCode(callSuper = true)
public class UnitConv extends SuperEntity<Long> {

    @Schema(description = "基本计量单位ID")
    private Long unitId;

    @Schema(description = "基本计量单位")
    private String unit;

    @Schema(description = "转换的计量单位ID")
    private Long convUnitId;

    @Schema(description = "转换的基本计量单位")
    private String convUnit;

    @Schema(description = "转换为基本单位的分子")
    private BigDecimal numerator;

    @Schema(description = "转换为基本单位的分母")
    private BigDecimal denominator;

    @Schema(description = "描述")
    private String description;

}
