package com.wemirr.platform.wms.inbound.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收货计划绑定容器和月台请求数据
 *
 * @author ddCat
 * @since 2024-08-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "ReceivingPlanBindingReq", description = "收货计划绑定容器和月台请求数据")
public class ReceivingPlanBindingReq extends PageRequest {

    @Schema(description = "月台ID")
    private Long dockId;

    @Schema(description = "容器ID")
    private Long containerId;

}
