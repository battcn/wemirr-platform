package com.battcn.bookstore.consumer.security.auth.token.extractor;

import com.battcn.bookstore.consumer.security.exceptions.AuthenticationServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 一个实现 {@link TokenExtractor} 接口 获取Token
 * 授权：Bearer scheme
 *
 * @author Levin
 * @since 2017-08-15
 */
@Component
public class HeaderTokenExtractor implements TokenExtractor {

    private static final String HEADER_PREFIX = "Bearer ";

    @Override
    public String extract(String header) {
        if (StringUtils.isBlank(header)) {
            throw new AuthenticationServiceException("Authorization header cannot be blank!");
        }
        if (header.length() < HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException("Invalid authorization header size.");
        }
        return header.substring(HEADER_PREFIX.length(), header.length());
    }
}
