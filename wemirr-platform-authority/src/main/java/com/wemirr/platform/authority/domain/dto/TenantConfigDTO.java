package com.wemirr.platform.authority.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Levin
 */
@Data
public class TenantConfigDTO {

    @NotNull(message = "动态数据源ID不能为空")
    private Long dynamicDatasourceId;

}
