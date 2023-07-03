package com.wemirr.framework.db.configuration;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.*;
import com.wemirr.framework.db.TenantEnvironment;
import com.wemirr.framework.db.configuration.handler.MyBatisMetaObjectHandler;
import com.wemirr.framework.db.injector.MySqlInjector;
import com.wemirr.framework.db.mybatis.auth.DataScopeAspect;
import com.wemirr.framework.db.mybatis.auth.DataScopeInnerInterceptor;
import com.wemirr.framework.db.properties.DatabaseProperties;
import com.wemirr.framework.db.properties.MultiTenantType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Mybatis 常用重用拦截器
 * <p>
 * 拦截器执行一定是：
 * WriteInterceptor > DataScopeInterceptor > PaginationInterceptor
 *
 * @author Levin
 * @since 2018/10/24
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(DatabaseProperties.class)
public abstract class BaseMybatisConfiguration {

    @Resource
    protected DatabaseProperties properties;
    @Resource
    private TenantEnvironment tenantEnvironment;
    @Resource
    private ApplicationContext applicationContext;


    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,
     * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false
     * 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    @Order(5)
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        if (MultiTenantType.NONE != properties.getMultiTenant().getType()) {
            // 新增多租户拦截器
            interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
                @Override
                public Expression getTenantId() {
                    // 租户ID
                    log.debug("当前租户ID - {}", tenantEnvironment.tenantId());
                    return new LongValue(tenantEnvironment.tenantId());
                }

                @Override
                public boolean ignoreTable(String tableName) {
                    final List<String> tables = properties.getMultiTenant().getIncludeTables();
                    //  判断哪些表不需要尽心多租户判断,返回false表示都需要进行多租户判断
                    return tenantEnvironment.anonymous() || !tables.contains(tableName);
                }

                @Override
                public String getTenantIdColumn() {
                    return properties.getMultiTenant().getTenantIdColumn();
                }

            }));
        }
        List<InnerInterceptor> beforeInnerInterceptor = getPaginationBeforeInnerInterceptor();
        if (!beforeInnerInterceptor.isEmpty()) {
            beforeInnerInterceptor.forEach(interceptor::addInnerInterceptor);
        }
        // 新增MYSQL分页拦截器,一定要先设置租户判断后才进行分页拦截设置
        // 分页插件: PaginationInnerInterceptor
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        paginationInnerInterceptor.setMaxLimit(properties.getMaxLimit());
        //防止全表更新与删除插件: BlockAttackInnerInterceptor
        interceptor.addInnerInterceptor(paginationInnerInterceptor);

        List<InnerInterceptor> afterInnerInterceptor = getPaginationAfterInnerInterceptor();
        if (!afterInnerInterceptor.isEmpty()) {
            afterInnerInterceptor.forEach(interceptor::addInnerInterceptor);
        }

        if (properties.isBlockAttack()) {
            BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();
            interceptor.addInnerInterceptor(blockAttackInnerInterceptor);
        }
        // sql性能规范插件，限制比较多，慎用哦
        if (properties.isIllegalSql()) {
            IllegalSQLInnerInterceptor isi = new IllegalSQLInnerInterceptor();
            interceptor.addInnerInterceptor(isi);
        }
        return interceptor;
    }


    /**
     * 分页拦截器之前的插件
     *
     * @return List<InnerInterceptor>
     */
    protected List<InnerInterceptor> getPaginationAfterInnerInterceptor() {
        return Collections.emptyList();
    }

    /**
     * 分页拦截器之后的插件
     *
     * @return List<InnerInterceptor>
     */
    protected List<InnerInterceptor> getPaginationBeforeInnerInterceptor() {
        List<InnerInterceptor> list = new ArrayList<>();
        boolean isDataScope = properties.isDataScope();
        if (isDataScope) {
            list.add(new DataScopeInnerInterceptor(applicationContext, tenantEnvironment));
        }
        return list;
    }

    /**
     * Mybatis Plus 注入器
     *
     * @return DatabaseProperties
     */
    @Bean("myBatisMetaObjectHandler")
    @ConditionalOnMissingBean
    public MetaObjectHandler myBatisMetaObjectHandler() {
        DatabaseProperties.Id id = properties.getId();
        return new MyBatisMetaObjectHandler(id.getWorkerId(), id.getDataCenterId(), tenantEnvironment);
    }


    @Bean
    @ConditionalOnMissingBean
    public MySqlInjector getMySqlInjector() {
        return new MySqlInjector();
    }

    @Bean
    @ConditionalOnMissingBean
    public DataScopeAspect dataScopeAspect() {
        return new DataScopeAspect();
    }
}
