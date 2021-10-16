
package com.wemirr.framework.commons.entity.validator;


import com.wemirr.framework.commons.exception.CheckedException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 * <p>
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author Levin
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    /**
     * @param object 待校验对象
     * @param groups 待校验的组
     */
    public static <T> void validateEntity(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<T> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append("\n");
            }
            throw new CheckedException(msg.toString());
        }
    }
}
