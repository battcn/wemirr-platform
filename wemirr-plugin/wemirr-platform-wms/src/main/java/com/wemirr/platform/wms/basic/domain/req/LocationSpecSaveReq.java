package com.wemirr.platform.wms.basic.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 储位规格保存请求数据
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@Schema(name = "LocationSpecSaveReq", description = "储位规格保存请求数据")
public class LocationSpecSaveReq {

    @NotBlank(message = "规格编号不能为空")
    @Schema(description = "储位规格编号")
    private String code;

    @NotBlank(message = "规格名称不能为空")
    @Schema(description = "储位规格名称")
    private String name;

    @NotBlank(message = "规格状态不能为空")
    @Schema(description = "状态")
    private String status;

    @NotNull(message = "长度不能为空")
    @Schema(description = "长度")
    private BigDecimal length;

    @NotNull(message = "宽度不能为空")
    @Schema(description = "宽度")
    private BigDecimal width;

    @NotNull(message = "高度不能为空")
    @Schema(description = "高度")
    private BigDecimal height;

    @NotNull(message = "承重信息不能为空")
    @Schema(description = "承重(KG)")
    private BigDecimal loadCapacity;
}
