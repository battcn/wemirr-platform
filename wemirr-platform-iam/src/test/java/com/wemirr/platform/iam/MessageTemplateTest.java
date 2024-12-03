package com.wemirr.platform.iam;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.HashMap;
import java.util.Map;

public class MessageTemplateTest {

    @Test
    public void format() {
        // 模板字符串
        String template = "欢迎 {username} 来到 WP 系统";

        // 参数映射
        Map<String, String> params = new HashMap<>();
        params.put("username", "张三");
        // 使用 Hutool 进行模板替换
        System.out.println(StrUtil.format(template, params)); // 输出: 欢迎 张三 来到 WP 系统
        params.put("username", "李四");
        // 创建占位符解析器
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
        // 替换占位符
        System.out.println(helper.replacePlaceholders(template, params::get)); // 输出: 欢迎 李四 来到 WP 系统
    }

}
