package com.wemirr.platform.wms.matedata.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * 物料配置分页查询响应数据
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@Schema(name = "MaterialConfigResp", description = "物料配置分页查询响应数据")
public class MaterialConfigPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "物料编号")
    private String materialCode;

    @Schema(description = "批次号管理")
    private Boolean batch;

    @Schema(description = "序列号管理")
    private Boolean serial;

    @Schema(description = "有效期管理")
    private Boolean validityPeriod;

    @Schema(description = "最小库存天数")
    private Integer minStockDay;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;
}
