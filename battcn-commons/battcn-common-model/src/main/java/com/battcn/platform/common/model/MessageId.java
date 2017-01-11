package com.battcn.platform.common.model;

/**
 * <p>
 * Api message id
 * </p>
 *
 * @author 唐亚峰
 * @date 2016-06-30 12:47
 */
public enum MessageId {

	GENERAL_SUCCESS(200),
	GENERAL_FAIL(401),
    BAD_REQUEST(400),
    AUTH(403),
    LOGIN(1000),
    REGISTER(1001),
    LOGOUT(1002),
    GET_PROFILE(1003),
    GET_OTHER_PROFILE(1004),
    UPDATE_PROFILE(1005),
    UPDATE_PASSWORD(1006),
    ADD_INVITOR(1007),
    SEND_SMS(1001),
    FORGOT_PASSWORD(1004),
    ;

    private int code;

    MessageId(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
