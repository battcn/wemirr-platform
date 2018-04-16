package com.battcn.bookstore.consumer.enums;

/**
 * @author Levin
 * @since 2018/4/16 0016
 */
public enum BookRecommendEnum {

    /**
     * 推荐
     */
    ONE(1),
    /**
     * 热门
     */
    TWO(2),;

    private int type;

    public int getType() {
        return type;
    }

    BookRecommendEnum(int type) {
        this.type = type;
    }


}
