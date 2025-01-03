package com.wemirr.platform;

import cn.hutool.core.lang.Validator;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Levin
 */
public class ValidTest {


    @Test
    public void test1() {
        Assert.assertTrue(Validator.isPlateNumber("京A88888"));
    }

    @SneakyThrows
    @Test
    public void test2() {
        Map<String, Object> values = Maps.newHashMap();
        values.put("qtyUnit", 100);
        values.put("quantity", 3);
        values.put("grossWeight", 2);
        String exStr = "qtyUnit * quantity * grossWeight";
        Expression expression = new Expression(exStr);
        EvaluationValue result = expression.withValues(values).evaluate();
        System.out.println("折叠体积: " + result.getStringValue());
    }

    @Test
    public void test3() {
        String expression = "3+5*2";
        Map<String, Object> orderInfo = new HashMap<>();
//        Map<String, Object> result = CalculateUtils.calculateFloat(expression, orderInfo);
//        System.out.println(result);  // 输出：0.0  (表达式非法，缺少'=')
    }


}
