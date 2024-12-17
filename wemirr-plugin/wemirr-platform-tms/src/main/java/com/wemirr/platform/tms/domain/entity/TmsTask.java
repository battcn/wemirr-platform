package com.wemirr.platform.tms.domain.entity;

import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * tms_task
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class TmsTask extends SuperEntity<Long> {

    @Schema(description = "调度计划名称")
    private String taskName;

    @Schema(description = "调度号")
    private String taskNo;

    @Schema(description = "项目id")
    private Long projectId;

    @Schema(description = "运输方式")
    private Integer transportType;

    @Schema(description = "车辆类型ID")
    private Integer truckType;

    @Schema(description = "预计提货时间")
    private Instant etd;

    @Schema(description = "实际提货时间")
    private Instant atd;

    @Schema(description = "预计到达时间")
    private Instant eta;

    @Schema(description = "实际到达时间")
    private Instant ata;

    @Schema(description = "始发站点ID")
    private Long stationFrom;

    @Schema(description = "目的站点ID")
    private Long stationTo;

    @Schema(description = "司机ID")
    private Long driverId;

    @Schema(description = "司机姓名")
    private String driverName;

    @Schema(description = "司机手机号")
    private String driverMobile;

    @Schema(description = "车辆id")
    private Long truckId;

    @Schema(description = "车牌号")
    private String plateNo;

    @Schema(description = "任务备注")
    private String remark;

    @Schema(description = "订单状态:-10取消,0新建,10出发,20运输中,30到达,40完成")
    private Integer taskStatus;

    @Schema(description = "数据来源")
    private Integer sourceId;

    @Schema(description = "是否启用 0=未锁定 1=锁定(逻辑删除用)")
    private Boolean locked;

}