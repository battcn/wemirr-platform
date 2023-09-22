package com.wemirr.framework.db.mybatisplus.intercept.data;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.MultiDataPermissionHandler;
import com.google.common.collect.Lists;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.TenantEnvironment;
import com.wemirr.framework.db.utils.MyBatisUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 3.5.2 之后推荐用 MultiDataPermissionHandler  但是多表条件会频繁获取 权限范围 数据 性能较差暂时没想好如何优化
 *
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class DataScopePermissionHandler implements MultiDataPermissionHandler {

    private final TenantEnvironment tenantEnvironment;


    @SneakyThrows
    @Override
    public Expression getSqlSegment(final Table table, Expression where, String mappedStatementId) {
        // 匿名用户不进入数据权限插件
        if (tenantEnvironment.anonymous()) {
            return null;
        }
        if (log.isDebugEnabled()) {
            log.debug("sql statementId{},where - {}", mappedStatementId, where);
        }
        final Method method = Optional.ofNullable(getMethod(mappedStatementId)).orElseThrow(() -> CheckedException.notFound("mapper method not found"));
        final DataScope dataScope = method.getAnnotation(DataScope.class);
        if (dataScope == null) {
            return null;
        }
        return buildAnnotationExpression(table, dataScope);
        //return buildDefaultExpression();
    }

    /**
     * 这种策略应该废弃了（全局需要处理的地方太多了,既然如此那就直接不实现吧）
     *
     * @return 表达式
     */
    private Expression buildDefaultExpression() {
        final DataScopeService service = SpringUtil.getBean(DataScopeService.class);
        if (service == null) {
            throw CheckedException.notFound("data scope service bean not found");
        }
        final DataPermission permission = service.getDataScopeById(tenantEnvironment.userId());
        if (permission == null || permission.getScopeType() == DataScopeType.ALL) {
            return null;
        }
        if (permission.getScopeType() == DataScopeType.SELF) {
            return new EqualsTo(new Column(permission.getSelfScopeName()), new LongValue(permission.getUserId()));
        }
        final ItemsList itemsList = new ExpressionList(permission.getOrgIds().stream()
                .map(LongValue::new).collect(Collectors.toList()));
        return new InExpression(new Column(permission.getScopeName()), itemsList);
    }

    private Expression buildAnnotationExpression(Table table, DataScope dataScope) {
        if (dataScope.type() == DataScopeType.ALL || dataScope.ignore()) {
            return null;
        }
        final DataPermission permission = DataPermissionContextHolder.get();
        if (permission == null) {
            log.warn("permission context is empty,skip process......");
            return null;
        }
        final DataColumn[] columns = dataScope.columns();
        final List<Expression> conditions = Lists.newArrayList();
        for (DataColumn column : columns) {
            if (StrUtil.equals(table.getAlias().getName(), column.alias())) {
                final Column expressionColumn = MyBatisUtils.buildColumn(table, column.name());
                // 判断类型,如果是个人就用 EQ 性能更高
                if (dataScope.type() == DataScopeType.SELF) {
                    conditions.add(new EqualsTo(expressionColumn, new LongValue(permission.getUserId())));
                } else {
                    ItemsList itemsList = new ExpressionList(permission.getOrgIds().stream()
                            .map(LongValue::new).collect(Collectors.toList()));
                    conditions.add(new InExpression(expressionColumn, itemsList));
                }
            }
        }
        // 使用循环将条件逐个与前面的条件组合起来
        Expression finalExpression = null;
        for (Expression condition : conditions) {
            finalExpression = Optional.of(condition).orElse(new AndExpression(finalExpression, condition));
        }
        return finalExpression;
    }

    @SneakyThrows
    private Method getMethod(String mappedStatementId) {
        final Class<?> clazz = Class.forName(mappedStatementId.substring(0, mappedStatementId.lastIndexOf(".")));
        final Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(getMethodName(mappedStatementId))) {
                return method;
            }
        }
        return null;
    }

    private String getMethodName(String mappedStatementId) {
        return mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1);
    }
}
