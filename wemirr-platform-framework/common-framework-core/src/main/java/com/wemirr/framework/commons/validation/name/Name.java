package com.wemirr.framework.commons.validation.name;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Levin
 * @since 2020/9/21
 */
@Target({
        METHOD,
        FIELD,
        ANNOTATION_TYPE,
        CONSTRUCTOR,
        PARAMETER
})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {NameValidator.class})
public @interface Name {


    String message() default "不能输入纯数字和特殊字符";

    String regexp() default "^(?![0-9]*$)[\\u4e00-\\u9fa50-9A-Za-z-_()（）]*$";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
