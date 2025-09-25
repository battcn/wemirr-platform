package com.wemirr.platform.wms.basic.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 储位规格分页查询响应数据
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@Schema(name = "LocationSpecPageResp", description = "储位规格分页查询响应数据")
public class LocationSpecPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "储位规格编号")
    private String code;

    @Schema(description = "储位规格名称")
    private String name;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "长度")
    private BigDecimal length;

    @Schema(description = "宽度")
    private BigDecimal width;

    @Schema(description = "高度")
    private BigDecimal height;

    @Schema(description = "承重(KG)")
    private BigDecimal loadCapacity;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;
}
