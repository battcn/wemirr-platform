package com.wemirr.platform.ai.core.tools;

import com.wemirr.platform.ai.core.annotation.AiTool;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;

/**
 * @author xJh
 * @since 2025/12/06
 * 平台功能工具类
 **/
@Service
@AiTool(
        name = "平台基础工具集",
        description = "提供菜单查询、系统状态检测等基础运维能力",
        icon = "ant-design:tool-outlined"
)
public class WpToolService {


    @Tool(name = "平台菜单查询工具",value = "查询当前平台的菜单结构")
    public String getMenu() {
        return "当前平台的菜单有：" + "菜单1, 菜单2, 菜单3";
    }

    /**
     * 文本长度统计
     */
    @Tool(name = "文本分析")
    public String analyzeText(@P("要分析的文本") String text) {
        if (text == null || text.isEmpty()) {
            return "请提供要分析的文本内容";
        }

        int length = text.length();
        int words = text.trim().isEmpty() ? 0 : text.trim().split("\\s+").length;
        int lines = text.split("\n").length;

        return String.format("""
                📊 文本分析结果：
                • 字符数：%d
                • 单词数：%d
                • 行数：%d
                • 首字符：%s
                • 末字符：%s
                """,
                length,
                words,
                lines,
                length > 0 ? text.charAt(0) : "无",
                length > 0 ? text.charAt(length - 1) : "无");
    }
    
    @Tool(name = "获取平台功能列表")
    public String getPlatformFeatures() {
        return """
                我具备以下平台功能：
                • 菜单查询 - 查看平台菜单结构
                • 用户管理 - 用户信息查询和管理
                • 系统状态 - 获取平台运行状态
                • 操作日志 - 查看系统操作记录
                """;
    }
}
