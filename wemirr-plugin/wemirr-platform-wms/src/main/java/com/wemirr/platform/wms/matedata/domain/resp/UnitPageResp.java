package com.wemirr.platform.wms.matedata.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * 基本计量单位分页查询响应数据
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@Schema(name = "UnitPageResp", description = "基本计量单位分页查询响应数据")
public class UnitPageResp {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "计量单位中文名称")
    private String name;

    @Schema(description = "计量单位英文名称")
    private String nameEn;

    @Schema(description = "计量单位符号")
    private String symbol;

    @Schema(description = "计量单位类型")
    private String type;

    @Schema(description = "计量单位状态")
    private String status;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;
}
