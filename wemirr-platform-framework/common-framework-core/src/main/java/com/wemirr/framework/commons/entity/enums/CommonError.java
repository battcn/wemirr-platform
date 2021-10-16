package com.wemirr.framework.commons.entity.enums;


import lombok.AllArgsConstructor;

/**
 * 推送异常类型
 *
 * @author Levin
 * @since 2019-06-11
 */
@AllArgsConstructor
public enum CommonError implements IntEnum {

    /**
     * 请求成功
     */
    API_SUCCESS(200, "请求成功"),
    TOKEN_INVALID(201, "登陆凭证无效"),
    PARAMETER_MISS(202, "必要参数缺失"),
    REQUEST_FORMAT(203, "请求参数格式错误"),
    DATA_AUTHORITY(204, "无此数据权限"),
    VALIDATION_FAIL(205, "效验数据失败"),
    DB_STORAGE_FAIL(206, "数据存储失败"),
    REMOTE_CALL_FAIL(207, "远程调用服务失败"),
    DATA_DUPLICATION(208, "第三方数据格式错误"),

    THIRD_DATA_ERROR(210, "已存在重复的数据"),
    UNAUTHORIZED(401, "账户无权限, 无法操作，请联系管理员!"),
    ACCOUNT_DISABLE(402, "账户被禁用，登录失败，请联系管理员!"),
    PASSWORD_ERROR(403, "用户名或密码输入错误，登录失败!"),
    NOT_FIND_PATH(404, "无法找到数据，请检查请求条件！"),
    NOT_FIND_DATA(405, "接口路径没有找到，请检查路由!"),
    ACCOUNT_UNBOUND(406, "该微信账号还未绑定手机号码"),
    ACCOUNT_NOT_FIND(407, "该账号不存在，请检查输入!"),
    UNKNOWN_ERROR(500, "未知错误"),

    DATA_EXISTS(1000, "数据 %s 已存在"),

    /**
     * 请求本系统类错误1001~2999
     * 请求标识类错误1001~1999
     * 请求参数不合法2001~2999
     * 外部系统返回错误6000~8000
     * 外部系统返回结果前异常6001~6999
     * 外部系统返回结果后转换异常7001~7999
     * 平台API未对接完成5000
     * 系统级错误9000~9999
     */
    REQUEST_COMMON_PARAM_EMPTY(1001, "公共参数【%s】为空"),
    REQUEST_SERVICE_PARAM_EMPTY(1002, "业务参数【%s】为空"),
    REQUEST_SIGN_NOT_MATCH(1003, "签名不一致"),
    REQUEST_TIMESTAMP_INVALID(1004, "请求有效期为20分钟"),
    REQUEST_APP_KEY_INVALID(1005, "非法appKey"),
    REQUEST_TOKEN_FAIL(1006, "TOKEN错误"),
    REQUEST_SERVICE_ERROR(1007, "业务异常【%s】"),


    ORDER_NOT_FOUND(1008, "行【%s】缺少客户订单号"),
    API_PARAM_CUSTOMER_REPEAT(1009, "客户订单号【%s】重复"),

    // TODO 需要修改
    REQUEST_INVALIDATE(1014, "请求参数不正确"),
    REQUEST_SIGN_ERROR(1015, "签名失败"),
    REQUEST_ASYNC_CALLBACK_NOT_NULL(1016, "异步请求时，回调地址不能为空"),
    REQUEST_PARAM_ERROR(1018, "请求参数验证失败"),
    REQUEST_CONFIG_LOCKED(1019, "该配置已被禁用"),


    USER_NOT_FOUND(1501, "用户不存在"),
    ACCESS_DENIED(1101, "访问受限，您的权限不足"),


    API_NOT_OPENED(5000, "平台API未对接完成"),

    API_ERROR(2001, "API返回错误"),
    API_RETURN_ERROR(2002, "错误信息：【%s】"),


    INTERNAL_ERROR(9000, "其他错误"),
    NETWORK_ERROR(9001, "网络错误"),
    ENCODE_ERROR(9003, "编码错误"),
    API_BODY_NOT_SUPPORT(9002, "内容格式不正确"),
    LBS_RESOLVE_FAILURE(9004, "地址解析失败"),
    INNER_SERVICE_ERROR(9005, "内部服务响应失败"),

    TOO_MANY_REQUESTS(9005, "访问受限,超出最大访问次数"),

    ;

    private final Integer type;

    private final String desc;


    @Override
    public Integer type() {
        return type;
    }

    @Override
    public String desc() {
        return desc;
    }

}
