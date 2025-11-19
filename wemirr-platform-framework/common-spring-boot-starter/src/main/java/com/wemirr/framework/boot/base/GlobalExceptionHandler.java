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

import cn.dev33.satoken.context.SaHolder;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.i18n.core.I18nMessageResource;
import com.wemirr.framework.redis.plus.exception.RedisLockException;
import feign.RetryableException;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.UnexpectedTypeException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Objects;

/**
 * @author Levin
 * @since 2019-01-21
 */
@Slf4j
@Order(10)
@Configuration
@ControllerAdvice
public class GlobalExceptionHandler {
    @Resource
    private I18nMessageResource i18nMessageResource;
    @Resource
    private HttpServletRequest request;

    @ResponseBody
    @ExceptionHandler(value = MultipartException.class)
    public Result<ResponseEntity<Void>> handlerException(MultipartException e) {
        return Result.fail(i18nMessageResource.getMessage("global.exception.file-too-large"));
    }

    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)
    public Result<ResponseEntity<Void>> nullPointerException(NullPointerException e) {
        log.error("null exception => http request uri => {},message => {}", SaHolder.getRequest().getUrl(), e.getLocalizedMessage());
        return Result.fail(e.getLocalizedMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = ServletException.class)
    public Result<ResponseEntity<Void>> servletException(ServletException e) {
        log.error("servlet exception => http request uri => {},message => {}", SaHolder.getRequest().getUrl(), e.getLocalizedMessage());
        return Result.fail(e.getLocalizedMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = RedisLockException.class)
    public Result<ResponseEntity<Void>> redisLockException(RedisLockException e) {
        log.error("redis lock exception => http request uri => {},message => {}", SaHolder.getRequest().getUrl(), e.getLocalizedMessage());
        return Result.fail(e.getLocalizedMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = CheckedException.class)
    public Result<ResponseEntity<Void>> handlerException(CheckedException e) {
        String message = i18nMessageResource.getMessage(e.getMessage(), e.getArgs());
        log.error("check exception => http request uri => {},message => {}", SaHolder.getRequest().getUrl(), message);
        return Result.fail(e.getCode(), message);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> unexpectedTypeException(UnexpectedTypeException e) {
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> dataIntegrityViolationException(DataIntegrityViolationException e) {
        log.warn("""
                
                [================================================================]
                [异常信息] - [{}]
                [================================================================]""", e.getLocalizedMessage());
        if (e.getCause() instanceof SQLException exception) {
            return Result.fail(exception.getMessage());
        }
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
                if (e.getCause().getCause().getCause() instanceof SQLSyntaxErrorException sqlSyntaxErrorException) {
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
        if (e.getCause() instanceof SQLSyntaxErrorException sqlSyntaxErrorException) {
            return Result.fail("Mybatis SQL绑定异常,错误信息为 " + sqlSyntaxErrorException.getMessage());
        }
        return Result.fail(e.getLocalizedMessage());
    }

    @ResponseBody
    @ExceptionHandler(MybatisPlusException.class)
    public final Result<ResponseEntity<Void>> mybatisPlusException(MybatisPlusException e) {
        log.error("mybatis-plus 操作异常", e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(RetryableException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> retryableException(RetryableException e) {
        log.error("重试失败", e);
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return Result.fail(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public final Result<ResponseEntity<Void>> handlerValidationException(final Exception e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ValidationException exception = (ValidationException) e;
        if (exception.getCause() instanceof CheckedException ex1) {
            return Result.fail(httpStatus.value(), i18nMessageResource.getMessage(ex1.getMessage(), ex1.getArgs()));
        }
        return Result.fail(httpStatus.value(), exception.getMessage());
    }

    /**
     * 通用的接口映射异常处理方法
     */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleException(Exception ex) {
        if (ex instanceof MethodArgumentNotValidException e) {
            String uri = request.getRequestURI();
            String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
            log.warn("[参数验证错误] - [{}] - [{}]", uri, message);
            return new ResponseEntity<>(Result.fail(HttpStatus.BAD_REQUEST.value(), message), HttpStatus.OK);
        } else if (ex instanceof HttpRequestMethodNotSupportedException e) {
            final String method = e.getMethod();
            String uri = request.getRequestURI();
            return new ResponseEntity<>(Result.fail("%s 请求方式 %s 不存在", uri, method), HttpStatus.OK);
        } else if (ex instanceof MethodArgumentTypeMismatchException exception) {
            log.error("参数转换失败，方法：{}，参数：{},信息：{}", Objects.requireNonNull(exception.getParameter().getMethod()).getName(), exception.getName(), exception.getLocalizedMessage());
            if (ex.getCause() instanceof ConversionFailedException &&
                    ex.getCause().getCause() instanceof IllegalArgumentException) {
                return new ResponseEntity<>(Result.fail(ex.getCause().getCause().getLocalizedMessage()), HttpStatus.OK);
            }
            return new ResponseEntity<>(Result.fail("表单填写错误"), HttpStatus.OK);
        } else if (ex instanceof HttpMessageNotReadableException e) {
            log.error("参数转换失败{}", ex.getLocalizedMessage());
            if (e.getCause() instanceof InvalidFormatException invalid) {
                return new ResponseEntity<>(Result.fail("字段类型映射错误 " + invalid.getMessage()), HttpStatus.OK);
            }
        } else if (ex instanceof NoHandlerFoundException e) {
            log.error("地址错误{}", e.getLocalizedMessage());
            return new ResponseEntity<>(Result.fail("地址错误 " + e.getMessage()), HttpStatus.OK);
        }
        final String contextPath = request.getContextPath();
        log.error("系统异常 - [{}]", contextPath, ex);
        return new ResponseEntity<>(Result.fail("系统异常：" + ex.getLocalizedMessage()), HttpStatus.OK);
    }
}