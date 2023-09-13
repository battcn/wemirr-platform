package com.wemirr.platform.authority.service;

import com.wemirr.framework.db.configuration.dynamic.event.body.EventAction;
import com.wemirr.framework.db.mybatis.SuperService;
import com.wemirr.platform.authority.domain.entity.tenant.DynamicDatasource;
import com.wemirr.platform.authority.domain.resp.TenantDynamicDatasourceResp;

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
    List<TenantDynamicDatasourceResp> selectTenantDynamicDatasource();

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
