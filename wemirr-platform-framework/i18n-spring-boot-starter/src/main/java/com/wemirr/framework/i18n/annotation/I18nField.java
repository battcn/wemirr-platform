package com.wemirr.framework.i18n.annotation;


import java.lang.annotation.*;

/**
 * 用于标注在需要国际化的 String 类型的属性上，用于标记其需要国际化。 必须在拥有 {@link I18nMethod} 注解标记的类上
 *
 * @author Levin
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface I18nField {

    /**
     * i18n 关键字
     *
     * @return String
     */
    String code() default "";

    /**
     * 映射的字段
     * 比如 type 映射到 typeLabel
     *
     * @return String
     */
    String target() default "";
}