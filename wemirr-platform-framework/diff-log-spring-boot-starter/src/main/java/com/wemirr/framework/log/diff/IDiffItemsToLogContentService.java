package com.wemirr.framework.log.diff;

import com.wemirr.framework.log.diff.domain.FieldChange;

import java.util.List;

/**
 * 差异内容转换服务接口
 *
 * @author Levin
 */
public interface IDiffItemsToLogContentService {

    /**
     * 转换为日志文本内容
     *
     * @param source 来源对象
     * @param target 目标对象
     * @return 格式化的日志内容
     */
    String toLogContent(final Object source, final Object target);

    /**
     * 转换为字段变更列表
     *
     * @param source 来源对象
     * @param target 目标对象
     * @return 字段变更记录列表
     */
    List<FieldChange> toFieldChanges(final Object source, final Object target);
}
