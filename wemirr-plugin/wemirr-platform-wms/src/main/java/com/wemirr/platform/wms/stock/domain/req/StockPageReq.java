package com.wemirr.platform.wms.stock.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.wms.inbound.domain.enums.StockLevelStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;


/**
 * 库存余额分页查询请求参数
 *
 * @author ddCat
 * @since 2024-07-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "StockPageReq", description = "库存余额分页查询请求参数")
public class StockPageReq extends PageRequest {

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "储位ID")
    private Long locationId;

    @Schema(description = "物料ID")
    private Long materialId;

    @Schema(description = "批次号")
    private String batchNum;

    @Schema(description = "生产日期")
    private LocalDate productionDate;

    @Schema(description = "失效日期")
    private LocalDate expiryDate;

    @Schema(description = "物料存货等级(良品、待检品、不良品、返工品、报废品、退货品)")
    private StockLevelStatus level;
}
