package com.wemirr.platform.wms.basic.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 巷道分页查询响应数据
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@Schema(name = "AislePageResp", description = "巷道分页查询响应数据")
public class AislePageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "巷道编号")
    private String code;

    @Schema(description = "巷道名称")
    private String name;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "长度")
    private BigDecimal length;

    @Schema(description = "宽度")
    private BigDecimal width;

    @Schema(description = "高度")
    private BigDecimal height;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;

    @Schema(description = "最后修改时间")
    private Instant lastModifiedTime;

    @Schema(description = "最后修改人名称")
    private String lastModifiedName;
}
