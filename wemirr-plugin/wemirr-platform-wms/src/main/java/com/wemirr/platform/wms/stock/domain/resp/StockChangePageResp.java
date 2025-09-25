package com.wemirr.platform.wms.stock.domain.resp;

import com.wemirr.platform.wms.inbound.domain.enums.StockChangeType;
import com.wemirr.platform.wms.inbound.domain.enums.StockLevelStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * 库存余额变动分页查询响应数据
 *
 * @author ddCat
 * @since 2024-07-08
 */
@Data
@Schema(name = "StockChangePageResp", description = "库存余额分页变动查询响应数据")
public class StockChangePageResp {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "库存余额ID")
    private Long stockId;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "储位ID")
    private Long locationId;

    @Schema(description = "物料ID")
    private Long materialId;

    @Schema(description = "单据ID")
    private Long docId;

    @Schema(description = "单据行ID")
    private Long docItemId;

    @Schema(description = "批次号")
    private String batchNum;

    @Schema(description = "生产日期")
    private LocalDate productionDate;

    @Schema(description = "失效日期")
    private LocalDate expiryDate;

    @Schema(description = "变动数量")
    private BigDecimal qty;

    @Schema(description = "库存单位")
    private String unit;

    @Schema(description = "特殊属性")
    private String attribute;

    @Schema(description = "物料存货等级(良品、待检品、不良品、返工品、报废品、退货品)")
    private StockLevelStatus level;

    @Schema(description = "单价")
    private BigDecimal unitPrice;

    @Schema(description = "库存变动类型(入库、出库、盘点、移库)")
    private StockChangeType changeType;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;
}
