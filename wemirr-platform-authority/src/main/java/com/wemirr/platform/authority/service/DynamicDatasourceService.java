package com.wemirr.platform.authority.service;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.platform.authority.domain.entity.tenant.DynamicDatasource;
import com.wemirr.platform.authority.domain.vo.TenantDynamicDatasourceVO;

import java.util.List;

/**
 * @author Levvin
 */
public interface DynamicDatasourceService extends SuperService<DynamicDatasource> {

    /**
     * 查询所有可用的动态数据源
     *
     * @return 查询结果
     */
    List<TenantDynamicDatasourceVO> selectTenantDynamicDatasource();

    void ping(Long id);

    void saveOrUpdateDatabase(DynamicDatasource dynamicDatasource);

    void removeDatabaseById(Long id);

    DynamicRoutingDataSource configDataSource(Long tenantId);
}
