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
 * 物料表
 * </p>
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_material")
@Schema(name = "Material", description = "物料")
@EqualsAndHashCode(callSuper = true)
public class Material extends SuperEntity<Long> {

    @Schema(description = "物料编号")
    private String code;

    @Schema(description = "物料编号")
    private String name;

    @Schema(description = "类型ID")
    private String categoryId;

    @Schema(description = "品牌ID")
    private Long brandId;

    @Schema(description = "基本计量单位(库内单位)")
    private String unit;

    @Schema(description = "规格")
    private String spec;

    @Schema(description = "型号")
    private String model;

    @Schema(description = "条码")
    private String barCode;

    @Schema(description = "毛重")
    private BigDecimal grossWeight;

    @Schema(description = "净重")
    private BigDecimal netWeight;

    @Schema(description = "长")
    private BigDecimal length;

    @Schema(description = "宽")
    private BigDecimal width;

    @Schema(description = "高")
    private BigDecimal height;

    @Schema(description = "物料描述")
    private String description;

}
