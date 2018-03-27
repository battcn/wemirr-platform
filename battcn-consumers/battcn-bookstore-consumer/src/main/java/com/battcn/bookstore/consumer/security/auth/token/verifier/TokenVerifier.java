package com.battcn.bookstore.consumer.security.auth.token.verifier;

/**
 * Token验证
 *
 * @author Levin
 * @since 2017-05-25
 */
public interface TokenVerifier {

    /**
     * 验证 JWT
     *
     * @param token Token
     * @return boolean
     */
    boolean verify(String token);

}
