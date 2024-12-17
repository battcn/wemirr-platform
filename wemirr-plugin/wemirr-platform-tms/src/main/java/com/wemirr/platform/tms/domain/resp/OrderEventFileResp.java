package com.wemirr.platform.tms.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Schema(name = "OrderEventFileResp")
public class OrderEventFileResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "订单节点ID")
    private Long eventId;

    @Schema(description = "文件ID")
    private Long fileId;

    @Schema(description = "数据来源")
    private Integer sourceId;

}