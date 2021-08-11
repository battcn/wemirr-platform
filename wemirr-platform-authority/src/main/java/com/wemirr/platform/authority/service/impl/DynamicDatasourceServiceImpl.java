package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.database.configuration.dynamic.event.DynamicDatasourceEvent;
import com.wemirr.framework.database.configuration.dynamic.event.body.EventAction;
import com.wemirr.framework.database.configuration.dynamic.event.body.TenantDynamicDatasource;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.framework.database.mybatis.conditions.query.LbqWrapper;
import com.wemirr.platform.authority.domain.entity.tenant.DynamicDatasource;
import com.wemirr.platform.authority.domain.vo.TenantDynamicDatasourceVO;
import com.wemirr.platform.authority.repository.DynamicDatasourceMapper;
import com.wemirr.platform.authority.repository.TenantConfigMapper;
import com.wemirr.platform.authority.repository.TenantMapper;
import com.wemirr.platform.authority.service.DynamicDatasourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.wemirr.platform.authority.domain.converts.TenantDynamicDatasourceConverts.TENANT_DYNAMIC_DATASOURCE_VO_2_TENANT_DYNAMIC_DATASOURCE_CONVERTS;


/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DynamicDatasourceServiceImpl extends SuperServiceImpl<DynamicDatasourceMapper, DynamicDatasource> implements DynamicDatasourceService {

    private final TenantConfigMapper tenantConfigMapper;
    private final TenantMapper tenantMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final BusProperties busProperties;

    @Override
    public List<TenantDynamicDatasourceVO> selectTenantDynamicDatasource() {
        return this.tenantConfigMapper.selectTenantDynamicDatasource(null);
    }

    @Override
    public void ping(Long id) {
        log.debug("查询结果 - {}", JSON.toJSONString(""));
    }

    @PostConstruct
    public void init() {
        final List<TenantDynamicDatasourceVO> dataSourceList = this.tenantConfigMapper.selectTenantDynamicDatasource(null);
        if (CollectionUtil.isEmpty(dataSourceList)) {
            log.warn("未找到符合条件的数据源...");
            return;
        }
        for (TenantDynamicDatasourceVO dynamicDatasource : dataSourceList) {
            publishEvent(EventAction.ADD, dynamicDatasource);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateDatabase(DynamicDatasource dynamicDatasource) {
        final LbqWrapper<DynamicDatasource> lbqWrapper = Wraps.<DynamicDatasource>lbQ().eq(DynamicDatasource::getPoolName, dynamicDatasource.getPoolName());
        if (dynamicDatasource.getId() != null) {
            lbqWrapper.ne(DynamicDatasource::getId, dynamicDatasource.getId());
        }
        final int count = super.count(lbqWrapper);
        if (count > 0) {
            throw CheckedException.badRequest("连接池名称已存在");
        }
        super.saveOrUpdate(dynamicDatasource);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeDatabaseById(Long id) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("数据连接信息不存在"));
        this.baseMapper.deleteById(id);
        final List<TenantDynamicDatasourceVO> dataSourceList = this.tenantConfigMapper.selectTenantDynamicDatasource(id);
        for (TenantDynamicDatasourceVO tenantDynamicDatasource : dataSourceList) {
            publishEvent(EventAction.DEL, tenantDynamicDatasource);
        }
    }

    @Override
    public void publishEvent(EventAction action, Long tenantId) {
        final TenantDynamicDatasourceVO dynamicDatasource = this.tenantConfigMapper.getTenantDynamicDatasourceByTenantId(tenantId);
        publishEvent(action, dynamicDatasource);
    }

    @Override
    public void initSqlScript() {


    }

    private void publishEvent(EventAction action, TenantDynamicDatasourceVO dynamicDatasource) {
        if (Objects.isNull(dynamicDatasource)) {
            throw CheckedException.notFound("租户未关联数据源信息");
        }
        final TenantDynamicDatasource datasource = TENANT_DYNAMIC_DATASOURCE_VO_2_TENANT_DYNAMIC_DATASOURCE_CONVERTS.convert(dynamicDatasource);
        eventPublisher.publishEvent(new DynamicDatasourceEvent(this, busProperties.getId(), datasource, action.getType()));
        log.debug("event publish successful - {}", datasource);
    }
}