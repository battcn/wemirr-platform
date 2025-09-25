package com.wemirr.platform.wms.basic.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
public class DockPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "月台编号")
    private String code;

    @Schema(description = "月台名称")
    private String name;

    @Schema(description = "月台规格")
    private String spec;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "创建人名称")
    private String createName;

    @Schema(description = "创建时间")
    private Instant createTime;

}
