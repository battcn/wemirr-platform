package com.wemirr.platform.wms.basic.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 容器规格保存请求数据
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@Schema(name = "ContainerSpecSaveReq", description = "容器规格保存请求数据")
public class ContainerSpecSaveReq {

    @NotBlank(message = "规格名称不能为空")
    @Schema(description = "容器规格名称")
    private String name;

    @NotBlank(message = "规格状态不能为空")
    @Schema(description = "状态")
    private String status;

    @NotNull(message = "长度不能为")
    @Schema(description = "长度")
    private BigDecimal length;

    @NotNull(message = "宽度不能为")
    @Schema(description = "宽度")
    private BigDecimal width;

    @NotNull(message = "高度不能为")
    @Schema(description = "高度")
    private BigDecimal height;

    @NotNull(message = "自重不能为")
    @Schema(description = "容器自重(单位KG)")
    private BigDecimal selfWeight;

    @NotNull(message = "可承载长度不能为")
    @Schema(description = "可承载长度")
    private BigDecimal loadLength;

    @NotNull(message = "可承载宽度不能为")
    @Schema(description = "可承载宽度")
    private BigDecimal loadWidth;

    @NotNull(message = "可承载高度不能为")
    @Schema(description = "可承载高度")
    private BigDecimal loadHeight;

    @NotNull(message = "可承载重量不能为")
    @Schema(description = "可承载重量(单位KG)")
    private BigDecimal loadWeight;
}
