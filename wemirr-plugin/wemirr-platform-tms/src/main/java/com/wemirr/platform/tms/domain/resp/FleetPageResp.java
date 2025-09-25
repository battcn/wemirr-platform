package com.wemirr.platform.tms.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
@Schema(name = "FleetPageResp")
public class FleetPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "车队名称")
    private String fleetName;

    @Schema(description = "队长姓名")
    private String leaderRealName;

    @Schema(description = "队长手机号")
    private String leaderMobile;

    @Schema(description = "队长用户ID")
    private Long leaderUserId;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建人")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;


}
