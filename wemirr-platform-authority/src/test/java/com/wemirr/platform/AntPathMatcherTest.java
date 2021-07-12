package com.wemirr.platform;

import com.wemirr.framework.commons.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;

public class AntPathMatcherTest {

    @Test
    public void test1() {
        final AntPathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.match("/abc/*/aa", "/abc/xxx/aa"));
        System.out.println(    StringUtils.containsAny("http://local","http","https"));

    }

}
