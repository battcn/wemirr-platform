package com.battcn.platform.common.exception;
/**
 * ClassName:IllegalArgumentException <br/>
 * 非法参数异常，当参数非法或不正确是抛出
 * @author   唐亚峰
 * @version  1.0
 * @since    2016年12月23日 下午1:07:51
 * @see 	 
 */
public class IllegalArgumentException extends BattcnException {

	private static final long serialVersionUID = 1539633371635703406L;
	public IllegalArgumentException() {
    }

    public IllegalArgumentException(String message) {
        super(message);
    }

    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalArgumentException(Throwable cause) {
        super(cause);
    }

    public IllegalArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

