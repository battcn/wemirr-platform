package com.wemirr.framework.db.mybatisplus.intercept.data;

import java.lang.annotation.*;

/**
 * @author Levin
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataColumn {

    String alias() default "";

    String name();


}
