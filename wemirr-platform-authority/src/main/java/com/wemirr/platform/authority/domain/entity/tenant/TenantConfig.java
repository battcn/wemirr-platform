package com.wemirr.platform.authority.domain.entity.tenant;


import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
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

    private Long tenantId;
    private Long dynamicDatasourceId;

}
