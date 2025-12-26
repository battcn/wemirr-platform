package com.wemirr.framework.db.mybatisplus.audit;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.commons.entity.DictEnum;
import com.wemirr.framework.db.properties.DatabaseProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class AuditInterceptor implements InnerInterceptor {

    private final DatabaseProperties.Audit audit;

    boolean ignoreTable(String name) {
        return !audit.getIncludeTables().contains(name);
    }

    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) {
        log.debug("ms - {} - parameter - [{}]", ms.getId(), JacksonUtils.toJson(parameter));
        if (ms.getId().endsWith("updateById")) {
            return;
        }
        Object entity = getEntityFromParameter(parameter);
        if (entity == null) {
            log.warn("parameter entity is null...");
            return;
        }
        TableInfo tableInfo = TableInfoHelper.getTableInfo(ms.getParameterMap().getType());
        log.debug("tableInfo - {}", tableInfo);
        if (ignoreTable(tableInfo.getTableName())) {
            return;
        }
        // 获取当前数据
        BaseMapper<?> baseMapper = getBaseMapper(ms);
        if (baseMapper == null) {
            return;
        }
        Object keyValue = tableInfo.getPropertyValue(entity, tableInfo.getKeyProperty());
        Object source = baseMapper.selectById((Serializable) keyValue);
        if (source == null) {
            return;
        }
        Map<String, AuditField> differences = compareDifferences(tableInfo, source, entity);
        log.info("审计日志 - {}", JacksonUtils.toJson(differences));
    }

    private Object getEntityFromParameter(Object parameter) {
        if (parameter instanceof Map<?, ?> paramMap) {
            if (paramMap.containsKey("et")) {
                return paramMap.get("et");
            }
        }
        return null;
    }

    private Map<String, AuditField> compareDifferences(TableInfo tableInfo, Object sourceObject, Object targetObject) {
        String tableName = tableInfo.getTableName();
        Map<String, AuditField> differences = new HashMap<>();
        for (TableFieldInfo fieldInfo : tableInfo.getFieldList()) {
            Field field = fieldInfo.getField();
            String column = fieldInfo.getColumn();
            if (audit.getIgnoreGlobalColumns().contains(column)) {
                log.debug("配置全局忽略记录 - {}", column);
                continue;
            }
            List<String> list = audit.getIgnoreTableColumns().get(tableName);
            if (list != null && list.contains(column)) {
                log.debug("配置指定表忽略记录 - {} - {}", tableName, column);
                continue;
            }
            boolean ignore = Optional.ofNullable(field.getAnnotation(AuditColumn.class)).map(AuditColumn::ignore).orElse(false);
            if (ignore) {
                log.debug("配置注解忽略 - {} - {}", tableName, column);
                continue;
            }
            String fieldName = field.getName();
            String label = Optional.ofNullable(field.getAnnotation(Schema.class)).map(Schema::description).orElse(null);
            Object oldValue = ReflectUtil.getFieldValue(sourceObject, field.getName());
            Object newValue = ReflectUtil.getFieldValue(targetObject, field.getName());
            if (fieldInfo.getUpdateStrategy() == FieldStrategy.NOT_NULL && Objects.isNull(newValue)) {
                continue;
            }
            // 处理枚举类型的显示值
            Object sourceDisplay = (oldValue instanceof DictEnum<?> dict) ? ObjUtil.defaultIfNull(dict.getLabel(), oldValue) : oldValue;
            Object targetDisplay = (newValue instanceof DictEnum<?> dict) ? ObjUtil.defaultIfNull(dict.getLabel(), newValue) : newValue;
            String format = (oldValue instanceof DictEnum<?> || newValue instanceof DictEnum<?>)
                    ? "字段 [%s] 从 %s 修改至 %s".formatted(label, sourceDisplay, targetDisplay)
                    : null;
            var auditField = new AuditField(fieldName, label, oldValue, newValue, format);
            differences.put(fieldName, auditField);
            if (!Objects.equals(oldValue, newValue)) {
                log.info("变化数据 - {}", auditField.formattedDescription());
            }
        }
        return differences;
    }


    private BaseMapper<?> getBaseMapper(MappedStatement mappedStatement) {
        String namespace = mappedStatement.getId().substring(0, mappedStatement.getId().lastIndexOf('.'));
        try {
            Class<?> mapperClass = Class.forName(namespace);
            // 确保mapperClass是BaseMapper的实现类
            if (BaseMapper.class.isAssignableFrom(mapperClass)) {
                // 从 Spring 上下文中获取 Mapper 实例
                return (BaseMapper<?>) SpringUtil.getBean(mapperClass);
            }
        } catch (ClassNotFoundException e) {
            log.error("未检测到通用Mapper - {}", e.getLocalizedMessage());
        }
        return null;
    }
}
