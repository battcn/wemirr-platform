package com.wemirr.platform.tms.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Schema(name = "OrderEventFileSaveReq")
public class OrderEventFileSaveReq {

    @Schema(description = "节点ID")
    private Long eventId;

    @Schema(description = "文件ID")
    private Long fileId;

    @Schema(description = "数据来源")
    private Integer sourceId;

}