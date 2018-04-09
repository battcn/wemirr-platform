package com.battcn.framework.commons.entity;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @param <S>
 * @param <T>
 * @author Levin
 */
public abstract class BaseConverter<S, T> {

    /**
     * 将源类型转换成目标类型
     *
     * @param source 源数据
     * @return 转换后的数据
     */
    public abstract T convert(S source);

    /**
     * 将源类型转换成目标类型
     *
     * @param sources 源数据
     * @return 转换后的数据
     */
    public List<T> convertAll(List<S> sources) {
        if (sources == null || sources.isEmpty()) {
            return new ArrayList<>();
        }
        return sources.stream().map(this::convert).collect(toList());
    }
}