package com.battcn.management.consumer.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * 定义Api返回结果实体类</p>
 *
 * @author Levin
 * @date 2018-03-08
 */
@ApiModel(value = "ApiResult", description = "接口返回结果")
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = 5657829410109424730L;
    private static final String OPERATION_SUCCESS = "操作成功!";
    private static final String OPERATION_FAILURE = "操作失败!";

    /**
     * 消息id
     */
    @ApiModelProperty(value = "消息id")
    private int messageId;
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String message;
    /**
     * 时间戳：Date 类型
     */
    @ApiModelProperty(value = "时间戳")
    private Date timestamp;
    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据")
    private T data;

    private ApiResult(Builder<T> builder) {
        this.messageId = builder.messageId;
        this.message = builder.message;
        this.timestamp = builder.timestamp;
        this.data = builder.data;
    }

    public static ApiResult<String> SUCCESS = getResponse(HttpStatus.OK.value(), OPERATION_SUCCESS, null);


    public static <T> ApiResult<T> getSuccess(T data) {
        return getResponse(HttpStatus.OK.value(), OPERATION_SUCCESS, data);
    }

    public static <T> ApiResult<T> getSuccess(String message, T data) {
        return getResponse(HttpStatus.OK.value(), message, data);
    }

    public static <T> ApiResult<T> getSuccess(String message) {
        return getSuccess(message, null);
    }

    public static ApiResult<String> getSuccess(int messageId) {
        return getSuccess(messageId, OPERATION_SUCCESS);
    }

    public static ApiResult<String> getSuccess(int messageId, String message) {
        return getSuccess(messageId, message, null);
    }

    public static <T> ApiResult<T> getSuccess(int messageId, T data) {
        return getResponse(messageId, OPERATION_SUCCESS, data);
    }

    public static <T> ApiResult<T> getSuccess(int messageId, String message, T data) {
        return getResponse(messageId, message, data);
    }

    public static <T> ApiResult<T> getFailure(String message, T data) {
        return getResponse(HttpStatus.BAD_REQUEST.value(), message, data);
    }

    public static <T> ApiResult<T> getFailure(String message) {
        return getFailure(message, null);
    }

    public static ApiResult<?> getFailure(int messageId, String message) {
        return getResponse(messageId, message, null);
    }

    public static <T> ApiResult<T> getFailure(int messageId, String message, T data) {
        return getResponse(messageId, message, data);
    }

    public static <T> ApiResult<T> getResponse(Boolean flag) {
        return flag ? getSuccess(OPERATION_SUCCESS) : getFailure(OPERATION_FAILURE);
    }

    public static <T> ApiResult<T> getResponse(Boolean flag, String message) {
        return flag ? getSuccess(message) : getFailure(message);
    }

    public static <T> ApiResult<T> getResponse(int messageId, String message, T data) {
        return new Builder<T>(messageId, new Date()).message(message).data(data).build();
    }

    public static class Builder<T> {
        private int messageId;
        private Date timestamp;
        private String message;
        private T data;

        public Builder(int messageId, Date timestamp) {
            this.messageId = messageId;
            this.timestamp = timestamp;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        @SuppressWarnings("unchecked")
        public Builder<T> data(T data) {
            this.data = data == null ? (T) new JSONObject() : data;
            return this;
        }

        public ApiResult<T> build() {
            return new ApiResult<>(this);
        }
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}