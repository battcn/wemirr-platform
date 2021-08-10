package com.wemirr.platform;

import com.wemirr.framework.commons.StringUtils;
import org.junit.jupiter.api.Test;

public class WordTest {
    @Test
    public void test1() {
        String uri = "/users/8";
        final String id = StringUtils.substringAfterLast(uri, "/");
        System.out.println(id);
    }
}
