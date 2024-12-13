package com.wemirr.platform.suite.file.exception;

import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.i18n.core.I18nMessageResource;
import jakarta.annotation.Resource;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author xiao1
 * @date 2024-12
 */
@Slf4j
@Configuration
@ControllerAdvice
public class GlobalFileExceptionHandler  extends ResponseEntityExceptionHandler {
    @Resource
    private I18nMessageResource i18nMessageResource;

    @ExceptionHandler(FileException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> handlerValidationException(final Exception e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        FileException exception = (FileException) e;
        if (exception.getCause() instanceof FileException ex1) {
            return Result.fail(httpStatus.value(), i18nMessageResource.getMessage(ex1.getMessage()));
        }
        return Result.fail(httpStatus.value(), exception.getMessage());
    }
}
