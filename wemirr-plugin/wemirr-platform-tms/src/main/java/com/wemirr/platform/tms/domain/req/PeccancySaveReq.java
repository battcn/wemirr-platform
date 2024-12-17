package com.wemirr.platform.tms.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Levin
 */
@Data
@Schema(name = "PeccancySaveReq", description = "车辆违章")
public class PeccancySaveReq {

    @Schema(description = "违章编号")
    private String peccancyNo;

    @Schema(description = "车辆ID")
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

}
