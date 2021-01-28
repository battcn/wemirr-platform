package com.wemirr.framework.commons.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对象转换接口
 *
 * @param <S> 源对象
 * @param <T> 目标对象
 * @author Levin
 * @version 1.0.0
 * @since 2019-03-19
 */
public interface BaseConverts<S, T> {

    /**
     * 类型转换
     *
     * @param source 原对象
     * @return 目标对象
     */
    T convert(S source);

    /**
     * 批量类型转换
     *
     * @param sources 原对象
     * @return 目标对象
     */
    default List<T> converts(List<S> sources) {
        return sources == null || sources.size() <= 0 ? new ArrayList<>() : sources.stream().map(this::convert).collect(Collectors.toList());
    }

}
