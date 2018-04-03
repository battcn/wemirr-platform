package com.battcn.bookstore.consumer.enums;


/**
 * @author Levin
 */
public enum AuthorizedEnum {

    /**
     * 验证码Key
     */
    REDIS_CAPTCHA_HASH("hash:captcha"),
    REDIS_MEMBER_NO_HASH("hash:member:member_no"),;

    private String key;


    public String getKey() {
        return key;
    }

    AuthorizedEnum(String key) {
        this.key = key;
    }


}
