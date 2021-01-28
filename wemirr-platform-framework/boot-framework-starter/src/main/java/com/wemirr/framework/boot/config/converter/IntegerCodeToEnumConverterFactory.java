package com.wemirr.framework.boot.config.converter;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Maps;
import com.wemirr.framework.database.mybatis.DictionaryEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Map;

/**
 * @author Levin
 */
public class IntegerCodeToEnumConverterFactory implements ConverterFactory<Integer, DictionaryEnum> {
    private static final Map<Class, Converter> CONVERTERS = Maps.newHashMap();

    /**
     * 获取一个从 Integer 转化为 T 的转换器，T 是一个泛型，有多个实现
     *
     * @param targetType 转换后的类型
     * @return 返回一个转化器
     */
    @Override
    public <T extends DictionaryEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        Converter<Integer, T> converter = CONVERTERS.get(targetType);
        if (converter == null) {
            converter = new IntegerToEnumConverter<>(targetType);
            CONVERTERS.put(targetType, converter);
        }
        return converter;
    }

    public static class IntegerToEnumConverter<T extends DictionaryEnum> implements Converter<Integer, T> {
        private Map<Integer, T> enumMap = Maps.newHashMap();

        public IntegerToEnumConverter(Class<T> enumType) {
            T[] enums = enumType.getEnumConstants();
            for (T e : enums) {
                enumMap.put((Integer) e.getValue(), e);
            }
        }

        @Override
        public T convert(Integer source) {
            T t = enumMap.get(source);
            if (ObjectUtil.isNull(t)) {
                throw new IllegalArgumentException("无法匹配对应的枚举类型");
            }
            return t;
        }
    }

}