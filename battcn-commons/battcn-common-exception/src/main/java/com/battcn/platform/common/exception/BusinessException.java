package com.battcn.platform.common.exception;
/**
 * ClassName:BusinessException <br/>
 * 业务异常、执行业务逻辑时出错异常
 * @author   唐亚峰
 * @version  1.0
 * @since    2016年12月23日 下午1:14:22
 * @see 	 
 */
public class BusinessException extends BattcnException {

	private static final long serialVersionUID = -18352350731406573L;

	public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

