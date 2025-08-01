package com.wemirr.platform.flow.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;

import java.util.Map;
import java.util.Set;


/**
 * @author Levin
 */
public class ParameterMappingJsonKeywordHandler implements JsonKeywordHandler {

    @Override
    public String handleKeyword(String content, JSONObject jsonObject) {
        //遍历 jsonObject 返回匹配参数
        Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            Object o = entry.getValue();
            if (StrUtil.equals(content, entry.getKey())) {
                if (o instanceof String) {
                    return entry.getValue() + "";
                }
            }
        }
        return "";
    }
}
