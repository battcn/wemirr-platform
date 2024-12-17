package com.wemirr.platform.tms.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Levin
 */
@Data
@Schema(name = "ExpenseSaveReq")
public class ExpenseSaveReq {

    @Schema(description = "费用项目")
    private String expenseItem;

    @Schema(description = "卡车ID")
    private Long truckId;

    @Schema(description = "车牌号")
    private String plateNo;

    @Schema(description = "到期日期")
    private LocalDate expiryDate;

    @Schema(description = "金额")
    private BigDecimal amount;

    @Schema(description = "办理日期")
    private LocalDate handlingDate;

    @Schema(description = "经办人姓名")
    private String agentName;

    @Schema(description = "备注")
    private String remark;



}
