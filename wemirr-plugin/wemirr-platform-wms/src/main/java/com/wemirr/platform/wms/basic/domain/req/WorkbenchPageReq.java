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
@Schema(name = "WorkbenchPageReq")
public class WorkbenchPageReq extends PageRequest {


    @Schema(description = "编码")
    private String code;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "状态")
    private String status;

}
