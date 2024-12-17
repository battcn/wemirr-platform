package com.wemirr.platform.tms.domain.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "DriverPageReq")
public class DriverPageReq extends PageRequest {


    @Schema(description = "身份证号")
    private String idCardNo;

    @Schema(description = "驾驶证号")
    private String licenseCode;

    @Schema(description = "驾驶证类型")
    private String licenseType;

    @Schema(description = "启用状态")
    private Boolean enabled;

}
