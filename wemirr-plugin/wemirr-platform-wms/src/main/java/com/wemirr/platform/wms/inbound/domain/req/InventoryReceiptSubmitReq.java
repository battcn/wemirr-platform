package com.wemirr.platform.wms.inbound.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 入库单保存请求数据
 *
 * @author ddCat
 * @since 2024-06-27
 */
@Data
@Schema(name = "InventoryReceiptSubmitReq", description = "入库单保存请求数据")
public class InventoryReceiptSubmitReq {

    @Schema(description = "订单ID")
    private Long id;


    @Schema(description = "入库单明细行保存请求数据")
    private List<InventoryReceiptItemSubmitReq> items;

    @Data
    public static class InventoryReceiptItemSubmitReq {

        @Schema(description = "ID")
        private Long id;

        @Schema(description = "储位ID")
        private Long locationId;
    }
}
