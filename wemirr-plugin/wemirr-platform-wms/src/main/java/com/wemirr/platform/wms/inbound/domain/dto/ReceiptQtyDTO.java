package com.wemirr.platform.wms.inbound.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 根据收货计划关联入库单查询数量
 *
 * @author ddCat
 * @since 2024-06-27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptQtyDTO {

    @Schema(description = "收货计划行项ID")
    private Long planItemId;

    @Builder.Default
    @Schema(description = "收货进行中的数量")
    private BigDecimal progressQty = BigDecimal.ZERO;

    @Builder.Default
    @Schema(description = "收货已完成的数量")
    private BigDecimal completeQty = BigDecimal.ZERO;

}
