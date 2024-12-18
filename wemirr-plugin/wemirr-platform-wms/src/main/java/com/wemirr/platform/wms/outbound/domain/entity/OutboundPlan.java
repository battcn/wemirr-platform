package com.wemirr.platform.wms.outbound.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.wms.inbound.domain.enums.DeliveryMode;
import com.wemirr.platform.wms.outbound.domain.enums.OutboundStatus;
import com.wemirr.platform.wms.outbound.domain.enums.OutboundType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * <p>
 * 出库单
 * </p>
 *
 * @author ddCat
 * @since 2024-07-11
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_outbound_plan")
@Schema(name = "OutboundPlan", description = "出库单")
@EqualsAndHashCode(callSuper = true)
public class OutboundPlan extends SuperEntity<Long> {

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

}
