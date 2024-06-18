package com.wemirr.framework.log.diff;

import de.danielbechler.diff.node.DiffNode;

/**
 * @author Levin
 */
public interface IDiffItemsToLogContentService {

    String toLogContent(DiffNode diffNode, final Object o1, final Object o2);
}
