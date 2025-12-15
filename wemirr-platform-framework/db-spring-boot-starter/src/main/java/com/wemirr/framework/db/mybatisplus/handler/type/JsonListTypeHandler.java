package com.wemirr.framework.db.mybatisplus.handler.type;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import org.apache.ibatis.type.MappedTypes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用 JSON List 类型处理器
 * <p>
 * 用于将 List 类型数据序列化为 JSON 存储到数据库，支持复杂对象列表的持久化
 *
 * @author Levin
 */
@MappedTypes(value = {List.class})
public class JsonListTypeHandler extends AbstractJsonTypeHandler<List<Object>> {

    public JsonListTypeHandler(Class<?> type, Field field) {
        super(type, field);
    }

    public JsonListTypeHandler(Class<?> type) {
        super(type);
    }

    @Override
    public List<Object> parse(String json) {
        if (json == null || json.isBlank()) {
            return new ArrayList<>();
        }
        try {
            return JSON.parseObject(json, new TypeReference<>() {});
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public String toJson(List<Object> obj) {
        return (obj == null || obj.isEmpty()) ? null : JSON.toJSONString(obj);
    }
}
