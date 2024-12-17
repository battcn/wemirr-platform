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
@Schema(name = "PartPurchasePageReq")
public class PartPurchasePageReq extends PageRequest {


    @Schema(description = "采购编号")
    private String purchaseNo;

    @Schema(description = "零件名称")
    private String partName;



}
