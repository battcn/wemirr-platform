/*
 * Copyright (c) 2023 WEMIRR-PLATFORM Authors. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wemirr.framework.boot.base;

import com.baomidou.dynamic.datasource.exception.CannotFindDataSourceException;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.entity.enums.CommonError;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.i18n.core.I18nMessageResource;
import feign.RetryableException;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.UnexpectedTypeException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Objects;

/**
 * @author Levin
 * @since 2019-01-21
 */
@Slf4j
@Configuration
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Resource
    private I18nMessageResource i18nMessageResource;
    
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Result<ResponseEntity<Void>>> jsonErrorHandler(HttpServletRequest request, Exception e) {
        HttpStatus defaultErrorResult = HttpStatus.OK;
        log.error("错误日志 - {} - {}", request.getRequestURI(), e.getLocalizedMessage());
        if (e instanceof CheckedException exception) {
            return new ResponseEntity<>(Result.fail(exception.getCode(), i18nMessageResource.getMessage(exception.getMessage(), exception.getArgs())), defaultErrorResult);
        } else if (e instanceof IllegalArgumentException exception) {
            return new ResponseEntity<>(Result.fail(exception.getMessage()), defaultErrorResult);
        } else if (e instanceof MultipartException) {
            return new ResponseEntity<>(Result.fail(i18nMessageResource.getMessage("global.exception.file-too-large")), defaultErrorResult);
        } else if (e instanceof InternalAuthenticationServiceException exception) {
            log.error("InternalAuthenticationServiceException", exception);
            if (exception.getCause() instanceof SQLSyntaxErrorException) {
                return new ResponseEntity<>(Result.fail(exception.getCause().getMessage()), defaultErrorResult);
            }
            if (e.getCause() instanceof MyBatisSystemException) {
                if (e.getCause().getCause() instanceof PersistenceException) {
                    if (e.getCause().getCause().getCause().getCause()instanceof SQLSyntaxErrorException sqlSyntaxErrorException) {
                        return new ResponseEntity<>(Result.fail("未找到数据源" + sqlSyntaxErrorException.getMessage()), defaultErrorResult);
                    }
                }
                if (e.getCause().getCause().getCause()instanceof CannotFindDataSourceException sourceException) {
                    return new ResponseEntity<>(Result.fail("未找到数据源" + sourceException.getMessage()), defaultErrorResult);
                }
                return new ResponseEntity<>(Result.fail("SQL 异常,错误信息为 " + e.getCause().getMessage()), defaultErrorResult);
            }
            return new ResponseEntity<>(Result.fail(exception.getMessage()), defaultErrorResult);
        } else if (e instanceof RuntimeException exception) {
            log.error("异常信息", exception);
            return new ResponseEntity<>(Result.fail(exception.getMessage()), defaultErrorResult);
        }
        return new ResponseEntity<>(Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), defaultErrorResult);
    }
    
    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> unexpectedTypeException(UnexpectedTypeException e) {
        return Result.fail(e.getMessage());
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> accessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        log.warn("""
                
                [================================================================]
                [异常信息] - [{}]
                [请求地址] - [{}] - [{}]
                [返回消息] - [{}]
                [================================================================]""", e.getLocalizedMessage(), method, uri, CommonError.ACCESS_DENIED.desc());
        return Result.fail(CommonError.ACCESS_DENIED);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> dataIntegrityViolationException(DataIntegrityViolationException e) {
        log.warn("""
                
                [================================================================]
                [异常信息] - [{}]
                [================================================================]""", e.getLocalizedMessage());
        if (e.getCause()instanceof SQLException exception) {
            return Result.fail(exception.getMessage());
        }
        return Result.fail(e.getMessage());
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
        return Result.fail(i18nMessageResource.getMessage("global.exception.duplicate-key"));
    }
    
    @ExceptionHandler(MyBatisSystemException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> myBatisSystemException(MyBatisSystemException e) {
        log.error("[Mybatis 系统异常]", e);
        if (e.getCause() instanceof PersistenceException) {
            if (e.getCause().getCause() instanceof MybatisPlusException) {
                if (e.getCause().getCause().getCause()instanceof SQLSyntaxErrorException sqlSyntaxErrorException) {
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
        if (e.getCause()instanceof SQLSyntaxErrorException sqlSyntaxErrorException) {
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
        if (exception.getCause()instanceof CheckedException ex1) {
            return Result.fail(CommonError.REQUEST_PARAM_ERROR.type(), i18nMessageResource.getMessage(ex1.getMessage(), ex1.getArgs()));
        }
        return Result.fail(CommonError.REQUEST_PARAM_ERROR.type(), exception.getMessage());
    }
    
    /**
     * 通用的接口映射异常处理方法
     */
    @ResponseBody
    protected ResponseEntity<Object> handleExceptionInternal(@Nonnull Exception ex, Object body, @Nonnull HttpHeaders headers, @Nonnull HttpStatusCode statusCode, @Nonnull WebRequest request) {
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        if (ex instanceof MethodArgumentNotValidException e) {
            String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
            log.warn("[参数验证错误] - [{}] - [{}]", uri, message);
            return new ResponseEntity<>(Result.fail(CommonError.REQUEST_PARAM_ERROR.type(), message), HttpStatus.OK);
        } else if (ex instanceof HttpRequestMethodNotSupportedException e) {
            final String method = e.getMethod();
            return new ResponseEntity<>(Result.fail("%s 请求方式 %s 不存在", uri, method), HttpStatus.OK);
        } else if (ex instanceof MethodArgumentTypeMismatchException exception) {
            logger.error("参数转换失败，方法：" + Objects.requireNonNull(exception.getParameter().getMethod()).getName() + "，参数：" + exception.getName()
                    + ",信息：" + exception.getLocalizedMessage());
            if (ex.getCause() instanceof ConversionFailedException &&
                    ex.getCause().getCause() instanceof IllegalArgumentException) {
                return new ResponseEntity<>(Result.fail(ex.getCause().getCause().getLocalizedMessage()), HttpStatus.OK);
            }
            return new ResponseEntity<>(Result.fail("表单填写错误"), HttpStatus.OK);
        } else if (ex instanceof HttpMessageNotReadableException e) {
            logger.error("参数转换失败" + ex.getLocalizedMessage());
            if (e.getCause()instanceof InvalidFormatException invalid) {
                return new ResponseEntity<>(Result.fail("字段类型映射错误 " + invalid.getMessage()), HttpStatus.OK);
            }
        } else if (ex instanceof NoHandlerFoundException e) {
            logger.error("地址错误" + e.getLocalizedMessage());
            return new ResponseEntity<>(Result.fail("地址错误 " + e.getMessage()), HttpStatus.OK);
        }
        final String contextPath = request.getContextPath();
        logger.error("参数转换失败 - [" + contextPath + "]", ex);
        return new ResponseEntity<>(Result.fail("表单填写错误"), HttpStatus.OK);
    }
}