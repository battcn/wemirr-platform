package com.wemirr.platform.authority.domain.tenant.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "TenantPageReq")
public class TenantPageReq extends PageRequest {

    @Schema(description = "名称")
    private String name;
    @Schema(description = "编码")
    private String code;
    @Schema(description = "类型")
    private Integer type;
    @Schema(description = "状态")
    private Integer status;
    @Schema(description = "行业")
    private Integer industry;

    @Schema(description = "省")
    private Long provinceId;
    @Schema(description = "市")
    private Long cityId;
    @Schema(description = "区")
    private Long districtId;
}
