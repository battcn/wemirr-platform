package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.dynamic.event.DynamicDatasourceEvent;
import com.wemirr.framework.db.dynamic.event.body.EventAction;
import com.wemirr.framework.db.dynamic.event.body.TenantDynamicDatasource;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.mybatisplus.wrap.query.LbqWrapper;
import com.wemirr.platform.authority.domain.tenant.entity.DynamicDatasource;
import com.wemirr.platform.authority.domain.tenant.resp.TenantDatasourceResp;
import com.wemirr.platform.authority.repository.common.DynamicDatasourceMapper;
import com.wemirr.platform.authority.repository.tenant.TenantConfigMapper;
import com.wemirr.platform.authority.service.TenantDatasourceService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.wemirr.platform.authority.domain.tenant.converts.TenantDatasourceConverts.TENANT_DYNAMIC_DATASOURCE_VO_2_TENANT_DYNAMIC_DATASOURCE_CONVERTS;


/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DynamicDatasourceServiceImpl extends SuperServiceImpl<DynamicDatasourceMapper, DynamicDatasource> implements TenantDatasourceService {

    private final TenantConfigMapper tenantConfigMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final ApplicationContext applicationContext;

    @Override
    public List<TenantDatasourceResp> selectTenantDynamicDatasource() {
        return this.tenantConfigMapper.selectTenantDynamicDatasource(null);
    }

    @Override
    public void ping(Long id) {
        log.debug("查询结果 - {}", JSON.toJSONString(""));
    }

    @PostConstruct
    public void init() {
        final List<TenantDatasourceResp> dataSourceList = this.tenantConfigMapper.selectTenantDynamicDatasource(null);
        if (CollectionUtil.isEmpty(dataSourceList)) {
            log.warn("未找到符合条件的数据源...");
            return;
        }
        for (TenantDatasourceResp dynamicDatasource : dataSourceList) {
            publishEvent(EventAction.ADD, dynamicDatasource);
        }
    }

    @Override
    @DSTransactional
    public void saveOrUpdateDatabase(DynamicDatasource dynamicDatasource) {
        final LbqWrapper<DynamicDatasource> lbqWrapper = Wraps.<DynamicDatasource>lbQ().eq(DynamicDatasource::getPoolName, dynamicDatasource.getPoolName());
        if (dynamicDatasource.getId() != null) {
            lbqWrapper.ne(DynamicDatasource::getId, dynamicDatasource.getId());
        }
        final long count = super.count(lbqWrapper);
        if (count > 0) {
            throw CheckedException.badRequest("连接池名称已存在");
        }
        super.saveOrUpdate(dynamicDatasource);
    }

    @Override
    @DSTransactional
    public void removeDatabaseById(Long id) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("数据连接信息不存在"));
        this.baseMapper.deleteById(id);
        final List<TenantDatasourceResp> dataSourceList = this.tenantConfigMapper.selectTenantDynamicDatasource(id);
        for (TenantDatasourceResp tenantDynamicDatasource : dataSourceList) {
            publishEvent(EventAction.DEL, tenantDynamicDatasource);
        }
    }

    @Override
    public void publishEvent(EventAction action, Long tenantId) {
        final TenantDatasourceResp dynamicDatasource = this.tenantConfigMapper.getTenantDynamicDatasourceByTenantId(tenantId);
        publishEvent(action, dynamicDatasource);
    }

    private void publishEvent(EventAction action, TenantDatasourceResp dynamicDatasource) {
        if (Objects.isNull(dynamicDatasource)) {
            throw CheckedException.notFound("租户未关联数据源信息");
        }
        final TenantDynamicDatasource datasource = TENANT_DYNAMIC_DATASOURCE_VO_2_TENANT_DYNAMIC_DATASOURCE_CONVERTS.convert(dynamicDatasource);
        eventPublisher.publishEvent(new DynamicDatasourceEvent(this, applicationContext.getId(), datasource, action.getType()));
        log.debug("event publish successful - {}", datasource);
    }
}
