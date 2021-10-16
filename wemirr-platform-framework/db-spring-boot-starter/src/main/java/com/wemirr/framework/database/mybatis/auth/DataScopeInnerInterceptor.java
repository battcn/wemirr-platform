package com.wemirr.framework.database.mybatis.auth;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.wemirr.framework.database.TenantEnvironment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * mybatis 数据权限拦截器
 * <p>
 * <p>
 * 1，全部：没有createUser权限
 * 2，本级：当前用户的orgId
 * 3，本级以及子级
 * 4，自定义：
 * 5，个人：created_by = 1
 *
 * @author Levin
 * @since 2020/9/08
 */
@Slf4j
@AllArgsConstructor
public class DataScopeInnerInterceptor implements InnerInterceptor {
    private final ApplicationContext applicationContext;
    private final TenantEnvironment tenantEnvironment;


    /**
     * 查找参数是否包括DataScope对象
     *
     * @param parameterObj 参数列表
     * @return DataScope
     */
    protected Optional<DataScope> findDataScope(Object parameterObj) {
        if (parameterObj == null) {
            return Optional.empty();
        }
        if (parameterObj instanceof DataScope) {
            return Optional.of((DataScope) parameterObj);
        } else if (parameterObj instanceof Map) {
            for (Object val : ((Map<?, ?>) parameterObj).values()) {
                if (val instanceof DataScope) {
                    return Optional.of((DataScope) val);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        DataScope dataScope = findDataScope(parameter).orElse(null);
        if (dataScope == null) {
            return;
        }
        String originalSql = boundSql.getSql();
        String scopeName = dataScope.getScopeName();
        String selfScopeName = dataScope.getSelfScopeName();
        Long userId = dataScope.getUserId() == null ? tenantEnvironment.userId() : dataScope.getUserId();
        List<Long> orgIds = dataScope.getOrgIds();
        DataScopeType dsType = dataScope.getScopeType();
        if (!DataScopeType.ALL.eq(dsType) && CollectionUtil.isEmpty(orgIds)) {
            // 查询当前用户的 角色 最小权限
            final DataScopeService scopeService = applicationContext.getBean(DataScopeService.class);
            // 获取当前用户的数据权限
            final DataScope result = scopeService.getDataScopeById(userId);
            if (result == null) {
                return;
            }
            dsType = result.getScopeType();
            orgIds = result.getOrgIds();
        }

        //查全部
        if (DataScopeType.ALL.eq(dsType)) {
            return;
        }
        //查个人
        if (DataScopeType.SELF.eq(dsType)) {
            originalSql = "select * from (" + originalSql + ") temp_data_scope where temp_data_scope." + selfScopeName + " = " + userId;
        }
        //查其他
        else if (StrUtil.isNotBlank(scopeName) && CollUtil.isNotEmpty(orgIds)) {
            String join = CollectionUtil.join(orgIds, ",");
            originalSql = "select * from (" + originalSql + ") temp_data_scope where temp_data_scope." + scopeName + " in (" + join + ")";
        }
        PluginUtils.MPBoundSql mpBoundSql = PluginUtils.mpBoundSql(boundSql);
        mpBoundSql.sql(originalSql);
    }

}
