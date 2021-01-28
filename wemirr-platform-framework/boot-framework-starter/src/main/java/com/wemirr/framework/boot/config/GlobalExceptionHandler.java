package com.wemirr.framework.boot.config;


import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.netflix.client.ClientException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.entity.enums.CommonError;
import com.wemirr.framework.commons.exception.CheckedException;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.UnexpectedTypeException;
import javax.validation.ValidationException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

/**
 * @author Levin
 * @since 2019-01-21
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired(required = false)
    private DiscoveryClient discoveryClient;


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Result<ResponseEntity<Void>>> jsonErrorHandler(Exception e) {
        HttpStatus defaultErrorResult = HttpStatus.OK;
        log.error("错误日志 - {}", e.getMessage());
        if (e instanceof CheckedException) {
            CheckedException exception = (CheckedException) e;
            return new ResponseEntity<>(Result.fail(exception.getCode(), exception.getMessage()), defaultErrorResult);
        } else if (e instanceof IllegalArgumentException) {
            IllegalArgumentException exception = (IllegalArgumentException) e;
            return new ResponseEntity<>(Result.fail(exception.getMessage()), defaultErrorResult);
        } else if (e instanceof MultipartException) {
            return new ResponseEntity<>(Result.fail("文件过大,请控制文件大小"), defaultErrorResult);
        } else if (e instanceof InternalAuthenticationServiceException) {
            InternalAuthenticationServiceException exception = (InternalAuthenticationServiceException) e;
            if (exception.getCause() instanceof SQLSyntaxErrorException) {
                return new ResponseEntity<>(Result.fail(exception.getCause().getMessage()), defaultErrorResult);
            }
            return new ResponseEntity<>(Result.fail(exception.getMessage()), defaultErrorResult);
        } else if (e instanceof RuntimeException) {
            log.warn("[RuntimeException]", e);
            RuntimeException exception = (RuntimeException) e;
            return new ResponseEntity<>(Result.fail(exception.getMessage()), defaultErrorResult);
        }
        return new ResponseEntity<>(Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), defaultErrorResult);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> unexpectedTypeException(UnexpectedTypeException e) {
        return Result.fail(e.getMessage());
    }

    private static final String DISABLED_TEXT = "User is disabled";


    @ExceptionHandler(OAuth2Exception.class)
    @ResponseBody
    public final ResponseEntity<Result> oAuth2Exception(OAuth2Exception e, HttpServletResponse response) {
        log.error("[错误内容]", e);
        if (e instanceof InvalidGrantException) {
            log.warn("[error message] - [{}]", e.getMessage());
            InvalidGrantException exception = (InvalidGrantException) e;
            if (exception.getMessage().contains(DISABLED_TEXT)) {
                return new ResponseEntity<>(Result.fail(e.getHttpErrorCode(), "用户已禁用"), HttpStatus.OK);
            }
            return new ResponseEntity<>(Result.fail(e.getHttpErrorCode(), "账号或密码错误"), HttpStatus.OK);
        }
        return new ResponseEntity<>(Result.fail(e.getHttpErrorCode(), e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> accessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        log.warn("\n[================================================================]\n" +
                "[异常信息] - [{}]\n" +
                "[请求地址] - [{}] - [{}]\n" +
                "[返回消息] - [{}]\n" +
                "[================================================================]", e.getLocalizedMessage(), method, uri, CommonError.ACCESS_DENIED.desc());
        return Result.fail(CommonError.ACCESS_DENIED);
    }


    @ExceptionHandler(InsufficientAuthenticationException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> insufficientAuthenticationException(InsufficientAuthenticationException e) {
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> duplicateKeyException(DuplicateKeyException e) {
        log.error("[主键冲突]", e);
        return Result.fail("数据主键冲突");
    }


    @ExceptionHandler(MyBatisSystemException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> myBatisSystemException(MyBatisSystemException e) {
        log.error("[Mybatis 系统异常]", e);
        if (e.getCause() instanceof PersistenceException) {
            if (e.getCause().getCause() instanceof MybatisPlusException) {
                if (e.getCause().getCause().getCause() instanceof SQLSyntaxErrorException) {
                    SQLSyntaxErrorException sqlSyntaxErrorException = (SQLSyntaxErrorException) e.getCause().getCause().getCause();
                    return Result.fail("SQL 异常,错误信息为 " + sqlSyntaxErrorException.getMessage());
                }
                return Result.fail(e.getLocalizedMessage());
            }
            return Result.fail(e.getLocalizedMessage());
        }
        return Result.fail(e.getLocalizedMessage());
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> badSqlGrammarException(BadSqlGrammarException e) {
        log.error("[Mybatis SQL 异常]", e);
        if (e.getCause() instanceof SQLSyntaxErrorException) {
            SQLSyntaxErrorException sqlSyntaxErrorException = (SQLSyntaxErrorException) e.getCause();
            return Result.fail("Mybatis SQL绑定异常,错误信息为 " + sqlSyntaxErrorException.getMessage());
        }
        return Result.fail(e.getLocalizedMessage());
    }

    @ExceptionHandler(MybatisPlusException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> mybatisPlusException(MybatisPlusException e) {
        System.out.println(e.getCause().getClass());
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(HystrixRuntimeException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> hystrixRuntimeException(HystrixRuntimeException e) {
        if (e.getCause() instanceof RuntimeException) {
            RuntimeException runtimeException = (RuntimeException) e.getCause();
            if (runtimeException.getCause() instanceof ClientException) {
                ClientException clientException = (ClientException) runtimeException.getCause();
                List<String> services = discoveryClient.getServices();
                for (String serviceId : services) {
                    String message = clientException.getMessage();
                    if (StringUtils.contains(message, serviceId)) {
                        log.warn("[服务访问失败] - [{}] - [错误信息] - [{}]", serviceId, message);
                        return Result.fail(CommonError.INNER_SERVICE_ERROR);
                    }
                }
            }
        }
        log.warn("[服务调用失败] - [{}]", e.getMessage());
        return Result.fail("服务响应失败，请稍后再试");
    }

    @ExceptionHandler(ClientException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> clientException(ClientException e) {
        log.error("内部服务响应失败", e);
        return Result.fail(CommonError.INNER_SERVICE_ERROR);
    }


//    @ExceptionHandler(ExcelAnalysisException.class)
//    @ResponseBody
//    public final Result<ResponseEntity<Void>> excelAnalysisException(ExcelAnalysisException e) {
//        log.error("Excel 解析失败", e);
//        if (e.getCause() instanceof CheckedException) {
//            CheckedException exception = (CheckedException) e.getCause();
//            return Result.fail(exception.getMessage());
//        } else if (e.getCause() instanceof ExcelDataConvertException) {
//            ExcelDataConvertException exception = (ExcelDataConvertException) e.getCause();
//            return Result.fail("导入失败,类型转换错误,请检查内容或者模板是否正确");
//        }
//        return Result.fail(e.getMessage());
//    }


    @ExceptionHandler(RetryableException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> retryableException(RetryableException e) {
        log.error("重试失败", e);
        return Result.fail(CommonError.INNER_SERVICE_ERROR);
    }


    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> handlerValidationException(final Exception e) {
        ValidationException exception = (ValidationException) e;
        if (exception.getCause() instanceof CheckedException) {
            return Result.fail(CommonError.REQUEST_PARAM_ERROR.type(), exception.getCause().getMessage());
        }
        return Result.fail(CommonError.REQUEST_PARAM_ERROR.type(), exception.getMessage());
    }


    /**
     * 通用的接口映射异常处理方法
     */
    @Override
    protected @ResponseBody
    ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                   HttpStatus status, WebRequest request) {
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            String message = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
            log.warn("[参数验证错误] - [{}] - [{}]", uri, message);
            return new ResponseEntity<>(Result.fail(CommonError.REQUEST_PARAM_ERROR.type(), message), HttpStatus.OK);
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException e = (HttpRequestMethodNotSupportedException) ex;
            final String method = e.getMethod();
            return new ResponseEntity<>(Result.fail("%s 请求方式 %s 不存在", uri, method), HttpStatus.OK);
        } else if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
            logger.error("参数转换失败，方法：" + exception.getParameter().getMethod().getName() + "，参数：" + exception.getName()
                    + ",信息：" + exception.getLocalizedMessage());
            if (ex.getCause() instanceof ConversionFailedException &&
                    ex.getCause().getCause() instanceof IllegalArgumentException) {
                return new ResponseEntity<>(Result.fail(ex.getCause().getCause().getLocalizedMessage()), HttpStatus.OK);
            }
            return new ResponseEntity<>(Result.fail("表单填写错误"), HttpStatus.OK);
        } else if (ex instanceof HttpMessageNotReadableException) {
            HttpMessageNotReadableException e = (HttpMessageNotReadableException) ex;
            logger.error("参数转换失败" + ex.getLocalizedMessage());
            if (e.getCause() instanceof InvalidFormatException) {
                InvalidFormatException invalid = (InvalidFormatException) e.getCause();
                return new ResponseEntity<>(Result.fail("字段类型映射错误 " + invalid.getMessage()), HttpStatus.OK);
            }
        } else if (ex instanceof NoHandlerFoundException) {
            NoHandlerFoundException e = (NoHandlerFoundException) ex;
            logger.error("地址错误" + e.getLocalizedMessage());
            return new ResponseEntity<>(Result.fail("地址错误 " + e.getMessage()), HttpStatus.OK);
        }
        final String contextPath = request.getContextPath();
        logger.error("参数转换失败 - [" + contextPath + "]", ex);
        return new ResponseEntity<>(Result.fail("表单填写错误"), HttpStatus.OK);
    }
}