package com.wemirr.platform.wms.matedata.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 物料保存请求数据
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@Schema(name = "MaterialSaveReq", description = "物料保存请求数据")
public class MaterialSaveReq {

    @NotBlank(message = "名字不能为空")
    @Schema(description = "物料名字")
    private String name;

    @NotNull(message = "类目不能为空")
    @Schema(description = "类别ID")
    private String categoryId;

    @NotNull(message = "品牌不能为空")
    @Schema(description = "品牌ID")
    private Long brandId;

    @NotBlank(message = "品牌不能为空")
    @Schema(description = "单位(库内单位)")
    private String unit;

    @NotBlank(message = "规格不能为空")
    @Schema(description = "规格")
    private String spec;

    @NotBlank(message = "型号不能为空")
    @Schema(description = "型号")
    private String model;

    @NotBlank(message = "条码不能为空")
    @Schema(description = "条码")
    private String barCode;

    @NotNull(message = "毛重不能为空")
    @Schema(description = "毛重")
    private BigDecimal grossWeight;

    @NotNull(message = "净重不能为空")
    @Schema(description = "净重")
    private BigDecimal netWeight;

    @NotNull(message = "长不能为空")
    @Schema(description = "长")
    private BigDecimal length;

    @NotNull(message = "宽不能为空")
    @Schema(description = "宽")
    private BigDecimal width;

    @NotNull(message = "高不能为空")
    @Schema(description = "高")
    private BigDecimal height;

    @Schema(description = "物料配置")
    private MaterialConfigSaveReq config;


    @Schema(description = "描述")
    private String description;
}
