package com.wemirr.framework.db.mybatisplus.handler.type;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import org.apache.ibatis.type.MappedTypes;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xJh
 * @date 2025/10/21
 **/
@MappedTypes(value = {Map.class})
public class MapTypeHandler extends AbstractJsonTypeHandler<Map<String, Object>> {

    public MapTypeHandler(Class<?> type, Field field) {
        super(type, field);
    }

    public MapTypeHandler(Class<?> type) {
        super(type);
    }

    @Override
    public Map<String, Object> parse(String json) {
        if (json == null || json.trim().isEmpty()) {
            return new HashMap<>();
        }
        try {
            return JSON.parseObject(json, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            // 解析失败时返回空Map
            return new HashMap<>();
        }
    }

    @Override
    public String toJson(Map<String, Object> obj) {
        if (obj == null || obj.isEmpty()) {
            return null;
        }
        return JSON.toJSONString(obj);
    }
}
