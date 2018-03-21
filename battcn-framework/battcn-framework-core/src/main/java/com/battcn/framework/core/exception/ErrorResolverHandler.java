package com.battcn.framework.core.exception;

import com.battcn.framework.core.exception.properties.ErrorMvcProperties;
import com.battcn.framework.exception.CustomException;
import com.battcn.framework.exception.ErrorResponseEntity;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.*;
import java.util.function.Supplier;

import static org.springframework.util.CollectionUtils.toIterator;

/**
 * @author Levin
 * @since 2018/1/17 0017
 */
@Data
@Slf4j
@RestControllerAdvice
class ErrorResolverHandler {

    private static final CharSequence JAVA = "java";

    private final ErrorMvcProperties errorMvcProperties;

    @Autowired
    public ErrorResolverHandler(ErrorMvcProperties errorMvcProperties) {
        this.errorMvcProperties = errorMvcProperties;
    }

    @ExceptionHandler(BindException.class)
    public final ErrorResponseEntity handlerBindException(final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        BindException exception = (BindException) e;
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String errorMsg = errorFieldToString(fieldErrors);
        return new ErrorResponseEntity(response.getStatus(), errorMsg);
    }

    /**
     * 捕获自定义异常
     *
     * @param request  request对象
     * @param e        异常信息
     * @param response response 响应对象
     * @return 返回信息
     */
    @ExceptionHandler(CustomException.class)
    public final ErrorResponseEntity handlerCustomException(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        logError(request, e);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        CustomException exception = (CustomException) e;
        return exception.toErrorResponseEntity();
    }

    @ExceptionHandler({IllegalArgumentException.class, MultipartException.class})
    public final ErrorResponseEntity handlerMultipartAndIllegalArgumentException(HttpServletRequest request,
                                                                                 final Exception e, HttpServletResponse response) {
        logError(request, e);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        String message = getErrorMvcProperties().getDefaultMessage();
        if (e instanceof MultipartException) {
            message = "文件内容过大,请重新上传";
        } else if (e instanceof IllegalArgumentException) {
            message = e.getMessage();
        }
        return new ErrorResponseEntity(response.getStatus(), message);
    }


    /**
     * 自定义验证抛出的异常信息
     *
     * @param request  request对象
     * @param e        异常信息
     * @param response response 响应对象
     * @return 返回信息
     */
    @ExceptionHandler(ValidationException.class)
    public final ErrorResponseEntity handlerValidationException(HttpServletRequest request,
                                                                final Exception e, HttpServletResponse response) {
        logError(request, e);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        ValidationException exception = (ValidationException) e;
        if (exception.getCause() instanceof CustomException) {
            return new ErrorResponseEntity(HttpStatus.BAD_REQUEST.value(), exception.getCause().getMessage());
        }
        return new ErrorResponseEntity(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ErrorResponseEntity handlerMethodArgumentNotValidException(HttpServletRequest request,
                                                                            final Exception e, HttpServletResponse response) {
        logError(request, e);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String defaultMsg = "参数绑定异常";
        if (!StringUtils.isEmpty(fieldErrors)) {
            defaultMsg = fieldErrors.get(0).getDefaultMessage();
        }
        return new ErrorResponseEntity(response.getStatus(), defaultMsg);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ErrorResponseEntity handlerConstraintViolationException(final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        ConstraintViolationException exception = (ConstraintViolationException) e;
        Set<ConstraintViolation<?>> set = exception.getConstraintViolations();
        StringJoiner stringJoiner = new StringJoiner(";");
        for (ConstraintViolation constraintViolation : set) {
            stringJoiner.add(constraintViolation.getMessage());
        }
        String errorMsg = stringJoiner.toString();
        return new ErrorResponseEntity(response.getStatus(), errorMsg);
    }

    private String errorFieldToString(List<FieldError> fieldErrors) {
        StringJoiner stringJoiner = new StringJoiner(";");
        for (FieldError fieldError : fieldErrors) {
            String defaultMsg = fieldError.getDefaultMessage();
            //参数绑定异常前端传参错误会把castException抛出来 统一改为 参数绑定异常
            if (StringUtils.isEmpty(defaultMsg) || defaultMsg.contains(JAVA)) {
                defaultMsg = "参数绑定异常";
            }
            stringJoiner.add(defaultMsg);
        }
        return stringJoiner.toString();
    }


    @ExceptionHandler({MySQLIntegrityConstraintViolationException.class,
            HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class,
            NoHandlerFoundException.class, MissingServletRequestParameterException.class
    })
    public final ErrorResponseEntity handlerDuplicateKeyException(final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        String errorMsg = "服务不可用";
        if (e instanceof MySQLIntegrityConstraintViolationException) {
            errorMsg = "主键或索引不唯一";
        }
        if (e instanceof HttpMessageNotReadableException) {
            errorMsg = "请输入请求参数";
        }
        if (e instanceof HttpRequestMethodNotSupportedException) {
            errorMsg = "HTTP请求方法错误";
        }
        if (e instanceof NoHandlerFoundException) {
            errorMsg = "请求路由不存在";
        }
        if (e instanceof MissingServletRequestParameterException) {
            errorMsg = "缺少必填参数";
        }
        log.warn("client exception  no need to handle", e);
        return new ErrorResponseEntity(response.getStatus(), errorMsg);
    }

    @ExceptionHandler(Throwable.class)
    public final ErrorResponseEntity handleException(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        String errorMsg = getErrorMvcProperties().getDefaultMessage();
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        ResponseStatus status = e.getClass().getAnnotation(ResponseStatus.class);
        if (status != null) {
            code = status.code().value();
            response.setStatus(code);
            if (!StringUtils.isEmpty(status.reason())) {
                errorMsg = status.reason();
            } else if (!StringUtils.isEmpty(e.getLocalizedMessage())) {
                errorMsg = e.getLocalizedMessage();
            }
        }
        if (code >= HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            if (code == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
            logError(request, e);
        }
        return new ErrorResponseEntity(code, errorMsg);
    }

    private void logError(HttpServletRequest request, Exception e) {
        StringBuilder messageBuilder = new StringBuilder("Unhandled exception in request:\n");
        messageBuilder.append(request.getMethod());
        messageBuilder.append(" ");
        messageBuilder.append(request.getRequestURI());
        messageBuilder.append("\nHeader:");
        toIterable(request::getHeaderNames).forEach(name -> {
            messageBuilder.append("\n");
            String value = request.getHeader(name);
            messageBuilder.append(name);
            messageBuilder.append(": ");
            messageBuilder.append(value);
        });
        messageBuilder.append("\n");
        if (request.getParameterMap().size() > 0) {
            StringBuilder parameters = getRequestParameters(request);
            messageBuilder.append(parameters);
        }
        log.error(messageBuilder.toString(), e);
    }

    private StringBuilder getRequestParameters(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder("request parameters:");
        stringBuilder.append("\n");
        final Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((k, v) -> {
            stringBuilder.append(k);
            stringBuilder.append(": ");
            stringBuilder.append(Arrays.toString(v));
            stringBuilder.append("\n");
        });
        return stringBuilder;
    }

    private <T> Iterable<T> toIterable(Supplier<Enumeration<T>> enumerationSupplier) {
        return () -> toIterator(enumerationSupplier.get());
    }
}
