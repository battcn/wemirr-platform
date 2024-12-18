package com.wemirr.platform.wms.inbound.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.wms.inbound.domain.enums.InventoryReceiptStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 入库单
 * </p>
 *
 * @author ddCat
 * @since 2024-06-25
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_inventory_receipt")
@Schema(name = "InventoryReceipt", description = "入库单")
@EqualsAndHashCode(callSuper = true)
public class InventoryReceipt extends SuperEntity<Long> {

    @Schema(description = "入库单编号")
    private String receiptNum;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "供应商ID")
    private Long supplierId;

    @Schema(description = "关联的收货计划ID")
    private Long planId;

    @Schema(description = "收货计划id")
    private String planNum;

    @Schema(description = "状态")
    private InventoryReceiptStatus status;

    @Schema(description = "备注")
    private String remark;

}
