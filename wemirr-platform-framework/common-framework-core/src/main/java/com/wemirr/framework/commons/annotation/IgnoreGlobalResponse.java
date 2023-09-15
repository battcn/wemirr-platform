package com.wemirr.framework.commons.annotation;

import java.lang.annotation.*;

/**
 * @author Levin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreGlobalResponse {


    /**
     * 描述
     *
     * @return {String}
     */
    String description();

}
