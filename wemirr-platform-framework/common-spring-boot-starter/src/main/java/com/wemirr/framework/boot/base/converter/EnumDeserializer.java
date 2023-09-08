package com.wemirr.framework.boot.base.converter;


import cn.hutool.core.util.ReflectUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * enum反序列化工具
 *
 * @author Levin
 * @since 2019-07-25 22:15
 */
@Slf4j
public class EnumDeserializer extends StdDeserializer<Enum<?>> {
    public final static EnumDeserializer INSTANCE = new EnumDeserializer();
    private final static String ALL_ENUM_STRING_CONVERT_METHOD = "get";
    private final static String ALL_ENUM_KEY_FIELD = "code";

    private EnumDeserializer() {
        super(Enum.class);
    }

    @Override
    public Enum<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonToken token = p.getCurrentToken();
        String value = null;
        while (!token.isStructEnd()) {
            if (ALL_ENUM_KEY_FIELD.equals(p.getText())) {
                p.nextToken();
                value = p.getValueAsString();
            } else {
                p.nextToken();
            }
            token = p.getCurrentToken();
        }
        if (value == null || "".equals(value)) {
            return null;
        }

        Object obj = p.getCurrentValue();
        if (obj == null) {
            return null;
        }
        Field field = ReflectUtil.getField(obj.getClass(), p.getCurrentName());
        //找不到字段
        if (field == null) {
            return null;
        }
        Class<?> fieldType = field.getType();
        try {
            Method method = fieldType.getMethod(ALL_ENUM_STRING_CONVERT_METHOD, String.class);
            return (Enum<?>) method.invoke(null, value);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
            log.warn("解析枚举失败", e);
            return null;
        }
    }


}
