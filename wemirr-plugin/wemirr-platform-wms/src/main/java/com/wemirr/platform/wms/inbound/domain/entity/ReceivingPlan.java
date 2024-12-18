package com.wemirr.platform.wms.inbound.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.wms.inbound.domain.enums.ArrivalStatus;
import com.wemirr.platform.wms.inbound.domain.enums.ReceivingPlanType;
import com.wemirr.platform.wms.inbound.domain.enums.ReceivingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * <p>
 * 收货计划
 * </p>
 *
 * @author ddCat
 * @since 2024-06-23
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_receiving_plan")
@Schema(name = "ReceivingPlan", description = "收货计划")
@EqualsAndHashCode(callSuper = true)
public class ReceivingPlan extends SuperEntity<Long> {

    @Schema(description = "收货计划编号")
    private String planNum;

    @Schema(description = "到货通知单编号")
    private String receivingNoticeNum;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "月台ID")
    private Long dockId;

    @Schema(description = "容器ID")
    private Long containerId;

    @Schema(description = "供应商ID")
    private Long supplierId;

    @Schema(description = "类型")
    private ReceivingPlanType type;

    @Schema(description = "来源")
    private String source;

    @Schema(description = "发货日期")
    private LocalDate deliveryDate;

    @Schema(description = "预计到货时间")
    private LocalDate expectedArrivalTime;

    @Schema(description = "实际到货时间")
    private LocalDate actualArrivalTime;

    @Schema(description = "到货状态")
    private ArrivalStatus arrivalStatus;

    @Schema(description = "状态")
    private ReceivingStatus status;

    @Schema(description = "送货车辆车牌号")
    private String deliveryCarNumber;

    @Schema(description = "送货司机")
    private String deliveryDriver;

    @Schema(description = "司机联系方式")
    private String driverContact;

    @Schema(description = "备注")
    private String remark;

}
