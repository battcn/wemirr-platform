package com.wemirr.framework.db.mybatisplus.handler.type;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.ibatis.type.MappedTypes;

import java.lang.reflect.Type;
import java.util.Set;

/**
 * 参考 {@link com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler} 实现
 * 字符串反序列化为 Set<Long> 时，如果元素的数值太小，会被处理成 Integer 类型，导致可能存在隐性的 BUG。
 *
 * @author Levin
 */
@MappedTypes(value = {JSONObject.class})
public class JsonTypeHandler extends AbstractJsonTypeHandler<JSONObject> {

    private static final TypeReference<Set<Long>> TYPE_REFERENCE = new TypeReference<>() {
    };

    @Override
    protected JSONObject parse(String json) {
        return JSON.parseObject(json, (Type) TYPE_REFERENCE);
    }

    @Override
    protected String toJson(JSONObject obj) {
        return obj.toJSONString();
    }


}