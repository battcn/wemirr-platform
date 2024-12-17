package com.wemirr.platform.wms.matedata.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 物料配置保存请求数据
 * @author ddCat
 * @since 2024-06-18
 */
@Data
@Schema(name = "MaterialConfigSaveReq", description = "物料配置保存请求数据")
public class MaterialConfigSaveReq {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "物料Id")
    private Long materialId;

    @Schema(description = "批次号管理")
    private Boolean batch;

    @Schema(description = "序列号管理")
    private Boolean serial;

    @Schema(description = "有效期管理")
    private Boolean validityPeriod;

    @Schema(description = "最小库存天数")
    private Integer minStockDay;
}
