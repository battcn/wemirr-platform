package com.wemirr.platform.tms.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Schema(name = "FleetSaveReq")
public class FleetSaveReq {

    @Schema(description = "车队名称")
    private String fleetName;

    @Schema(description = "队长姓名")
    private String leaderRealName;

    @Schema(description = "队长手机号")
    private String leaderMobile;

    @Schema(description = "队长用户ID")
    private String leaderUserId;

    @Schema(description = "备注")
    private String remark;


}
