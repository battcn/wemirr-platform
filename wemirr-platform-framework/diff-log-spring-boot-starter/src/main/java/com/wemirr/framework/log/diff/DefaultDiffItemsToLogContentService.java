package com.wemirr.framework.log.diff;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.log.diff.configuration.DiffLogProperties;
import com.wemirr.framework.log.diff.core.DiffFieldStrategy;
import com.wemirr.framework.log.diff.core.annotation.DiffField;
import com.wemirr.framework.log.diff.service.IFunctionService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.InitialValueChange;
import org.javers.core.diff.changetype.TerminalValueChange;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.metamodel.property.Property;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.lang.NonNull;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;

import static org.javers.core.diff.ListCompareAlgorithm.LEVENSHTEIN_DISTANCE;

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
    public String toLogContent(final Object source, final Object target) {
        Javers javers = JaversBuilder.javers().withListCompareAlgorithm(LEVENSHTEIN_DISTANCE).build();
        Diff diff = javers.compare(source, target);
        if (!diff.hasChanges()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Change change : diff.getChanges()) {
            processChangeNode(source != null ? source.getClass() : target.getClass(), builder, javers, change);
        }
        return builder.toString().replaceAll(diffLogProperties.getFieldSeparator().concat("$"), "");
    }

    private void processChangeNode(Class<?> clazz, StringBuilder builder, Javers javers, Change change) {
        if (!(change instanceof ValueChange valueChange)) {
            return;
        }
        Property property = javers.getProperty(valueChange);
        Field field = ReflectUtil.getField(clazz, property.getOriginalName());
        if (diffLogProperties.getIgnoreGlobalFields().contains(property.getOriginalName())) {
            return;
        }
        DiffField annotation = field.getAnnotation(DiffField.class);
        if (diffLogProperties.isCheckAnnotation() && annotation == null) {
            return;
        }
        String filedLogName = Optional.ofNullable(annotation).map(DiffField::name).orElse(property.getName());
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
