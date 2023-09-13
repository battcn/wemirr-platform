package com.wemirr.platform.authority.domain.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Levin
 */
@Data
public class TenantConfigReq {

    @NotNull(message = "动态数据源ID不能为空")
    private Long dynamicDatasourceId;

}
