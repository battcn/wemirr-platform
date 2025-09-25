package com.wemirr.platform.wms.matedata.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 物料分页查询响应数据
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@Schema(name = "MaterialPageResp", description = "物料分页查询响应数据")
public class MaterialPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "物料编号")
    private String code;

    @Schema(description = "物料名称")
    private String name;

    @Schema(description = "物料描述")
    private String description;

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

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;
}
