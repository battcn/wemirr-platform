package com.wemirr.framework.redis.plus.sequence;


/**
 * @author Levin
 */
public interface Sequence {


    /**
     * Redis Key
     *
     * @return String
     */
    String key();

    /**
     * Redis Prefix
     *
     * @return UN => UN20210909000001
     */
    String prefix();

}
