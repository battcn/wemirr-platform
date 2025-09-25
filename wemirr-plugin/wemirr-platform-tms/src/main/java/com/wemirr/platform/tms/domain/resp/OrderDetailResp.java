package com.wemirr.platform.tms.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * @author Levin
 */
@Data
@Schema(name = "OrderPageResp")
public class OrderDetailResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "客户单号")
    private String customNo;

    @Schema(description = "平台顶单号")
    private String orderNo;

    @Schema(description = "运输单号")
    private String waybillNo;

    @Schema(description = "项目id")
    private Long projectId;

    @Schema(description = "项目名称")
    private String projectName;

    @Schema(description = "运输方式")
    private String transportType;

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

    @Schema(description = "单位")
    private String qtyUnit;

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

    @Schema(description = "操作人员ID")
    private Integer operatorId;

    @Schema(description = "运输线路id")
    private Integer transportLineId;

    @Schema(description = "是否有电子回单")
    private Boolean hasEpod;

    @Schema(description = "数据来源")
    private Integer sourceId;

    @Schema(description = "发货信息")
    private OrderAddressResp senderInfo;

    @Schema(description = "收货信息")
    private OrderAddressResp receiverInfo;

    @Schema(description = "货品信息")
    private List<OrderSkuResp> skus;

    @Schema(description = "文件信息")
    private List<OrderFileResp> files;

    @Schema(description = "创建人")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;


}