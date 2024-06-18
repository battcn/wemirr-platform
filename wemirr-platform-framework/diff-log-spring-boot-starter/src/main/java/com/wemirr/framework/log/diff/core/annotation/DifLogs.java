package com.wemirr.framework.log.diff.core.annotation;

import java.lang.annotation.*;

/**
 * @author wulang
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DifLogs {

    /**
     * 日志 @DiffLog
     *
     * @return DiffLog[]
     */
    DiffLog[] value();
}
