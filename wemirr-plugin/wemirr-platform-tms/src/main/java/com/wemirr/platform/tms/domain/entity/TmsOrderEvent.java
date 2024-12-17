package com.wemirr.platform.tms.domain.entity;

import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * tms_order_event
 *
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class TmsOrderEvent extends SuperEntity<Long> {

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "平台单号")
    private String orderNo;

    @Schema(description = "省份")
    private String province;

    @Schema(description = "城市")
    private String city;

    @Schema(description = "区县")
    private String district;

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

    @Schema(description = "记录时间")
    private Instant recordTime;

    @Schema(description = "节点状态ID")
    private Integer eventType;

    @Schema(description = "操作人员ID")
    private Long operatorId;

    @Schema(description = "节点备注")
    private String remark;

    @Schema(description = "总数量")
    private Integer quantity;

    @Schema(description = "毛重")
    private BigDecimal grossWeight;

    @Schema(description = "体积")
    private BigDecimal volume;

    @Schema(description = "订单货物ID")
    private Long skuId;

    @Schema(description = "站点ID")
    private Long stationId;

    @Schema(description = "任务ID")
    private Long taskId;

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

    @Schema(description = "数据来源")
    private Integer sourceId;

    @Schema(description = "是否启用 0=未锁定 1=锁定(逻辑删除用)")
    private Boolean locked;

}