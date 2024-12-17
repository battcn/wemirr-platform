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
 * tms_order
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class TmsOrder extends SuperEntity<Long> {

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "客户单号")
    private String customNo;

    @Schema(description = "运输单号")
    private String waybillNo;

    @Schema(description = "项目id")
    private Long projectId;

    @Schema(description = "项目名称")
    private String projectName;

    @Schema(description = "单位")
    private String qtyUnit;


    @Schema(description = "运输方式")
    private Integer transportType;

    @Schema(description = "货物信息")
    private String cargoDesc;

    @Schema(description = "总数量")
    private Integer quantity;

    @Schema(description = "订单明细数量")
    private Integer detailQuantity;

    @Schema(description = "毛重")
    private BigDecimal grossWeight;

    @Schema(description = "体积")
    private BigDecimal volume;

    @Schema(description = "确认数量")
    private Integer checkQuantity;

    @Schema(description = "确认重量")
    private BigDecimal checkWeight;

    @Schema(description = "确认体积")
    private BigDecimal checkVolume;

    @Schema(description = "声明价值")
    private BigDecimal declareValue;

    @Schema(description = "预计发货时间")
    private Instant etd;

    @Schema(description = "实际发货时间")
    private Instant atd;

    @Schema(description = "预计到达时间")
    private Instant eta;

    @Schema(description = "实际到达时间")
    private Instant ata;

    @Schema(description = "提货时间")
    private Instant pickTime;

    @Schema(description = "签收时间")
    private Instant podTime;

    @Schema(description = "最后一个节点类型")
    private Integer latestEventType;

    @Schema(description = "最后一个节点id")
    private Long latestEventId;

    @Schema(description = "订单状态:-10取消,0新建,10提货,20到达始发站,30离开始发站,40到达目的站,50离开目的站,60签收,70回单,80完成")
    private Integer orderStatus;

    @Schema(description = "订单备注")
    private String remark;

    @Schema(description = "运输线路id")
    private Long transportLineId;

    @Schema(description = "是否有电子回单")
    private Boolean hasEpod;

    @Schema(description = "数据来源")
    private Integer sourceType;

}