package com.wemirr.platform.authority.repository.tenant;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.tenant.entity.TenantConfig;
import com.wemirr.platform.authority.domain.tenant.resp.TenantDatasourceResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Repository
public interface TenantConfigMapper extends SuperMapper<TenantConfig> {

    /**
     * 查询所有可用的动态数据源
     *
     * @param datasourceId datasourceId
     * @return 查询结果
     */
    List<TenantDatasourceResp> selectTenantDynamicDatasource(@Param("datasourceId") Long datasourceId);

    /**
     * 获取租户动态数据源
     *
     * @param tenantId tenantId
     * @return 查询结果
     */
    TenantDatasourceResp getTenantDynamicDatasourceByTenantId(Long tenantId);

}
