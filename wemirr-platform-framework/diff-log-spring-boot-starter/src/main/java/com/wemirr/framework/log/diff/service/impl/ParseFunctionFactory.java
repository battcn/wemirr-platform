package com.wemirr.framework.log.diff.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.log.diff.service.IParseFunction;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author muzhantong
 */

public class ParseFunctionFactory {
    private Map<String, IParseFunction> allFunctionMap;

    public ParseFunctionFactory(List<IParseFunction> parseFunctions) {
        if (CollectionUtils.isEmpty(parseFunctions)) {
            return;
        }
        allFunctionMap = new HashMap<>();
        for (IParseFunction parseFunction : parseFunctions) {
            if (StrUtil.isBlank(parseFunction.functionName())) {
                continue;
            }
            allFunctionMap.put(parseFunction.functionName(), parseFunction);
        }
    }

    public IParseFunction getFunction(String functionName) {
        return allFunctionMap.get(functionName);
    }

    public boolean isBeforeFunction(String functionName) {
        return allFunctionMap.get(functionName) != null && allFunctionMap.get(functionName).executeBefore();
    }
}
