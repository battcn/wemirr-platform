package com.wemirr.framework.log.diff;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.log.diff.configuration.DiffLogProperties;
import com.wemirr.framework.log.diff.core.DiffFieldStrategy;
import com.wemirr.framework.log.diff.core.LocalPropertyChange;
import com.wemirr.framework.log.diff.core.annotation.DiffField;
import com.wemirr.framework.log.diff.service.IFunctionService;
import com.wemirr.framework.log.diff.utils.DiffUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.core.diff.DiffBuilder;
import org.javers.core.diff.changetype.InitialValueChange;
import org.javers.core.diff.changetype.PropertyChange;
import org.javers.core.diff.changetype.TerminalValueChange;
import org.javers.core.diff.changetype.ValueChange;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.lang.NonNull;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Levin
 */
@Slf4j
@Setter
@Getter
@RequiredArgsConstructor
public class DefaultDiffItemsToLogContentService implements IDiffItemsToLogContentService, BeanFactoryAware, SmartInitializingSingleton {

    private IFunctionService functionService;
    private BeanFactory beanFactory;
    private final Javers javers;
    private final DiffLogProperties diffLogProperties;

    @Override
    public String toLogContent(final Object source, final Object target) {
        if (source == null && target == null) {
            return "";
        }
        var diff = DiffUtils.compare(javers, source, target);
        if (!diff.hasChanges()) {
            return "";
        }
        var changes = diff.getChangesByType(PropertyChange.class)
                .stream()
                .map(x -> LocalPropertyChange.wrap(javers, x))
                .toList();
        if (diffLogProperties.isPrettyValuePrinter()) {
            diff = new DiffBuilder(javers.getCoreConfiguration().getPrettyValuePrinter())
                    .addChanges(changes)
                    .build();
            log.info("diff format - {}", diff);
        }
        StringBuilder builder = new StringBuilder();
        for (Change change : changes) {
            processChangeNode(source != null ? source.getClass() : target.getClass(), builder, change);
        }
        return builder.toString().replaceAll(diffLogProperties.getFieldSeparator().concat("$"), "");
    }

    private void processChangeNode(Class<?> clazz, StringBuilder builder, Change change) {
        if (!(change instanceof LocalPropertyChange valueChange)) {
            return;
        }
        Field field = ReflectUtil.getField(clazz, valueChange.getOriginalName());
        if (diffLogProperties.getIgnoreGlobalFields().contains(valueChange.getOriginalName())) {
            return;
        }
        DiffField annotation = field.getAnnotation(DiffField.class);
        if (diffLogProperties.isCheckAnnotation() && annotation == null) {
            return;
        }
        String filedLogName = Optional.ofNullable(annotation).map(DiffField::name).orElse(valueChange.getPropertyName());
        String functionName = Optional.ofNullable(annotation).map(DiffField::function).orElse(null);
        DiffFieldStrategy strategy = Optional.ofNullable(annotation).map(DiffField::strategy).orElse(DiffFieldStrategy.ALWAYS);
        String logContent = getFieldLogContent(valueChange, filedLogName, functionName, strategy);
        if (StrUtil.isBlank(logContent)) {
            return;
        }
        builder.append(logContent).append(diffLogProperties.getFieldSeparator());
    }


    public String getFieldLogContent(Change change, String filedLogName, String functionName, DiffFieldStrategy strategy) {
        if (change instanceof InitialValueChange node) {
            return diffLogProperties.formatAdd(filedLogName, getFunctionValue(node.getRight(), functionName));
        }
        if (change instanceof TerminalValueChange node) {
            return diffLogProperties.formatDeleted(filedLogName, getFunctionValue(node.getLeft(), functionName));
        }
        if (change instanceof ValueChange node) {
            if (strategy == DiffFieldStrategy.NOT_NULL && Objects.isNull(node.getRight())) {
                return "";
            }
            return diffLogProperties.formatUpdate(filedLogName, getFunctionValue(node.getLeft(), functionName), getFunctionValue(node.getRight(), functionName));
        }
        return "";
    }


    private Object getFunctionValue(Object value, String functionName) {
        if (StrUtil.isEmpty(functionName)) {
            return value;
        }
        return functionService.apply(functionName, value);
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
