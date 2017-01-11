package com.battcn.platform.common.exception;
/**
 * 项目异常总父类 <br/>
 * @author   唐亚峰
 * @version  1.0
 * @since    2016年12月19日 下午5:13:01
 * @see 	 
 */
public class BattcnException extends RuntimeException {

	private static final long serialVersionUID = 649333084513029673L;
	
	public BattcnException() {
    }

    public BattcnException(String message) {
        super(message);
    }

    public BattcnException(String message, Throwable cause) {
        super(message, cause);
    }

    public BattcnException(Throwable cause) {
        super(cause);
    }

    public BattcnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

