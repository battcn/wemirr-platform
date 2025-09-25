package com.wemirr.platform.wms.matedata.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 基本计量单位转换分页查询响应数据
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@Schema(name = "UnitConvPageResp", description = "基本计量单位转换分页查询响应数据")
public class UnitConvPageResp {

    @Schema(description = "主键")
    private Long id;

    /**
     * 外部单位
     */
    @Schema(description = "基本计量单位ID")
    private Long unitId;

    @Schema(description = "基本计量单位")
    private String unit;

    /**
     * 外部单位
     */
    @Schema(description = "转换的计量单位ID")
    private Long convUnitId;

    /**
     * 数据一般都是库存单位
     */
    @Schema(description = "转换的基本计量单位")
    private String convUnit;

    @Schema(description = "转换为基本单位的分子")
    private BigDecimal numerator;

    @Schema(description = "转换为基本单位的分母")
    private BigDecimal denominator;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;

    @Schema(description = "最后修改时间")
    private Instant lastModifiedTime;

    @Schema(description = "最后修改人名称")
    private String lastModifiedName;
}
