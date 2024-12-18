package com.wemirr.platform.wms.inbound.domain.req;

import com.wemirr.framework.redis.plus.anontation.RedisParam;
import com.wemirr.platform.wms.inbound.domain.enums.ArrivalStatus;
import com.wemirr.platform.wms.inbound.domain.enums.ReceivingPlanType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * 收货计划保存请求数据
 *
 * @author ddCat
 * @since 2024-06-24
 */
@Data
@Schema(name = "ReceivingPlanSaveReq", description = "收货计划保存请求数据")
public class ReceivingPlanSaveReq {

    @Schema(description = "收货计划编号")
    private String planNum;

    @Schema(description = "到货通知单编号")
    private String receivingNoticeNum;

    @RedisParam
    @Schema(description = "仓库ID")
    private Long warehouseId;

    @RedisParam
    @Schema(description = "月台ID")
    private Long dockId;

    @RedisParam
    @Schema(description = "容器ID")
    private Long containerId;

    @RedisParam
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

    @Schema(description = "送货车辆车牌号")
    private String deliveryCarNumber;

    @Schema(description = "送货司机")
    private String deliveryDriver;

    @Schema(description = "司机联系方式")
    private String driverContact;

    @Schema(description = "备注")
    private String remark;

    @NotEmpty(message = "收货计划明细不能为空")
    @Schema(description = "收货计划明细行")
    private List<ReceivingPlanItemSaveReq> items;
}
