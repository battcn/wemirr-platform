package com.wemirr.framework.db.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.framework.db.properties.MultiTenantType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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
     * 获取多租户配置
     */
    private static DatabaseProperties.MultiTenant getMultiTenant() {
        return SpringUtil.getBean(DatabaseProperties.class).getMultiTenant();
    }

    /**
     * 判断当前用户是否为超级租户
     */
    public static boolean isSuperTenant() {
        var context = SpringUtil.getBean(AuthenticationContext.class);
        var tenantCode = context.tenantCode();
        return StringUtils.isNotBlank(tenantCode) && StringUtils.equals(tenantCode, getMultiTenant().getSuperTenantCode());
    }

    /**
     * 判断是否为动态数据源模式
     */
    public static boolean isDynamicSource() {
        return getMultiTenant().getType() == MultiTenantType.DATASOURCE;
    }

    // ==================== 主数据源执行 ====================

    /**
     * 使用主数据源执行（有返回值）
     *
     * @param supplier 待执行的动作
     * @param <T>      返回类型
     * @return 执行结果
     */
    public static <T> T executeWithMaster(Supplier<T> supplier) {
        try {
            DynamicDataSourceContextHolder.push(getMultiTenant().getDefaultDsName());
            return supplier.get();
        } finally {
            DynamicDataSourceContextHolder.poll();
        }
    }

    /**
     * 使用主数据源执行（无返回值）
     *
     * @param runnable 待执行的动作
     */
    public static void runWithMaster(Runnable runnable) {
        executeWithMaster(() -> {
            runnable.run();
            return null;
        });
    }

    // ==================== 租户数据源执行 ====================

    /**
     * 使用指定租户 DB 执行（有返回值）
     *
     * @param tenantCode 租户编码
     * @param supplier   待执行的动作
     * @param <T>        返回类型
     * @return 执行结果
     */
    public static <T> T executeWithTenantDb(String tenantCode, Supplier<T> supplier) {
        var multiTenant = getMultiTenant();
        if (multiTenant.getType() == MultiTenantType.COLUMN || multiTenant.isSuperTenant(tenantCode)) {
            return executeWithMaster(supplier);
        }
        try {
            DynamicDataSourceContextHolder.push(multiTenant.buildTenantDataSourceName(tenantCode));
            return supplier.get();
        } finally {
            DynamicDataSourceContextHolder.poll();
        }
    }

    /**
     * 使用指定租户 DB 执行（无返回值）
     *
     * @param tenantCode 租户编码
     * @param runnable   待执行的动作
     */
    public static void runWithTenantDb(String tenantCode, Runnable runnable) {
        executeWithTenantDb(tenantCode, () -> {
            runnable.run();
            return null;
        });
    }

    // ==================== 隔离类型执行 ====================

    /**
     * 根据隔离类型执行（有返回值）
     *
     * @param dbSupplier     数据源隔离函数
     * @param columnSupplier 字段隔离函数
     * @param <T>            返回类型
     * @return 执行结果
     */
    public static <T> T executeWithIsolationType(Supplier<T> dbSupplier, Supplier<T> columnSupplier) {
        return isDynamicSource() ? dbSupplier.get() : columnSupplier.get();
    }

    /**
     * 根据隔离类型执行（无返回值）
     *
     * @param dbRunnable     数据源隔离动作
     * @param columnRunnable 字段隔离动作
     */
    public static void runWithIsolationType(Runnable dbRunnable, Runnable columnRunnable) {
        if (isDynamicSource()) {
            dbRunnable.run();
        } else {
            columnRunnable.run();
        }
    }

    // ==================== 忽略策略执行 ====================

    /**
     * 忽略租户策略执行（有返回值）
     *
     * @param block 待执行的动作
     * @param <T>   返回类型
     * @return 执行结果
     */
    public static <T> T withIgnoreStrategy(Supplier<T> block) {
        return withIgnoreStrategy(IgnoreStrategy.builder().tenantLine(true).build(), block);
    }

    /**
     * 忽略指定策略执行（有返回值）
     *
     * @param strategy 忽略策略
     * @param block    待执行的动作
     * @param <T>      返回类型
     * @return 执行结果
     */
    public static <T> T withIgnoreStrategy(IgnoreStrategy strategy, Supplier<T> block) {
        try {
            InterceptorIgnoreHelper.handle(strategy);
            return block.get();
        } finally {
            InterceptorIgnoreHelper.clearIgnoreStrategy();
        }
    }

    /**
     * 忽略租户策略执行（无返回值）
     *
     * @param runnable 待执行的动作
     */
    public static void runWithIgnoreStrategy(Runnable runnable) {
        runWithIgnoreStrategy(IgnoreStrategy.builder().tenantLine(true).build(), runnable);
    }

    /**
     * 忽略指定策略执行（无返回值）
     *
     * @param strategy 忽略策略
     * @param runnable 待执行的动作
     */
    public static void runWithIgnoreStrategy(IgnoreStrategy strategy, Runnable runnable) {
        withIgnoreStrategy(strategy, () -> {
            runnable.run();
            return null;
        });
    }
}
