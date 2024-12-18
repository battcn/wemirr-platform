package com.wemirr.platform.wms.stock.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.wms.inbound.domain.enums.StockChangeType;
import com.wemirr.platform.wms.inbound.domain.enums.StockLevelStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;


/**
 * 库存余额变动分页查询请求参数
 *
 * @author ddCat
 * @since 2024-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "StockChangePageReq", description = "库存余额变动分页查询请求参数")
public class StockChangePageReq extends PageRequest {

    @Schema(description = "库存余额ID")
    private Long stockId;

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

    @Schema(description = "库存变动类型(入库、出库、盘点、移库)")
    private StockChangeType changeType;
}
