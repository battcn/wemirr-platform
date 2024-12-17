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
@Schema(name = "DockPageReq")
public class DockPageReq extends PageRequest {

    @Schema(description = "月台编号")
    private String code;

    @Schema(description = "月台名称")
    private String name;

    @Schema(description = "状态")
    private String status;

}
