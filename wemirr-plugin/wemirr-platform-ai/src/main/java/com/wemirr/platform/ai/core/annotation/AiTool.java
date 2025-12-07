package com.wemirr.platform.ai.core.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author xiao1
 * @date 2025-12
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface AiTool {

    /**
     * 工具名称/别名
     */
    String name() default "";

    /**
     * 工具描述
     */
    String description() default "";

    /**
     * 工具图标
     */
    String icon() default "";
}
