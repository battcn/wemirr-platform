package com.wemirr.platform.tms.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Levin
 */
@Data
@Schema(name = "BmsDetailCalcReq" )
public class BmsDetailCalcReq {

    @Schema(description = "项目id")
    private Long projectId;

    @Schema(description = "类型:1-应收,0-应付")
    private Integer paymentType;

    @Schema(description = "时间-起始")
    private LocalDate startDate;

    @Schema(description = "时间-截至")
    private LocalDate endDate;

    @Schema(description = "订单ids")
    private List<Long> orderIds;

}