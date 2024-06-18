package com.wemirr.framework.log.diff;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.log.diff.configuration.DiffLogProperties;
import com.wemirr.framework.log.diff.core.annotation.DIffIgnore;
import com.wemirr.framework.log.diff.core.annotation.DiffField;
import com.wemirr.framework.log.diff.core.annotation.DiffFields;
import com.wemirr.framework.log.diff.service.IFunctionService;
import de.danielbechler.diff.node.DiffNode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.lang.NonNull;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Levin
 */
@Slf4j
@Setter
@Getter
public class DefaultDiffItemsToLogContentService implements IDiffItemsToLogContentService, BeanFactoryAware, SmartInitializingSingleton {

    private IFunctionService functionService;
    private final DiffLogProperties diffLogProperties;
    private BeanFactory beanFactory;

    public DefaultDiffItemsToLogContentService(DiffLogProperties diffLogProperties) {
        this.diffLogProperties = diffLogProperties;
    }

    @Override
    public String toLogContent(DiffNode diffNode, final Object sourceObject, final Object targetObject) {
        if (!diffNode.hasChanges()) {
            return "";
        }
        DiffFields annotation = sourceObject.getClass().getAnnotation(DiffFields.class);
        StringBuilder stringBuilder = new StringBuilder();
        Set<DiffNode> set = new HashSet<>();
        diffNode.visit((node, visit) -> generateAllFieldLog(sourceObject, targetObject, stringBuilder, node, annotation, set));
        set.clear();
        return stringBuilder.toString().replaceAll(diffLogProperties.getFieldSeparator().concat("$"), "");
    }

    private void generateAllFieldLog(Object sourceObject, Object targetObject, StringBuilder stringBuilder, DiffNode node,
                                     DiffFields annotation, Set<DiffNode> set) {
        if (node.isRootNode() || node.getValueTypeInfo() != null || set.contains(node)) {
            return;
        }
        DIffIgnore logIgnore = node.getFieldAnnotation(DIffIgnore.class);
        if (logIgnore != null) {
            memorandum(node, set);
            return;
        }
        DiffField diffFieldAnnotation = node.getFieldAnnotation(DiffField.class);
        if (annotation == null && diffFieldAnnotation == null) {
            return;
        }
        String filedLogName = getFieldLogName(node, diffFieldAnnotation, annotation != null);
        if (StrUtil.isEmpty(filedLogName)) {
            return;
        }
        // 是否是容器类型的字段
        boolean valueIsContainer = valueIsContainer(node, sourceObject, targetObject);
        String functionName = diffFieldAnnotation != null ? diffFieldAnnotation.function() : "";
        String logContent = valueIsContainer
                ? getCollectionDiffLogContent(filedLogName, node, sourceObject, targetObject, functionName)
                : getDiffLogContent(filedLogName, node, sourceObject, targetObject, functionName);
        if (StrUtil.isNotBlank(logContent)) {
            stringBuilder.append(logContent).append(diffLogProperties.getFieldSeparator());
        }
        memorandum(node, set);
    }

    private void memorandum(DiffNode node, Set<DiffNode> set) {
        set.add(node);
        if (!node.hasChildren()) {
            return;
        }
        Field childrenField = ReflectionUtils.findField(DiffNode.class, "children");
        assert childrenField != null;
        ReflectionUtils.makeAccessible(childrenField);
        Object object = ReflectionUtils.getField(childrenField, node);
        if (object instanceof Map<?, ?> children) {
            for (Object value : children.values()) {
                memorandum((DiffNode) value, set);
            }
        }
    }

    private String getFieldLogName(DiffNode node, DiffField diffFieldAnnotation, boolean isField) {
        String filedLogName = diffFieldAnnotation != null ? diffFieldAnnotation.title() : node.getPropertyName();
        if (node.getParentNode() != null) {
            //获取对象的定语：比如：创建人的ID
            filedLogName = getParentFieldName(node, isField) + filedLogName;
        }
        return filedLogName;
    }

    private boolean valueIsContainer(DiffNode node, Object sourceObject, Object targetObject) {
        if (sourceObject != null) {
            Object sourceValue = node.canonicalGet(sourceObject);
            if (sourceValue == null) {
                if (targetObject != null) {
                    return node.canonicalGet(targetObject) instanceof Collection || node.canonicalGet(targetObject).getClass().isArray();
                }
            } else {
                return sourceValue instanceof Collection || sourceValue.getClass().isArray();
            }
        }
        return false;
    }

    private String getParentFieldName(DiffNode node, boolean isField) {
        DiffNode parent = node.getParentNode();
        String fieldNamePrefix = "";
        while (parent != null) {
            DiffField diffFieldAnnotation = parent.getFieldAnnotation(DiffField.class);
            if ((diffFieldAnnotation == null && !isField) || parent.isRootNode()) {
                // 父节点没有配置名称且不用属性名映射，不拼接
                parent = parent.getParentNode();
                continue;
            }
            fieldNamePrefix = diffFieldAnnotation != null
                    ? diffFieldAnnotation.title().concat(diffLogProperties.getOfWord()).concat(fieldNamePrefix)
                    : parent.getPropertyName().concat(diffLogProperties.getOfWord()).concat(fieldNamePrefix);
            parent = parent.getParentNode();
        }
        return fieldNamePrefix;
    }

    public String getCollectionDiffLogContent(String filedLogName, DiffNode node, Object sourceObject, Object targetObject, String functionName) {
        //集合走单独的diff模板
        Collection<?> sourceList = getListValue(node, sourceObject);
        Collection<?> targetList = getListValue(node, targetObject);
        Collection<?> addItemList = listSubtract(targetList, sourceList);
        Collection<?> delItemList = listSubtract(sourceList, targetList);
        String listAddContent = listToContent(functionName, addItemList);
        String listDelContent = listToContent(functionName, delItemList);
        return diffLogProperties.formatList(filedLogName, listAddContent, listDelContent);
    }

    public String getDiffLogContent(String filedLogName, DiffNode node, Object sourceObject, Object targetObject, String functionName) {
        return switch (node.getState()) {
            case ADDED ->
                    diffLogProperties.formatAdd(filedLogName, getFunctionValue(getFieldValue(node, targetObject), functionName));
            case CHANGED ->
                    diffLogProperties.formatUpdate(filedLogName, getFunctionValue(getFieldValue(node, sourceObject), functionName), getFunctionValue(getFieldValue(node, targetObject), functionName));
            case REMOVED ->
                    diffLogProperties.formatDeleted(filedLogName, getFunctionValue(getFieldValue(node, sourceObject), functionName));
            default -> {
                log.warn("diff log not support");
                yield "";
            }
        };
    }

    private Collection<?> getListValue(DiffNode node, Object object) {
        Object fieldSourceValue = getFieldValue(node, object);
        if (fieldSourceValue != null && fieldSourceValue.getClass().isArray()) {
            return new ArrayList<>(Arrays.asList((Object[]) fieldSourceValue));
        }
        if (fieldSourceValue == null) {
            return new ArrayList<>();
        }
        if (fieldSourceValue instanceof Collection<?> collection) {
            return collection;
        }
        return List.of(fieldSourceValue);
    }

    private Collection<Object> listSubtract(Collection<?> minuend, Collection<?> subTractor) {
        Collection<Object> addItemList = new ArrayList<>(minuend);
        addItemList.removeAll(subTractor);
        return addItemList;
    }

    private String listToContent(String functionName, Collection<?> addItemList) {
        StringBuilder listAddContent = new StringBuilder();
        if (CollUtil.isNotEmpty(addItemList)) {
            for (Object item : addItemList) {
                listAddContent.append(getFunctionValue(item, functionName)).append(diffLogProperties.getListItemSeparator());
            }
        }
        return listAddContent.toString().replaceAll(diffLogProperties.getListItemSeparator() + "$", "");
    }

    private String getFunctionValue(Object canonicalGet, String functionName) {
        if (StrUtil.isEmpty(functionName)) {
            return canonicalGet.toString();
        }
        return functionService.apply(functionName, canonicalGet.toString());
    }

    private Object getFieldValue(DiffNode node, Object o2) {
        return node.canonicalGet(o2);
    }

    @Override
    public void setBeanFactory(@NonNull BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.functionService = beanFactory.getBean(IFunctionService.class);
    }
}
