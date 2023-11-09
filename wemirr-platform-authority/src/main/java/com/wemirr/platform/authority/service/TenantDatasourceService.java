package com.wemirr.platform.authority.service;

import com.wemirr.framework.db.dynamic.core.EventAction;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.tenant.entity.DynamicDatasource;
import com.wemirr.platform.authority.domain.tenant.resp.TenantDatasourceResp;

import java.util.List;

/**
 * @author Levvin
 */
public interface TenantDatasourceService extends SuperService<DynamicDatasource> {

    /**
     * 查询所有可用的动态数据源
     *
     * @return 查询结果
     */
    List<TenantDatasourceResp> selectTenantDynamicDatasource();

    /**
     * ping 数据源
     *
     * @param id id
     */
    void ping(Long id);

    /**
     * 添加或者保存动态数据源信息
     *
     * @param dynamicDatasource dynamicDatasource
     */
    void saveOrUpdateDatabase(DynamicDatasource dynamicDatasource);

    /**
     * 删除数据源
     *
     * @param id id
     */
    void removeDatabaseById(Long id);

    /**
     * 发布数据源事件
     *
     * @param action   动作
     * @param tenantId 租户ID
     */
    void publishEvent(EventAction action, Long tenantId);
}
