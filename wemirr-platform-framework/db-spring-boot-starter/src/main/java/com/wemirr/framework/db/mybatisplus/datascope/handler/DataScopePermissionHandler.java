/*
 * Copyright (c) 2023 WEMIRR-PLATFORM Authors. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wemirr.framework.db.mybatisplus.datascope.handler;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.MultiDataPermissionHandler;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.commons.security.DataPermission;
import com.wemirr.framework.commons.security.DataScopeType;
import com.wemirr.framework.db.mybatisplus.datascope.holder.DataPermissionRuleHolder;
import com.wemirr.framework.db.mybatisplus.datascope.util.DataPermissionUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.schema.Table;

import java.util.List;

/**
 * 数据权限处理
 *
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class DataScopePermissionHandler implements MultiDataPermissionHandler {
    
    private final AuthenticationContext context;
    
    @SneakyThrows
    @Override
    public Expression getSqlSegment(final Table table, Expression where, String mappedStatementId) {
        // 匿名用户不进入数据权限插件
        if (context.anonymous()) {
            return null;
        }
        if (log.isDebugEnabled()) {
            log.debug("sql statementId{},where - {}", mappedStatementId, where);
        }
        // 默认从当前线程上下文获取,兼容 DataPermissionUtils.executeWithDataPermissionRule 方式
        DataPermissionRule rule = DataPermissionRuleHolder.peek();
        if (rule == null) {
            // 注解的优先级最低
            rule = DataPermissionUtils.getDataPermissionRuleByMappedStatementId(mappedStatementId);
        }
        return buildAnnotationExpression(table, rule);
    }
    
    private Expression buildAnnotationExpression(Table table, DataPermissionRule rule) {
        if (rule == null) {
            return null;
        }
        final DataPermission permission = context.dataPermission();
        if (permission.getScopeType() == DataScopeType.ALL || rule.isIgnore()) {
            return null;
        }
        final List<DataPermissionRule.Column> columns = rule.getColumns();
        final List<Expression> conditions = DataPermissionUtils.buildConditions(context, table, columns);
        if (CollUtil.isEmpty(conditions)) {
            return null;
        }
        // 使用循环将条件逐个与前面的条件组合起来
        return conditions.stream().reduce(AndExpression::new).orElse(null);
    }
}
