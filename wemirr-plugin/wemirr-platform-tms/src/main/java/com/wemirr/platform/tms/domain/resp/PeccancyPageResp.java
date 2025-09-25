package com.wemirr.platform.tms.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * @author Levin
 */
@Data
@Schema(name = "PeccancyPageResp")
public class PeccancyPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "违章编号")
    private String peccancyNo;

    @Schema(description = "卡车ID")
    private Long truckId;

    @Schema(description = "车牌号")
    private String plateNo;

    @Schema(description = "驾驶员ID")
    private Long driverId;

    @Schema(description = "驾驶员姓名")
    private String driverName;

    @Schema(description = "违章项目")
    private String peccancyItem;

    @Schema(description = "扣分")
    private Integer pointDeduction;

    @Schema(description = "违章地点")
    private String categoryCityName;

    @Schema(description = "违章地点ID")
    private String categoryCityId;

    @Schema(description = "罚款金额")
    private BigDecimal fineAmount;

    @Schema(description = "公司应付")
    private BigDecimal companyPayable;

    @Schema(description = "司机应付")
    private BigDecimal driverPayable;

    @Schema(description = "违章日期")
    private LocalDate peccancyDate;

    @Schema(description = "费用日期")
    private LocalDate expenseDate;

    @Schema(description = "经办人姓名")
    private String agentName;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建人")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;

}
