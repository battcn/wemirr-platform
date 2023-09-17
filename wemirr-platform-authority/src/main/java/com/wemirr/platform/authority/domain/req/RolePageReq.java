package com.wemirr.platform.authority.domain.req;

import com.wemirr.framework.db.mybatis.auth.DataScopeType;
import com.wemirr.framework.db.page.PageRequest;
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


    private String name;
    private Boolean locked;
    private DataScopeType scopeType;

}
