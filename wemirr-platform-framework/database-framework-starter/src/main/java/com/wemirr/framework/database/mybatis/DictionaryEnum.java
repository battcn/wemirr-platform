package com.wemirr.framework.database.mybatis;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.wemirr.framework.commons.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 枚举类型基类
 *
 * @author Levin
 * @since 2019/07/26
 */
public interface DictionaryEnum<T extends Serializable> extends IEnum<Serializable> {

    /**
     * 描述信息
     *
     * @return 描述
     */
    String getDesc();


    /**
     * 获取枚举编码
     *
     * @return 编码
     */
    default String getCode() {
        return String.valueOf(this.getValue());
    }


    /**
     * 枚举数组转集合
     *
     * @param dictionaries 枚举
     * @return 集合
     */
    static List<BaseDictionary> getList(DictionaryEnum<?>[] dictionaries) {
        if (dictionaries == null) {
            return null;
        }
        return Arrays.stream(dictionaries).map(dictionary -> BaseDictionary.builder()
                .code(dictionary.getCode()).desc(dictionary.getDesc())
                .build()).collect(Collectors.toList());
    }

    /**
     * 获取指定类型枚举映射
     *
     * @param enumClass 枚举类
     * @param type      类型
     * @param <E>       包装类
     * @return 枚举值
     */
    static <E extends DictionaryEnum<?>> E of(Class<E> enumClass, int type) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            final int value = (int) e.getValue();
            if (value == type) {
                return e;
            }
        }
        return null;
    }

    char SEPARATOR = ',';


    /**
     * 转换成字符串
     *
     * @param dictionaries 枚举
     * @return 转换结果
     */
    static <E extends DictionaryEnum<?>> String of(List<E> dictionaries) {
        if (CollectionUtil.isEmpty(dictionaries)) {
            return null;
        }
        return dictionaries.stream()
                .filter(Objects::nonNull)
                .map(DictionaryEnum::getCode).collect(Collectors.joining(","));
    }

    /**
     * 转换成集合枚举
     *
     * @param enumClass    枚举类
     * @param dictionaries 枚举
     * @return 转换结果
     */
    static <E extends DictionaryEnum<?>> List<E> of(Class<E> enumClass, String dictionaries) {
        if (StringUtils.isBlank(dictionaries)) {
            return null;
        }
        final List<String> split = StrUtil.split(dictionaries, SEPARATOR);
        return split.stream().filter(Objects::nonNull)
                .map(type -> of(enumClass, Integer.parseInt(type)))
                .collect(toList());
    }

}
