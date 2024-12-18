package com.wemirr.platform.wms.outbound.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.wms.inbound.domain.enums.DeliveryMode;
import com.wemirr.platform.wms.outbound.domain.enums.OutboundStatus;
import com.wemirr.platform.wms.outbound.domain.enums.OutboundType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 出库单分页查询请求数据
 *
 * @author ddCat
 * @since 2024-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "OutboundPlanPageReq", description = "出库单分页查询请求数据")
public class OutboundPlanPageReq extends PageRequest {

    @Schema(description = "出库单编号")
    private String planNum;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    // 发货月台
    @Schema(description = "月台ID")
    private Long dockId;

    @Schema(description = "供应商ID")
    private Long supplierId;

    @Schema(description = "承运商ID")
    private Long carrierId;

    @Schema(description = "计划发货日期")
    private LocalDate deliveryDate;

    @Schema(description = "出库单类型")
    private OutboundType type;

    @Schema(description = "客户订单号")
    private String customerOrderNum;

    @Schema(description = "送货方式")
    private DeliveryMode deliveryMode;

    @Schema(description = "状态")
    private OutboundStatus status;
}
