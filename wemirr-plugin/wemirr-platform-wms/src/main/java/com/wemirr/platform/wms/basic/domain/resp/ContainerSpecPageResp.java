package com.wemirr.platform.wms.basic.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 容器规格分页查询响应数据
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@Schema(name = "ContainerSpecPageResp", description = "容器规格分页查询响应数据")
public class ContainerSpecPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "容器规格名称")
    private String name;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "长度")
    private BigDecimal length;

    @Schema(description = "宽度")
    private BigDecimal width;

    @Schema(description = "高度")
    private BigDecimal height;

    @Schema(description = "容器自重(单位KG)")
    private BigDecimal selfWeight;

    @Schema(description = "可承载长度")
    private BigDecimal loadLength;

    @Schema(description = "可承载宽度")
    private BigDecimal loadWidth;

    @Schema(description = "可承载高度")
    private BigDecimal loadHeight;

    @Schema(description = "可承载重量(单位KG)")
    private BigDecimal loadWeight;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;
}
