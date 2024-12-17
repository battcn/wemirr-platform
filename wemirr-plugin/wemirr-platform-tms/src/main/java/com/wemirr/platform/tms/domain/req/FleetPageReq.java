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
@Schema(name = "FleetPageReq")
public class FleetPageReq extends PageRequest {


    @Schema(description = "车队名称")
    private String fleetName;

    @Schema(description = "队长姓名")
    private String leaderRealName;

    @Schema(description = "队长手机号")
    private String leaderMobile;

}
