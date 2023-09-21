package com.wemirr.framework.boot.sensitive;

/**
 * @author Levin
 */
public interface SensitiveStrategy {

    default String serialize(String original) {
        return original;
    }


}
