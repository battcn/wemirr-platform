package com.battcn.bookstore.consumer.security.exceptions;

import com.battcn.bookstore.consumer.security.model.token.Token;


/**
 * 过期的Token
 *
 * @author Levin
 * @since 2017-05-25
 */
public class ExpiredTokenException extends RuntimeException {

    private static final long serialVersionUID = -5959543783324224864L;

    private Token token;

    public ExpiredTokenException(String msg) {
        super(msg);
    }

    public ExpiredTokenException(Token token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String token() {
        return this.token.getToken();
    }
}
