package com.wemirr.platform.tms.util;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Maps;
import com.wemirr.platform.tms.domain.enums.RuleOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author Levin
 */
@Slf4j
public class CalculateUtils {
    /**
     * 数字栈：用于存储表达式中的各个数字
     */
    private static Stack<Long> numberStack = null;
    /**
     * 数字栈：用于存储表达式中的各个数字
     */
    private static Stack<Float> numberFloatStack = null;
    /**
     * 符号栈：用于存储运算符和括号
     */
    private static Stack<Character> symbolStack = null;

    private static List<String> optionCharList = RuleOption.getOptionCharList();

    /**
     * 解析并计算四则运算表达式(含括号)，返回计算小数结果
     *
     * @param numStr 算术表达式(含括号)
     */
    public static Map<String, Object> calculateFloat(String numStr, Map<String, Object> orderInfo) {
        Map<String, Object> result = Maps.newHashMap();
        StringBuffer conditionSub = new StringBuffer();
        Map<String, Object> expDetail = Maps.newHashMap();
        numStr = removeStrSpace(numStr); // 去除空格
        // 检查表达式是纯数字,直接返回数值
        if (numStr.matches("[0-9.]+")) {
            result.put("amount", Float.valueOf(numStr));
            return result;
        }
        // 如果算术表达式尾部没有‘=’号，则在尾部添加‘=’，表示结束符
        if (numStr.length() > 1 && !"=".equals(numStr.charAt(numStr.length() - 1) + "")) {
            numStr += "=";
        }
        // 检查表达式是否合法
        if (!isStandard(numStr)) {
            System.err.println("错误：算术表达式有误！");
            result.put("amount", 0f);
            return result;
        }
        // 初始化栈
        numberFloatStack = new Stack<>();
        symbolStack = new Stack<>();
        // 用于缓存数字，因为数字可能是多位的
        StringBuffer temp = new StringBuffer();
        // 用于缓存中文字段，因为字段可能是多位的
        StringBuffer columnTemp = new StringBuffer();
        // 从表达式的第一个字符开始处理
        for (int i = 0; i < numStr.length(); i++) {
            // 获取一个字符
            char ch = numStr.charAt(i);
            if (isNumber(ch)) {
                // 若当前字符是数字
                // 加入到数字缓存中
                temp.append(ch);
            } else if (isOptionChar(ch)) {
                columnTemp.append(ch);
            } else { // 非数字的情况
                String tempStr = temp.toString(); // 将数字缓存转为字符串
                if (StringUtils.hasLength(tempStr)) {
                    int index_num = numStr.indexOf(tempStr);
                    if (index_num == 0) {
                        String pb = String.valueOf(numStr.charAt(tempStr.length()));
                        if ("*".equals(pb)) {
                            expDetail.put(tempStr + "*", "单价(" + tempStr + ")*"); //单价
                        } else if ("×".equals(pb)) {
                            expDetail.put(tempStr + "×", "单价(" + tempStr + ")×"); //单价
                        }
                    } else {
                        String sb = String.valueOf(numStr.charAt(index_num - 1));
                        if ("*".equals(sb)) {
                            expDetail.put("*" + tempStr, "*单价(" + tempStr + ")"); //单价
                        } else if ("×".equals(sb)) {
                            expDetail.put("×" + tempStr, "×单价(" + tempStr + ")"); //单价
                        }
                    }
                }
                String columnTempStr = columnTemp.toString();
                if (!tempStr.isEmpty()) {
                    float num = Float.parseFloat(tempStr); // 将数字字符串转为float
                    numberFloatStack.push(num); // 将数字压栈
                    temp = new StringBuffer(); // 重置数字缓存
                } else if (!columnTempStr.isEmpty()) {
                    RuleOption option = RuleOption.getOptionByName(columnTempStr);
                    if (option != null) {
                        float num = 0; // 取出字段值转为长整型数
                        Map<String, Object> valueAndConditionSub = getOptionValue(orderInfo, option);
                        String condition_sub1 = valueAndConditionSub.get("condition_sub").toString();
                        if (!conditionSub.toString().contains(condition_sub1)) {
                            conditionSub.append(condition_sub1);
                        }
                        Object obj = valueAndConditionSub.get("value");
                        if (obj != null) {
                            if (obj instanceof Integer) {
                                num = Long.valueOf((Integer) obj); // 取出字段值转为长整型数
                            } else if (obj instanceof Long) {
                                num = (Long) obj; // 取出字段值转为长整型数
                            } else if (obj instanceof BigDecimal) {
                                num = ((BigDecimal) obj).floatValue(); // 取出字段值转为长整型数
                            }
                            expDetail.put(columnTempStr, columnTempStr + "(" + obj + ")"); //公式中的字段名
                        } else {
                            expDetail.put(columnTempStr, columnTempStr + "(0)"); //公式中的字段名
                        }
                        numberFloatStack.push(num); // 将数字压栈
                        columnTemp = new StringBuffer(); // 重置字段缓存
                    } else {
                        numberFloatStack.push(0f); // 将字段取值压栈
                        columnTemp = new StringBuffer(); // 重置字段缓存
                    }
                }
                // 判断运算符的优先级，若当前优先级低于栈顶的优先级，则先把计算前面计算出来
                while (!comparePri(ch) && !symbolStack.empty()) {
                    float b = numberFloatStack.pop(); // 出栈，取出数字，后进先出
                    float a = numberFloatStack.pop();
                    // 取出运算符进行相应运算，并把结果压栈进行下一次运算
                    char symbol = symbolStack.pop();
                    switch (symbol) {
                        case '+' -> numberFloatStack.push(a + b);
                        case '-' -> numberFloatStack.push(a - b);
                        case '*', '×' -> numberFloatStack.push(a * b);
                        case '/', '÷' -> numberFloatStack.push(a / b);
                        default -> {
                        }
                    }
                } // while循环结束
                if (ch != '=') {
                    symbolStack.push(ch); // 符号入栈
                    if (ch == ')') { // 去括号
                        symbolStack.pop();
                        symbolStack.pop();
                    }
                }
            }
        } // for循环结束
        result.put("amount", numberFloatStack.pop());
        result.put("condition_sub", conditionSub.toString());
        result.put("exp_detail", expDetail);
        return result; // 返回计算结果
    }

    private static Map<String, Object> getOptionValue(Map<String, Object> orderInfo, RuleOption option) {
        Map<String, Object> map = Maps.newHashMap();
        StringBuffer conditionSub = new StringBuffer();
        Object obj = null;
        String exp_sub_name = option.getSubName();
        String colName = option.getColName();
        if (!StringUtils.hasLength(exp_sub_name) || "tms_order".equals(exp_sub_name)) {
            obj = orderInfo.get(colName);
        } else if ("tms_order_sender".equals(exp_sub_name) || "tms_order_consignee".equals(exp_sub_name)) {
            List<Map<String, Object>> subMaps = (List<Map<String, Object>>) orderInfo.get(exp_sub_name);
            if (CollUtil.isNotEmpty(subMaps)) {
                obj = subMaps.get(0).get(colName);
            }
        }
        map.put("value", obj);
        map.put("condition_sub", conditionSub.toString());
        return map;
    }

    /**
     * 去除字符串中的所有空格
     */
    private static String removeStrSpace(String str) {
        return str != null ? str.replaceAll(" ", "") : "";
    }

    /**
     * 转换字符串中的运算符号
     */
    private static String replaceSign(String str) {
        // % -> /100
        str = str != null ? str.replaceAll("%", "/100") : "";
        // ‰ -> /1000
        str = str.replaceAll("‰", "/1000");
        return str;
    }

    /**
     * 检查算术表达式的基本合法性，符合返回true，否则false
     */
    private static boolean isStandard(String numStr) {
        // 表达式不能为空
        if (numStr == null || numStr.isEmpty()) {
            return false;
        }
        Stack<Character> stack = new Stack<>(); // 用来保存括号，检查左右括号是否匹配
        boolean b = false; // 用来标记'='符号是否存在多个
        for (int i = 0; i < numStr.length(); i++) {
            char n = numStr.charAt(i);
            // 判断字符是否合法
            if (!(isNumber(n) || isOptionChar(n) || "(".equals(n + "") || ")".equals(n + "") || "+".equals(n + "") || "-".equals(n + "") || "*".equals(n + "") || "/".equals(n + "") || "=".equals(n + ""))) {
                return false;
            }
            // 将左括号压栈，用来给后面的右括号进行匹配
            if ("(".equals(n + "")) {
                stack.push(n);
            }
            if (")".equals(n + "")) { // 匹配括号
                if (stack.isEmpty() || !"(".equals((char) stack.pop() + "")) // 括号是否匹配
                {
                    return false;
                }
            }
            // 检查是否有多个'='号
            if ("=".equals(n + "")) {
                if (b) {
                    return false;
                }
                b = true;
            }
        }
        // 可能会有缺少右括号的情况
        if (!stack.isEmpty()) {
            return false;
        }
        // 检查'='号是否不在末尾
        return "=".equals(numStr.charAt(numStr.length() - 1) + "");
    }

    /**
     * 判断字符是否是0-9的数字或者.
     */
    private static boolean isNumber(char num) {
        return (num >= '0' && num <= '9') || num == '.';
    }

    /**
     * 判断字符是否是字段中的字符
     */
    public static boolean isOptionChar(char option) {
        String str = String.valueOf(option);
        return optionCharList.contains(str);
    }

    /**
     * 比较优先级：如果当前运算符比栈顶元素运算符优先级高则返回true，否则返回false
     */
    private static boolean comparePri(char symbol) {
        if (symbolStack.empty()) { // 空栈返回ture
            return true;
        }

        // 符号优先级说明（从高到低）:
        // 第1级: (
        // 第2级: * /
        // 第3级: + -
        // 第4级: )

        char top = (char) symbolStack.peek(); // 查看堆栈顶部的对象，注意不是出栈
        if (top == '(') {
            return true;
        }
        // 比较优先级
        switch (symbol) {
            case '(' -> { // 优先级最高
                return true;
            }
            case '*', '/' -> {
                // 优先级比+和-高
                return top == '+' || top == '-';
            }
            case '+', '-', ')', '=' -> {
                return false;
            }
            // 优先级最低
            // 结束符
            default -> {
            }
        }
        return true;
    }

}
