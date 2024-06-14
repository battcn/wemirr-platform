package com.wemirr.framework.db.mybatisplus.audit;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Levin
 */
@Slf4j
public class DataChangeAuditInterceptor implements InnerInterceptor {


    @Override
    public boolean willDoUpdate(Executor executor, MappedStatement ms, Object parameter) {
        return ms.getId().endsWith("updateById");
    }

    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) {
        log.info("ms - {} - parameter - [{}]", ms.getId(), JSON.toJSONString(parameter));
        Object entity = getEntityFromParameter(parameter);
        if (entity == null) {
            return;
        }
        String tableName = getTableName(entity);
        log.info("tableName - {}", tableName);
        // 获取当前数据
        BaseMapper<?> baseMapper = getBaseMapper(ms);
        if (baseMapper == null) {
            return;
        }
        Object currentEntity = baseMapper.selectById(getIdValue(entity));
        if (currentEntity == null) {
            return;
        }
        Map<String, AuditField> differences = getDifferences(currentEntity, entity);
        log.info("审计日志 - {}", JSON.toJSONString(differences));
    }

    private Object getEntityFromParameter(Object parameter) {
        if (parameter instanceof Map<?, ?> paramMap) {
            if (paramMap.containsKey("et")) {
                return paramMap.get("et");
            }
        }
        return null;
    }

    private String getTableName(Object entity) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entity.getClass());
        return tableInfo.getTableName();
    }

    private Serializable getIdValue(Object entity) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entity.getClass());
        return (Serializable) tableInfo.getPropertyValue(entity, tableInfo.getKeyProperty());
    }

    private static Map<String, AuditField> getDifferences(Object currentUser, Object updatedUser) {
        Map<String, AuditField> differences = new HashMap<>();
        Field[] fields = ReflectUtil.getFields(currentUser.getClass());
        for (Field field : fields) {
            String fieldName = field.getName();
            String description = Optional.ofNullable(field.getAnnotation(Schema.class)).map(Schema::description).orElse(null);
            Object oldValue = ReflectUtil.getFieldValue(currentUser, field.getName());
            Object newValue = ReflectUtil.getFieldValue(updatedUser, field.getName());
            AuditField auditField = AuditField.builder().label(description).field(fieldName).source(oldValue).target(newValue).build();
            differences.put(fieldName, auditField);
            if (!Objects.equals(oldValue, newValue)) {
                log.info("变化数据 - {}", auditField.getFormat());
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
