package com.wemirr.platform.tms.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "OrderPageReq")
public class OrderPageReq extends PageRequest {

    @Schema(description = "项目Id")
    private Long projectId;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "客户单号")
    private String customNo;

    @Schema(description = "数据来源")
    private Integer sourceType;

    @Schema(description = "运单号")
    private String waybillNo;

    @Schema(description = "运输方式:1-零担,2-整车,3-铁运,4-空运,5-快递")
    private Integer transportType;

    @Schema(description = "时间-类型:1-下单时间,2-预计提货时间,3-预计签收时间,4-实际提货时间,5-实际签收时间")
    private Integer dateType;

    @Schema(description = "时间-起始")
    private LocalDate startDate;

    @Schema(description = "时间-截至")
    private LocalDate endDate;

    @Schema(description = "订单状态:-10取消,0新建,10提货,20到达始发站,30离开始发站,40到达目的站,50离开目的站,60签收,70回单,80完成")
    private List<Integer> orderStatus;

    @Schema(description = "是否有电子回单")
    private Boolean hasEpod;

    @Schema(description = "创建人")
    private Long createBy;

}
