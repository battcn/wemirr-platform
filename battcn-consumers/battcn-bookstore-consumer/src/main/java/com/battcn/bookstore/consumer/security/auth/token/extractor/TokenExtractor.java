package com.battcn.bookstore.consumer.security.auth.token.extractor;

/**
 * 实现这个接口应该返回原Base-64编码
 *
 * @author Levin
 * @since 2017-05-25
 */
public interface TokenExtractor {

    /**
     * 提取Token
     *
     * @param payload payload
     * @return String
     */
    String extract(String payload);
}
