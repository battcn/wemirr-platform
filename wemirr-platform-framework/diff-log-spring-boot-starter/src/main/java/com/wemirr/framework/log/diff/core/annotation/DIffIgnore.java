package com.wemirr.framework.log.diff.core.annotation;

import java.lang.annotation.*;

/**
 * Ignore convert field
 *
 * @author wulang
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DIffIgnore {
}
