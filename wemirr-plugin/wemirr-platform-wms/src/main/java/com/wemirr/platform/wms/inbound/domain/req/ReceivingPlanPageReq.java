package com.wemirr.platform.wms.inbound.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.wms.inbound.domain.enums.ArrivalStatus;
import com.wemirr.platform.wms.inbound.domain.enums.ReceivingPlanType;
import com.wemirr.platform.wms.inbound.domain.enums.ReceivingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收货计划分页查询请求数据
 * @author ddCat
 * @since 2024-06-17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "ReceivingPlanPageReq", description = "收货计划分页查询请求数据")
public class ReceivingPlanPageReq extends PageRequest {

    @Schema(description = "收货计划编号")
    private String planNum;

    @Schema(description = "到货通知单编号")
    private String receivingNoticeNum;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "月台ID")
    private Long dockId;

    @Schema(description = "容器ID")
    private Long containerId;

    @Schema(description = "供应商ID")
    private Long supplierId;

    @Schema(description = "类型")
    private ReceivingPlanType type;

    @Schema(description = "来源")
    private String source;

    @Schema(description = "到货状态")
    private ArrivalStatus arrivalStatus;

    @Schema(description = "状态")
    private ReceivingStatus status;
}
