package com.wemirr.platform.wms.inbound.domain.resp;

import com.wemirr.platform.wms.inbound.domain.enums.ArrivalStatus;
import com.wemirr.platform.wms.inbound.domain.enums.ReceivingPlanType;
import com.wemirr.platform.wms.inbound.domain.enums.ReceivingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

/**
 * 收货计划分页查询响应数据
 *
 * @author ddCat
 * @since 2024-06-24
 */
@Data
@Schema(name = "ReceivingPlanPageResp", description = "收货计划分页查询响应数据")
public class ReceivingPlanPageResp {

    @Schema(description = "主键")
    private Long id;

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

    @Schema(description = "创建人")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;

}
