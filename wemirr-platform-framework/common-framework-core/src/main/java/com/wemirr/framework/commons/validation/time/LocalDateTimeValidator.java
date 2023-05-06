package com.wemirr.framework.commons.validation.time;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.format.DateTimeFormatter;

/**
 * 日期格式验证
 *
 * @author Levin
 * @version 1.0.0
 * @since 2020-06-06
 */
public class LocalDateTimeValidator implements ConstraintValidator<LocalDateTime, java.time.LocalDateTime> {

    private LocalDateTime localDateTime;

    @Override
    public void initialize(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean isValid(java.time.LocalDateTime value, ConstraintValidatorContext context) {
        // 如果 value 为空则不进行格式验证，为空验证可以使用 @NotBlank @NotNull @NotEmpty 等注解来进行控制，职责分离
        if (value == null) {
            return true;
        }
        try {
            value.format(DateTimeFormatter.ofPattern(localDateTime.format()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}