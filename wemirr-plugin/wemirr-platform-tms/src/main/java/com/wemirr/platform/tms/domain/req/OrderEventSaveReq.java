package com.wemirr.platform.tms.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * @author Levin
 */
@Data
@Schema(name = "OrderEventSaveReq" )
public class OrderEventSaveReq {

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "平台单号")
    private String systemNo;

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

    @Schema(description = "附件照片")
    private List<OrderEventFileSaveReq> files;

}