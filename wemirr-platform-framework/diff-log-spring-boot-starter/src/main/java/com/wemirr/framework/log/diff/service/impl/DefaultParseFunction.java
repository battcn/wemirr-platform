package com.wemirr.framework.log.diff.service.impl;


import com.wemirr.framework.log.diff.service.IParseFunction;

/**
 * 默认的解析器
 *
 * @author muzhantong
 */
public class DefaultParseFunction implements IParseFunction {

    @Override
    public boolean executeBefore() {
        return true;
    }

    @Override
    public String functionName() {
        return null;
    }

    @Override
    public String apply(Object value) {
        return null;
    }
}
