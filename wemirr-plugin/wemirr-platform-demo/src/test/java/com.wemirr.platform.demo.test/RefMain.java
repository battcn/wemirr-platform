package com.wemirr.platform.demo.test;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Levin
 */
public class RefMain {


    public static void main(String[] args) {
        String message = "这是一个示例字符串，包含了{xxx}和{yyy}这样的模式";
        String pattern = "\\{(.+?)\\}"; // 正则表达式，用于匹配 ${xxx} 类型的模式

        // 使用 ReUtil.findAll 方法来提取匹配的字符串
        // 第一个参数是要匹配的字符串，第二个参数是正则表达式
        List<String> results = ReUtil.findAll(pattern, message, 0, Lists.newArrayList());
        System.out.println(results);
        // 输出提取到的结果
//        for (String result : results) {
//            System.out.println(result);
//        }
        String value = StrUtil.replace(message, pattern, (param -> getMessage(param.group())));
        System.out.println(value);
    }

    public static String getMessage(String text) {
        return text.replace("{", "").replace("}", "");
    }


}
