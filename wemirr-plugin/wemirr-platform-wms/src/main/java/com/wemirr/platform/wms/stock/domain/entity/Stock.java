package com.wemirr.platform.wms.stock.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.wms.inbound.domain.enums.StockLevelStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 库存余额表
 * </p>
 *
 * @author ddCat
 * @since 2024-07-02
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_stock")
@Schema(name = "Stock", description = "库存余额")
@EqualsAndHashCode(callSuper = true)
public class Stock extends SuperEntity<Long> {

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

    @Schema(description = "可用库存数量")
    private BigDecimal availableQty;

    @Schema(description = "库存数量")
    private BigDecimal qty;

    @Schema(description = "库存单位")
    private String unit;

    @Schema(description = "特殊属性")
    private String attribute;

    @Schema(description = "物料存货等级(良品、待检品、不良品、返工品、报废品、退货品)")
    private StockLevelStatus level;

    @Schema(description = "单价")
    private BigDecimal unitPrice;

}
