package com.wemirr.framework.log.diff.service.impl;


import com.wemirr.framework.log.diff.service.IFunctionService;
import com.wemirr.framework.log.diff.service.IParseFunction;

/**
 * @author muzhantong
 */
public class DefaultFunctionServiceImpl implements IFunctionService {

    private final ParseFunctionFactory parseFunctionFactory;

    public DefaultFunctionServiceImpl(ParseFunctionFactory parseFunctionFactory) {
        this.parseFunctionFactory = parseFunctionFactory;
    }

    @Override
    public String apply(String functionName, Object value) {
        IParseFunction function = parseFunctionFactory.getFunction(functionName);
        if (function == null) {
            return value.toString();
        }
        return function.apply(value);
    }

    @Override
    public boolean beforeFunction(String functionName) {
        return parseFunctionFactory.isBeforeFunction(functionName);
    }
}
