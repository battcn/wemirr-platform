package com.wemirr.platform;

import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;

public class AntPathMatcherTest {

    @Test
    public void test1() {
        final AntPathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.match("/abc/*/aa", "/abc/xxx/aa"));
    }

}
