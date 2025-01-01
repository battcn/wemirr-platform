package com.wemirr.platform.tms.util;

import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Levin
 */
@Slf4j
public class CalculateUtils {

    /**
     * 解析并计算四则运算表达式(含括号)，返回计算小数结果
     *
     * @param exStr 算术表达式(含括号)
     */
    @SneakyThrows
    public static BigDecimal calculate(String exStr, Map<String, Object> values) {
        Expression expression = new Expression(exStr);
        EvaluationValue result = expression.withValues(values).evaluate();
        return new BigDecimal(result.getStringValue());
    }
}
