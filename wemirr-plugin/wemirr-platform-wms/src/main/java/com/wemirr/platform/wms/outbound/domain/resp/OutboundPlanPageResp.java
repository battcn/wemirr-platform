package com.wemirr.platform.wms.outbound.domain.resp;

import com.wemirr.platform.wms.inbound.domain.enums.DeliveryMode;
import com.wemirr.platform.wms.outbound.domain.enums.OutboundStatus;
import com.wemirr.platform.wms.outbound.domain.enums.OutboundType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

/**
 * 出库单分页查询响应数据
 *
 * @author ddCat
 * @since 2024-07-20
 */
@Data
@Schema(name = "OutboundPlanPageResp", description = "出库单分页查询响应数据")
public class OutboundPlanPageResp {

    @Schema(description = "主键")
    private Long id;

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

    @Schema(description = "运单号或车辆车牌号")
    private String deliveryCarNumber;

    @Schema(description = "运输人")
    private String deliveryDriver;

    @Schema(description = "司机联系方式")
    private String driverContact;

    @Schema(description = "出库单类型")
    private OutboundType type;

    @Schema(description = "客户订单号")
    private String customerOrderNum;

    @Schema(description = "收货人")
    private String deliveryReceiver;

    @Schema(description = "收货人电话")
    private String deliveryReceiverPhone;

    @Schema(description = "收货人地址")
    private String deliveryReceiverAddress;

    @Schema(description = "送货方式")
    private DeliveryMode deliveryMode;

    @Schema(description = "状态")
    private OutboundStatus status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;
}
