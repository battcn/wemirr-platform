package com.wemirr.platform.authority.domain.baseinfo.req;

import com.wemirr.framework.commons.security.DataScopeType;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "RolePageReq")
public class RolePageReq extends PageRequest {


    @Schema(description = "名称")
    private String name;
    @Schema(description = "是否禁用")
    private Boolean locked;

    @Schema(description = "数据类型范围")
    private DataScopeType scopeType;

}
