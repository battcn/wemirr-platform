package com.battcn.framework.mybatis.pojo.enums;

/**
 * 自定义整数类型枚举
 *
 * @param <T> 枚举子类
 * @author Levin
 */
public interface IntEnum<T> {

    /**
     * 枚举对应的整数值
     *
     * @return 值
     */
    int value();

    /**
     * 枚举类型描述
     *
     * @return 描述信息
     */
    String desc();
}
