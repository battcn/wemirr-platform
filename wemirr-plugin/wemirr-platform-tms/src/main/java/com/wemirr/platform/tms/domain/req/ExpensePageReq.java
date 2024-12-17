package com.wemirr.platform.tms.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "ExpensePageReq")
public class ExpensePageReq extends PageRequest {

    @Schema(description = "费用编号")
    private String expenseNo;

    @Schema(description = "费用项目")
    private String expenseItem;

    @Schema(description = "车牌号")
    private String plateNo;


}
