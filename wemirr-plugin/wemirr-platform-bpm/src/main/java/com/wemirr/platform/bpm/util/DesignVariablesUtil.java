package com.wemirr.platform.bpm.util;

import com.alibaba.fastjson2.JSONObject;

/**
 * 自定义参数处理工具类
 *
 * @author Levin
 */
public class DesignVariablesUtil {

    public static String getVariable(String template, JSONObject jsonObject) {
        //标记处理类：配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingJsonKeywordHandler parameterMappingTokenHandler = new ParameterMappingJsonKeywordHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("${", "}", parameterMappingTokenHandler);
        //解析
        return genericTokenParser.parse(template, jsonObject);
    }
}
