package com.wemirr.framework.mongodb.annotation;

import java.lang.annotation.*;

/**
 * @author Levin
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicMongo {

}
