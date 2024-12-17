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
@Schema(name = "PeccancyPageReq", description = "车辆违章查询")
public class PeccancyPageReq extends PageRequest {

    @Schema(description = "违章编号")
    private String peccancyNo;

    @Schema(description = "车牌号")
    private String plateNo;


    @Schema(description = "违章项目")
    private String peccancyItem;

}
