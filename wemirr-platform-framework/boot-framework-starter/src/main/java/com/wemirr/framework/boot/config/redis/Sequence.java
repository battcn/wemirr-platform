package com.wemirr.framework.boot.config.redis;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Levin
 */
@Slf4j
@AllArgsConstructor
public enum Sequence {

    /**
     * 用户编号
     */
    UN("UN", "user:no:"),
    /**
     * 公司编号
     */
    CN("CN", "company:no:"),
    ;

    private final String prefix;
    private final String key;

    public String prefix() {
        return this.prefix;
    }

    public String key() {
        return this.key;
    }

}
