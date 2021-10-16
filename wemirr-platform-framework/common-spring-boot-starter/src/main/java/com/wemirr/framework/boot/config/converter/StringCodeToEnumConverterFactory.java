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
public class StringCodeToEnumConverterFactory implements ConverterFactory<String, DictionaryEnum<?>> {

    private static final Map<Class, Converter> CONVERTERS = Maps.newHashMap();

    /**
     * 获取一个从 Integer 转化为 T 的转换器，T 是一个泛型，有多个实现
     *
     * @param targetType 转换后的类型
     * @return 返回一个转化器
     */
    @Override
    public <T extends DictionaryEnum<?>> Converter<String, T> getConverter(Class<T> targetType) {

        Converter<String, T> converter = CONVERTERS.get(targetType);
        if (converter == null) {
            converter = new StringCodeToEnumConverterFactory.StringToEnumConverter<>(targetType);
            CONVERTERS.put(targetType, converter);
        }
        return converter;
    }

    public static class StringToEnumConverter<T extends DictionaryEnum<?>> implements Converter<String, T> {
        private Map<String, T> enumMap = Maps.newHashMap();

        public StringToEnumConverter(Class<T> enumType) {
            T[] enums = enumType.getEnumConstants();
            for (T e : enums) {
                enumMap.put(e.getCode().toString(), e);
            }
        }

        @Override
        public T convert(String source) {
            T t = enumMap.get(source);
            if (ObjectUtil.isNull(t)) {
                throw new IllegalArgumentException("无法匹配对应的枚举类型");
            }
            return t;
        }
    }

}