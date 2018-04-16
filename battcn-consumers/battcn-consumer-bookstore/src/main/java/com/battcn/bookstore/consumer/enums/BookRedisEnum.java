package com.battcn.bookstore.consumer.enums;


/**
 * @author Levin
 */
public enum BookRedisEnum {

    /**
     * 验证码Key
     */
    BOOKS_CLICK_RANKINGS("books:click:rankings"),;

    private String key;


    public String getKey() {
        return key;
    }

    BookRedisEnum(String key) {
        this.key = key;
    }


}
