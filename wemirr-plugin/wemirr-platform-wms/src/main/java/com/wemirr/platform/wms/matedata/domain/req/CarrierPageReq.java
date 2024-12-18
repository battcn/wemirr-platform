package com.wemirr.platform.wms.matedata.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.wms.inbound.domain.enums.CarrierType;
import com.wemirr.platform.wms.inbound.domain.enums.ServiceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 承运商分页查询请求数据
 *
 * @author ddCat
 * @since 2024-07-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "CarrierPageReq", description = "承运商分页查询请求数据")
public class CarrierPageReq extends PageRequest {

    @Schema(description = "承运商编码")
    private String code;

    @Schema(description = "承运商名称")
    private String name;

    @Schema(description = "承运商类型：外贸、内贸")
    private CarrierType type;

    @Schema(description = "服务类型：陆运、海运、空运")
    private ServiceType serviceType;

    @Schema(description = "状态")
    private String status;
}
