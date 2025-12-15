package com.wemirr.framework.log.diff.support.parse;

import com.wemirr.framework.log.diff.domain.MethodExecute;
import com.wemirr.framework.log.diff.service.impl.DiffParseFunction;
import jakarta.annotation.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.EvaluationContext;

import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析需要存储的日志里面的SpeEL表达式
 *
 * @author mzt.
 */
public class DiffLogValueParser implements BeanFactoryAware {

    public static final String COMMA = ",";
    private static final Pattern PATTERN = Pattern.compile("\\{\\s*(\\w*)\\s*\\{(.*?)}}");
    protected final DiffLogExpressionEvaluator expressionEvaluator = new DiffLogExpressionEvaluator();
    protected BeanFactory beanFactory;
    protected boolean diffLog;

    private DiffLogFunctionParser diffLogFunctionParser;

    private DiffParseFunction diffParseFunction;

    public static int strCount(String srcText, String findText) {
        int count = 0;
        int index = 0;
        while ((index = srcText.indexOf(findText, index)) != -1) {
            index = index + findText.length();
            count++;
        }
        return count;
    }

    public String singleProcessTemplate(MethodExecute methodExecute,
                                        String templates,
                                        Map<String, String> beforeFunctionNameAndReturnMap) {
        Map<String, String> stringStringMap = processTemplate(Collections.singletonList(templates), methodExecute,
                beforeFunctionNameAndReturnMap);
        return stringStringMap.get(templates);
    }

    public Map<String, String> processTemplate(Collection<String> templates, MethodExecute methodExecute,
                                               Map<String, String> beforeFunctionNameAndReturnMap) {
        Map<String, String> expressionValues = new HashMap<>();
        EvaluationContext evaluationContext = expressionEvaluator.createEvaluationContext(methodExecute.getMethod(),
                methodExecute.getArgs(), methodExecute.getTargetClass(), methodExecute.getResult(),
                methodExecute.getErrorMsg(), beanFactory);

        for (String expressionTemplate : templates) {
            if (expressionTemplate.contains("{")) {
                Matcher matcher = PATTERN.matcher(expressionTemplate);
                StringBuilder parsedStr = new StringBuilder();
                AnnotatedElementKey annotatedElementKey = new AnnotatedElementKey(methodExecute.getMethod(), methodExecute.getTargetClass());
                boolean diffLogFlag = !diffLog;
                while (matcher.find()) {

                    String expression = matcher.group(2);
                    String functionName = matcher.group(1);
                    if (DiffParseFunction.DIFF_FUNCTION_NAME.equals(functionName)) {
                        expression = getDiffFunctionValue(evaluationContext, annotatedElementKey, expression);
                    } else {
                        Object value = expressionEvaluator.parseExpression(expression, annotatedElementKey, evaluationContext);
                        expression = diffLogFunctionParser.getFunctionReturnValue(beforeFunctionNameAndReturnMap, value, expression, functionName);
                    }
                    if (expression != null && !Objects.equals(expression, "")) {
                        diffLogFlag = false;
                    }
                    matcher.appendReplacement(parsedStr, Matcher.quoteReplacement(expression == null ? "" : expression));
                }
                matcher.appendTail(parsedStr);
                expressionValues.put(expressionTemplate, diffLogFlag ? expressionTemplate : parsedStr.toString());
            } else {
                expressionValues.put(expressionTemplate, expressionTemplate);
            }

        }
        return expressionValues;
    }

    private String getDiffFunctionValue(EvaluationContext evaluationContext, AnnotatedElementKey annotatedElementKey, String expression) {
        String[] params = parseDiffFunction(expression);
        if (params.length == 1) {
            Object targetObj = expressionEvaluator.parseExpression(params[0], annotatedElementKey, evaluationContext);
            expression = diffParseFunction.diff(targetObj);
        } else if (params.length == 2) {
            Object sourceObj = expressionEvaluator.parseExpression(params[0], annotatedElementKey, evaluationContext);
            Object targetObj = expressionEvaluator.parseExpression(params[1], annotatedElementKey, evaluationContext);
            expression = diffParseFunction.diff(sourceObj, targetObj);
        }
        return expression;
    }

    private String[] parseDiffFunction(String expression) {
        if (expression.contains(COMMA) && strCount(expression, COMMA) == 1) {
            return expression.split(COMMA);
        }
        return new String[]{expression};
    }

    public Map<String, String> processBeforeExecuteFunctionTemplate(Collection<String> templates, Class<?> targetClass, Method method, Object[] args) {
        Map<String, String> functionNameAndReturnValueMap = new HashMap<>();
        EvaluationContext evaluationContext = expressionEvaluator.createEvaluationContext(method, args, targetClass, null, null, beanFactory);

        for (String expressionTemplate : templates) {
            if (!expressionTemplate.contains("{")) {
                continue;
            }
            Matcher matcher = PATTERN.matcher(expressionTemplate);
            while (matcher.find()) {
                String expression = matcher.group(2);
                if (expression.contains("#_ret") || expression.contains("#_errorMsg")) {
                    continue;
                }
                AnnotatedElementKey annotatedElementKey = new AnnotatedElementKey(method, targetClass);
                String functionName = matcher.group(1);
                if (diffLogFunctionParser.beforeFunction(functionName)) {
                    Object value = expressionEvaluator.parseExpression(expression, annotatedElementKey, evaluationContext);
                    String functionReturnValue = diffLogFunctionParser.getFunctionReturnValue(null, value, expression, functionName);
                    String functionCallInstanceKey = diffLogFunctionParser.getFunctionCallInstanceKey(functionName, expression);
                    functionNameAndReturnValueMap.put(functionCallInstanceKey, functionReturnValue);
                }
            }
        }
        return functionNameAndReturnValueMap;
    }


    @Override
    public void setBeanFactory(@Nullable BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void setLogFunctionParser(DiffLogFunctionParser diffLogFunctionParser) {
        this.diffLogFunctionParser = diffLogFunctionParser;
    }

    public void setDiffParseFunction(DiffParseFunction diffParseFunction) {
        this.diffParseFunction = diffParseFunction;
    }
}
