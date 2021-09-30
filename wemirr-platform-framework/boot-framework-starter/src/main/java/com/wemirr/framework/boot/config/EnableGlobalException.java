package com.wemirr.framework.boot.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * extend.exception.global.enabled: true
 *
 * @author Levin
 */
@Deprecated
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GlobalExceptionHandler.class)
public @interface EnableGlobalException {
}
