package com.wemirr.framework.db.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.wemirr.framework.db.mybatisplus.datascope.holder.DataPermissionRuleHolder;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.framework.db.properties.MultiTenantType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * 租户工具类
 *
 * @author Levin
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TenantHelper {

    /**
     * 使用主数据源执行
     *
     * @param supplier 待执行的动作
     */
    public static <T> T executeWithMaster(Supplier<T> supplier) {
        try {
            DatabaseProperties properties = SpringUtil.getBean(DatabaseProperties.class);
            DynamicDataSourceContextHolder.push(properties.getMultiTenant().getDefaultDsName());
            return supplier.get();
        } finally {
            DataPermissionRuleHolder.poll();
        }
    }

    /**
     * 使用指定 DB 执行
     *
     * @param supplier 待执行的动作
     */
    public static <T> T executeWithTenantDb(String tenantCode, Supplier<T> supplier) {
        DatabaseProperties properties = SpringUtil.getBean(DatabaseProperties.class);
        DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
        if (multiTenant.getType() == MultiTenantType.COLUMN || multiTenant.isSuperTenant(tenantCode)) {
            return executeWithMaster(supplier);
        }
        try {
            DynamicDataSourceContextHolder.push(multiTenant.buildTenantDataSourceName(tenantCode));
            return supplier.get();
        } finally {
            DataPermissionRuleHolder.poll();
        }
    }

    public static boolean isDynamicSource() {
        DatabaseProperties properties = SpringUtil.getBean(DatabaseProperties.class);
        DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
        return multiTenant.getType() == MultiTenantType.DATASOURCE;
    }


    /**
     * 使用隔离类型执行
     *
     * @param dbSupplier     数据源函数
     * @param columnSupplier 字段隔离函数
     * @param <T>            返回类型
     * @return 查询结果
     */
    public static <T> T executeWithIsolationType(Supplier<T> dbSupplier, Supplier<T> columnSupplier) {
        return isDynamicSource() ? dbSupplier.get() : columnSupplier.get();
    }
}
