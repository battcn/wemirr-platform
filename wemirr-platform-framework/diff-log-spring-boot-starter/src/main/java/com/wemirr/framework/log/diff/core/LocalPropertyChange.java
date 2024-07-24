package com.wemirr.framework.log.diff.core;

import com.wemirr.framework.log.diff.domain.enums.ChangeAction;
import lombok.Getter;
import org.javers.common.string.PrettyValuePrinter;
import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.core.diff.changetype.Atomic;
import org.javers.core.diff.changetype.PropertyChange;
import org.javers.core.diff.changetype.PropertyChangeMetadata;

import java.io.Serial;
import java.util.Objects;

/**
 * @author dax
 * @since 2024/6/21
 */
public class LocalPropertyChange extends PropertyChange<Object> {

    @Serial
    private static final long serialVersionUID = 1L;
    @Getter
    private final PropertyChange<?> propertyChange;
    private final Atomic left;
    private final Atomic right;
    @Getter
    private final String originalName;
    @Getter
    private final Class<?> className;

    @Getter
    private final ChangeAction action;


    private LocalPropertyChange(Javers javers, PropertyChange<?> propertyChange) {
        super(new PropertyChangeMetadata(propertyChange.getAffectedGlobalId(),
                propertyChange.getPropertyName(),
                propertyChange.getCommitMetadata(),
                propertyChange.getChangeType()
        ));
        this.propertyChange = propertyChange;
        this.left = new Atomic(propertyChange.getLeft());
        this.right = new Atomic(propertyChange.getRight());
        this.originalName = javers.getProperty(propertyChange).getOriginalName();
        this.className = propertyChange.getAffectedObject().<Class<?>>map(Object::getClass).orElse(null);
        if (propertyChange.isPropertyAdded()) {
            action = ChangeAction.ADDED;
        } else if (propertyChange.isPropertyRemoved()) {
            action = ChangeAction.REMOVED;
        } else {
            action = ChangeAction.UPDATED;
        }
    }

    public static Change wrap(Javers javers, PropertyChange<?> propertyChange) {
        return new LocalPropertyChange(javers, propertyChange);
    }

    @Override
    public Object getLeft() {
        return propertyChange.getLeft();
    }

    @Override
    public Object getRight() {
        return propertyChange.getRight();
    }

    @Override
    public String prettyPrint(PrettyValuePrinter valuePrinter) {
        String propertyNameWithPath = propertyChange.getPropertyNameWithPath();
        if (!Objects.equals(this.getPropertyName(), originalName)) {
            propertyNameWithPath += "（" + originalName + "）";
        }
        if (propertyChange.isPropertyAdded()) {
            return valuePrinter.formatWithQuotes(propertyNameWithPath) +
                    " 属性以及值 " + valuePrinter.formatWithQuotes(right.unwrap()) + " 已添加";
        } else if (propertyChange.isPropertyRemoved()) {
            return valuePrinter.formatWithQuotes(propertyNameWithPath) +
                    " 属性以及值 " + valuePrinter.formatWithQuotes(left.unwrap()) + " 被移除";
        } else {
            if (left.isNull()) {
                return valuePrinter.formatWithQuotes(propertyNameWithPath) +
                        " 赋值为 " + valuePrinter.formatWithQuotes(getRight());
            } else if (right.isNull()) {
                return valuePrinter.formatWithQuotes(propertyNameWithPath) +
                        " 值 " + valuePrinter.formatWithQuotes(getLeft()) + " 未变动";
            } else {
                return valuePrinter.formatWithQuotes(propertyNameWithPath) +
                        " 由 " + valuePrinter.formatWithQuotes(getLeft()) + " 变更为 " +
                        valuePrinter.formatWithQuotes(getRight());
            }
        }
    }
}
