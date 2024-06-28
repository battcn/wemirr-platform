package com.wemirr.framework.log.diff.utils;

import org.javers.core.Javers;
import org.javers.core.diff.Diff;

import java.util.Collection;
import java.util.Collections;

/**
 * @author levin
 */
@SuppressWarnings("all")
public class DiffUtils {

    private static Diff compareCollectionsSafely(Javers javers, Object source, Object target) {
        Collection oldVersion = Collections.emptyList();
        Collection currentVersion = Collections.emptyList();
        Class clazz = null;
        if (source != null) {
            oldVersion = (Collection) source;
            clazz = oldVersion.iterator().next().getClass();
        }
        if (target != null) {
            currentVersion = (Collection) target;
            clazz = currentVersion.iterator().next().getClass();
        }
        return javers.compareCollections(oldVersion, currentVersion, clazz);
    }


    public static Diff compare(Javers javers, Object source, Object target) {
        if (source instanceof Collection<?> || target instanceof Collection<?>) {
            return compareCollectionsSafely(javers, source, target);
        }
        return javers.compare(source, target);
    }

}
