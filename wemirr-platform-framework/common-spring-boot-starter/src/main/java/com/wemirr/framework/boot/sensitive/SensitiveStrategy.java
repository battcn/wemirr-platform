package com.wemirr.framework.boot.sensitive;

/**
 * @author Levin
 */
public interface SensitiveStrategy {

    /**
     * 脱敏策略
     *
     * @param original original
     * @return 脱敏后的值
     */
    default String serialize(String original) {
        return original;
    }


}
