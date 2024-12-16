package com.wemirr.platform.suite;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import org.dromara.x.file.storage.core.hash.HashInfo;
import org.junit.jupiter.api.Test;

public class JsonTests {

    @Test
    public void test1() {
        String json = null;
        System.out.println(JSON.parseObject("", HashInfo.class));
        System.out.println(JSON.parseObject(json, HashInfo.class));
        System.out.println( StrUtil.removePrefix("str-xxx", "-"));
        System.out.println( StrUtil.removePrefix(null, "-"));
    }
}
