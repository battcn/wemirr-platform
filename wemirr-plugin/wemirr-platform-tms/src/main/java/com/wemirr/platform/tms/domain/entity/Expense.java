package com.wemirr.platform.tms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@TableName(value = "tms_expense")
@Schema(name = "Expense")
public class Expense extends SuperEntity<Long> {


    @Schema(description = "费用编号")
    private String expenseNo;

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
