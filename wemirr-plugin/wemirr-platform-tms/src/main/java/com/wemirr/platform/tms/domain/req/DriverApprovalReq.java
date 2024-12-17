package com.wemirr.platform.tms.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Schema(name = "DriverApprovalReq")
public class DriverApprovalReq {


    @Schema(description = "审批状态")
    private Boolean approvalStatus;

    @Schema(description = "审批描述")
    private String approvalDesc;

}
