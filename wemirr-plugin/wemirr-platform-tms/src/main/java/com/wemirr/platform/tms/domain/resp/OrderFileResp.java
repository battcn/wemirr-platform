package com.wemirr.platform.tms.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Schema(name = "OrderFileResp")
public class OrderFileResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "文件ID")
    private Long fileId;

    @Schema(description = "数据来源")
    private Integer sourceId;

}