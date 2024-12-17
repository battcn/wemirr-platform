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
@Schema(name = "AccidentPageReq", description = "车辆事故查询")
public class AccidentPageReq extends PageRequest {


    @Schema(description = "事故编号")
    private String accidentNo;

    @Schema(description = "车牌号")
    private String plateNo;

    @Schema(description = "驾驶员姓名")
    private String driverName;


}
