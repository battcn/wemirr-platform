package com.wemirr.framework.boot.sensitive;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 默认随机打吗
 *
 * @author Levin
 */
public class DefaulSensitiveStrategy implements SensitiveStrategy {

    @Override
    public String serialize(String original) {
        return StrUtil.hide(original, 0, RandomUtil.randomInt(0, original.length()));
    }
}
