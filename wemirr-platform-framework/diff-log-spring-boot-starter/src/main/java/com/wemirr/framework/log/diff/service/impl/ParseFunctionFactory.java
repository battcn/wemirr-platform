package com.wemirr.framework.log.diff.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.log.diff.service.IParseFunction;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 解析函数工厂，管理所有自定义解析函数
 *
 * @author muzhantong
 */
public class ParseFunctionFactory {

    private final Map<String, IParseFunction> allFunctionMap;

    public ParseFunctionFactory(List<IParseFunction> parseFunctions) {
        this.allFunctionMap = Optional.ofNullable(parseFunctions)
                .map(functions -> functions.stream()
                        .filter(f -> StrUtil.isNotBlank(f.functionName()))
                        .collect(Collectors.toMap(IParseFunction::functionName, Function.identity())))
                .orElse(Map.of());
    }

    public IParseFunction getFunction(String functionName) {
        return allFunctionMap.get(functionName);
    }

    public boolean isBeforeFunction(String functionName) {
        return Optional.ofNullable(allFunctionMap.get(functionName))
                .map(IParseFunction::executeBefore)
                .orElse(false);
    }
}
