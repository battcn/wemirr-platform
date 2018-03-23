package com.battcn.bookstore.consumer.security.auth.token.verifier;

import org.springframework.stereotype.Component;

/**
 * Token验证过滤器
 * TODO 暂时放行所有的TOKEN,后期补充验证规则
 *
 * @author Levin
 * @since 2017-05-25
 */
@Component
public class BloomFilterTokenVerifier implements TokenVerifier {

    @Override
    public boolean verify(String jti) {

        return true;
    }

}
