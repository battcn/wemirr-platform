package com.battcn.framework.mybatis.pojo.enums;

/**
 * @author Levin
 * @since 2018-01-20
 */
public enum Gender implements IntEnum<Gender> {

    /**
     * 男
     */
    MALE(1, "男"),
    /**
     * 女
     */
    FEMALE(2, "女"),

    /**
     * 性别无关
     */
    ALL(3, "性别无关");
    private Integer value;
    private String desc;

    Gender(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public int value() {
        return this.value;
    }

    @Override
    public String desc() {
        return this.desc;
    }

    public static Gender of(int value) {
        for (Gender type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
