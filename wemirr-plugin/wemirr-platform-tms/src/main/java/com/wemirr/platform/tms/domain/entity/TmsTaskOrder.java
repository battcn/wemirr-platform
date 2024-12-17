package com.wemirr.platform.tms.domain.entity;

import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * tms_task_order
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class TmsTaskOrder extends SuperEntity<Long> {

    @Schema(description = "调度计划ID")
    private Long taskId;

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "数据来源")
    private Integer sourceId;

    @Schema(description = "是否启用 0=未锁定 1=锁定(逻辑删除用)")
    private Boolean locked;

}