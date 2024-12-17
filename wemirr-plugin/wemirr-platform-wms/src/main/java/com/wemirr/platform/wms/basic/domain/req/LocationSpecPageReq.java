package com.wemirr.platform.wms.basic.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "LocationSpecPageReq")
public class LocationSpecPageReq extends PageRequest {

    @Schema(description = "储位规格编号")
    private String specCode;

    @Schema(description = "储位规格名称")
    private String specName;

    @Schema(description = "状态")
    private String status;

}
