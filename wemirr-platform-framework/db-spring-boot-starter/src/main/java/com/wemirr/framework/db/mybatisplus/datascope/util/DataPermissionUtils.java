package com.wemirr.framework.db.mybatisplus.datascope.util;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.commons.security.DataPermission;
import com.wemirr.framework.commons.security.DataResourceType;
import com.wemirr.framework.commons.security.DataScopeType;
import com.wemirr.framework.db.mybatisplus.datascope.annotation.DataScope;
import com.wemirr.framework.db.mybatisplus.datascope.handler.DataPermissionRule;
import com.wemirr.framework.db.mybatisplus.datascope.holder.DataPermissionRuleHolder;
import com.wemirr.framework.db.utils.MyBatisUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@Slf4j
public final class DataPermissionUtils {

    private DataPermissionUtils() {

    }

    /**
     * 使用指定的数据权限执行任务
     *
     * @param supplier 待执行的动作
     */
    public static <T> T executeDefaultDataPermissionRule(Supplier<T> supplier) {
        // 根据默认权限规则查询
        DataPermissionRule rule = DataPermissionRule.builder().columns(List.of(new DataPermissionRule.Column())).build();
        return executeWithDataPermissionRule(rule, supplier);
    }

    /**
     * 使用指定的数据权限执行任务
     *
     * @param rule 当前任务执行时使用的数据权限规则
     * @param supplier           待执行的动作
     */
    public static <T> T executeWithDataPermissionRule(DataPermissionRule rule, Supplier<T> supplier) {
        DataPermissionRuleHolder.push(rule);
        try {
            return supplier.get();
        } finally {
            DataPermissionRuleHolder.poll();
        }
    }

    /**
     * 数据权限本地缓存,减少解析耗时 越用越流畅 遥遥领先
     */
    private static final Map<String, DataPermissionRule> DATA_SCOPE_CACHE = Maps.newConcurrentMap();

    @SneakyThrows
    public static DataPermissionRule getDataPermissionRuleByMappedStatementId(String mappedStatementId) {
        if (DATA_SCOPE_CACHE.containsKey(mappedStatementId)) {
            return DATA_SCOPE_CACHE.get(mappedStatementId);
        }
        final Class<?> clazz = Class.forName(mappedStatementId.substring(0, mappedStatementId.lastIndexOf(".")));
        final Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (!method.getName().equals(getMethodName(mappedStatementId))) {
                continue;
            }
            var rule = buildPermissionRule(method.getAnnotation(DataScope.class));
            if (rule == null) {
                return null;
            }
            DATA_SCOPE_CACHE.put(mappedStatementId, rule);
            return rule;
        }
        return null;
    }

    private static DataPermissionRule buildPermissionRule(DataScope scope) {
        if (scope == null) {
            return null;
        }
        final List<DataPermissionRule.Column> columns = Arrays.stream(scope.columns())
                .map(column -> DataPermissionRule.Column.builder().alias(column.alias()).name(column.name())
                        .resource(column.resource()).javaClass(column.javaClass()).build()).toList();
        return DataPermissionRule.builder().ignore(scope.ignore()).columns(columns).build();
    }

    public static List<Expression> buildConditions(AuthenticationContext context, final Table table, final List<DataPermissionRule.Column> columns) {
        final DataPermission permission = context.dataPermission();
        final Map<DataResourceType, List<Object>> dataPermissionMap = permission.getDataPermissionMap();
        final List<Expression> conditions = Lists.newArrayList();
        for (DataPermissionRule.Column column : columns) {
            final DataScopeType scopeType = column.getScopeType() == DataScopeType.IGNORE ? permission.getScopeType() : column.getScopeType();
            if (scopeType == DataScopeType.ALL) {
                continue;
            }
            if (table.getAlias() != null && !StrUtil.equals(table.getAlias().getName(), column.getAlias())) {
                continue;
            }
            final Column expressionColumn = MyBatisUtils.buildColumn(table, column.getName());
            // 判断类型,如果是个人就用 EQ 性能更高
            if (scopeType == DataScopeType.SELF) {
                conditions.add(new EqualsTo(expressionColumn, new LongValue(context.userId())));
                continue;
            }
            if (CollUtil.isEmpty(dataPermissionMap)) {
                log.warn("data permission context is empty,skip process......");
                return null;
            }
            ItemsList itemsList = getItemsList(dataPermissionMap, column);
            if (itemsList == null) {
                continue;
            }
            conditions.add(new InExpression(expressionColumn, itemsList));
        }
        return conditions;
    }

    private static String getMethodName(String mappedStatementId) {
        return mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1);
    }

    /**
     * 获取 ItemsList
     *
     * @param permissionMap 权限MAP
     * @param column        字段
     * @return ItemsList
     */
    private static ItemsList getItemsList(Map<DataResourceType, List<Object>> permissionMap, DataPermissionRule.Column column) {
        ItemsList itemsList = null;
        final List<?> valList = permissionMap.get(column.getResource());
        if (CollUtil.isEmpty(valList)) {
            return null;
        }
        final Class<?> javaClass = column.getJavaClass();
        if (javaClass.equals(Integer.class) || javaClass.equals(Long.class)) {
            itemsList = new ExpressionList(valList.stream().filter(Objects::nonNull)
                    .map(x -> new LongValue(Integer.parseInt(x.toString()))).collect(Collectors.toList()));
        } else if (javaClass.equals(String.class)) {
            itemsList = new ExpressionList(valList.stream().filter(Objects::nonNull)
                    .map(x -> new StringValue(x.toString())).collect(Collectors.toList()));
        }
        return itemsList;
    }


}
