package com.wemirr.framework.boot.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * @author Levin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GlobalExceptionHandler.class)
public @interface EnableGlobalException {
}
