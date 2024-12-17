package com.wemirr.platform;

import cn.hutool.core.lang.Validator;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;

/**
 * @author Levin
 */
public class ValidTest {


    @Test
    public void test1() {
        Assert.assertTrue(Validator.isPlateNumber("京A88888"));
    }

}
