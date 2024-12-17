package com.wemirr.platform.tms.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "TruckPageReq")
public class TruckPageReq extends PageRequest {

    @Schema(description = "启用/禁用")
    private Boolean enabled;

    @Schema(description = "车牌号")
    private String plateNo;

    @Schema(description = "道路运输证号")
    private String roadCertificateCode;

    @Schema(description = "能源类型（租户字典 TMS_ENERGY_TYPE）")
    private String energyType;

}
