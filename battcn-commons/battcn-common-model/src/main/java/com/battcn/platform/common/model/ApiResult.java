package com.battcn.platform.common.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 定义Api返回结果实体类
 * </p>
 *
 * @author 唐亚峰
 * @date 2016-06-30 12:25
 */
@ApiModel(value = "Response", description = "接口返回结果")
public class ApiResult<T> implements Serializable {
	
	private static final long serialVersionUID = 8860500251143389894L;
	/**
     * 消息id
     */
    @ApiModelProperty(value = "消息id")
    private int messageId;
    /**
     * 消息状态：-1-会话失效、1-成功、其他-失败
     */
    @ApiModelProperty(value = "响应状态：-1-会话失效、1-成功、其他-失败")
    private int status;
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

    private ApiResult (Builder<T> builder) {
        this.messageId = builder.messageId;
        this.status = builder.status;
        this.message = builder.message;
        this.timestamp = builder.timestamp;
        this.data = builder.data;
    }

    public static ApiResult<String> getSuccess(MessageId messageId) {
        return getSuccess(messageId, null);
    }

    public static ApiResult<String> getSuccess(MessageId messageId, String message) {
        return getSuccess(messageId, message, null);
    }

    public static <T> ApiResult<T> getSuccess(MessageId messageId, T data) {
        return getResponse(messageId, Status.SUCCESS, null, data);
    }

    public static <T> ApiResult<T> getSuccess(MessageId messageId, String message, T data) {
        return getResponse(messageId, Status.SUCCESS, message, data);
    }

    public static ApiResult<?> getFailure(MessageId messageId, String message) {
        return getResponse(messageId, Status.FAILURE, message, null);
    }

    public static <T> ApiResult<T> getFailure(MessageId messageId, String message, T data) {
        return getResponse(messageId, Status.FAILURE, message, data);
    }

    public static <T> ApiResult<T> getResponse(MessageId messageId, Status status, String message, T data) {
        return new Builder<T>(messageId.getCode(), status.getValue(), new Date()).message(message).data(data).build();
    }

    public static class Builder<T> {
        private int messageId;
        private int status;
        private Date timestamp;

        private String message;
        private T data;

        public Builder(int messageId, int status, Date timestamp) {
            this.messageId = messageId;
            this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

enum Status {
    /**
     * session失效
     */
    EXPIRE(-1, "user.session_is_expired"),
    /**
     * 成功
     */
    SUCCESS(1, "operation.success"),
    /**
     * 出错
     */
    FAILURE(0, "operation.failure"),
    /**
     * 请求错误
     */
    BAD_REQUEST(400, "bad.request")
    ;

    private int value;
    private String name;

    Status(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}