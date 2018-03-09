package com.battcn.management.consumer.config;

import com.battcn.framework.exception.CustomException;
import com.battcn.management.consumer.util.ApiResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:GrobalExceptionHandler <br/>
 *
 * @author Levin
 * @version 1.0
 * @see
 * @since 2017年06月15日
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult jsonErrorHandler(Exception e, HttpServletResponse rep) throws Exception {
        logger.error("出错啦", e);
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            rep.setStatus(400);
            return ApiResult.getFailure(rep.getStatus(), exception.getMessage());
        } else if (e instanceof CustomException) {
            CustomException exception = (CustomException) e;
            rep.setStatus(exception.getCode());
            return ApiResult.getFailure(exception.getCode(), exception.getMessage());
        } else if (e instanceof IllegalArgumentException) {
            IllegalArgumentException exception = (IllegalArgumentException) e;
            rep.setStatus(HttpStatus.BAD_REQUEST.value());
            return ApiResult.getFailure(rep.getStatus(), exception.getMessage());
        } else if (e instanceof MultipartException) {
            rep.setStatus(HttpStatus.BAD_REQUEST.value());
            return ApiResult.getFailure(rep.getStatus(), "图片大小不能超过3M");
        }
        rep.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ApiResult.getFailure(rep.getStatus(), e.getMessage());
    }

    /**
     * 通用的接口映射异常处理方法
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        logger.error("出错啦", ex);
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            return new ResponseEntity<>(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage(), status);
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
            logger.error("参数转换失败，方法：" + exception.getParameter().getMethod().getName() + "，参数：" + exception.getName()
                    + ",信息：" + exception.getLocalizedMessage());
            return new ResponseEntity<>("参数转换失败", status);
        }
        return new ResponseEntity<>("参数转换失败", status);
    }
}
