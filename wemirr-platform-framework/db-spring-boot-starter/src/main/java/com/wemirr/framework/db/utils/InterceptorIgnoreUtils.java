package com.wemirr.framework.db.utils;

import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;

import java.util.function.Supplier;

/**
 * @author YanCh
 * Created on: 2025-04-09 13:31
 **/
public class InterceptorIgnoreUtils {
    public static <T> T withIgnoreStrategy(Supplier<T> block) {
        return withIgnoreStrategy(IgnoreStrategy.builder().tenantLine(true).build(), block);
    }

    public static <T> T withIgnoreStrategy(IgnoreStrategy strategy, Supplier<T> block) {
        try {
            InterceptorIgnoreHelper.handle(strategy);
            return block.get();
        } finally {
            InterceptorIgnoreHelper.clearIgnoreStrategy();
        }
    }
}