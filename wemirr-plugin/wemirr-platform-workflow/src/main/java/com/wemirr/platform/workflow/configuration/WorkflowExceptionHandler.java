package com.wemirr.platform.workflow.configuration;

import com.wemirr.framework.commons.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.dromara.warm.flow.core.exception.FlowException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 工作流异常捕捉,用于处理 FlowException
 *
 * @author battcn
 * @since 2025/7/24
 **/
@Slf4j
@RestControllerAdvice
public class WorkflowExceptionHandler {

    @ExceptionHandler(FlowException.class)
    public ResponseEntity<Result<?>> handleFlowException(FlowException e) {

        // 根据不同的流程异常类型返回不同的错误码和信息（可选）
        return ResponseEntity.ok(Result.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
}
