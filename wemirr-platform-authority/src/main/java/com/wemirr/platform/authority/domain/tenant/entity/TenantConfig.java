package com.wemirr.platform.authority.domain.tenant.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 租户配置信息
 * </p>
 *
 * @author Levin
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("t_tenant_config")
public class TenantConfig extends SuperEntity<Long> {

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "数据源ID")
    private Long datasourceId;

    @Schema(description = "配置原因")
    private String description;

    @Schema(description = "是否延迟加载")
    private Boolean lazy;

}
