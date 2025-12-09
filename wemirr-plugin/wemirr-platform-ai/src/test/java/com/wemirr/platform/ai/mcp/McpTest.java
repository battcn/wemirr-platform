package com.wemirr.platform.ai.mcp;

import com.wemirr.platform.ai.core.provider.mcp.DynamicMcpToolProvider;
import com.wemirr.platform.ai.core.provider.mcp.McpClientFactory;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import com.wemirr.platform.ai.service.ModelConfigService;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xiao1
 * @since  2025-12
 */
@SpringBootTest
public class McpTest {

    private McpClient mcpClient;
    private ChatModel model;

    @Autowired
    private McpClientFactory mcpClientFactory;

    @Autowired
    private DynamicMcpToolProvider dynamicMcpToolProvider;

    @Autowired
    private ModelConfigService modelConfigService;


    interface GiteeBot {
        @SystemMessage("你是一个Gitee助手，可以帮助用户操作Gitee仓库。你可以获取仓库信息、查看提交历史、创建Issue等操作。请用中文回复用户。")
        String chat(String message);
    }

    @BeforeEach
    public void setUp() throws Exception {
        ModelConfig modelConfig = modelConfigService.getById(1L);

        // 创建OpenAI模型实例
        model = QwenChatModel.builder()
                .apiKey(modelConfig.getApiKey())
                .modelName("qwen-max")
//                .timeout(Duration.ofSeconds(60))
                .build();


        mcpClient = mcpClientFactory.getClient(1L);
        List<ToolSpecification> toolSpecifications = mcpClient.listTools();
        toolSpecifications.forEach(toolSpecification -> {System.out.println(toolSpecification.name()+":"+toolSpecification.description());});
        // 等待连接建立
        System.out.println("正在连接到 Gitee MCP 服务器...");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("连接建立成功！");
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (mcpClient != null) {
            mcpClient.close();
            System.out.println("MCP客户端已关闭");
        }
    }

    @Test
    public void testGiteeRepositoryInfo() throws Exception {
        System.out.println("=== 测试获取仓库信息 ===");
//
//        ToolProvider toolProvider = McpToolProvider.builder()
//                .mcpClients(List.of(mcpClient))
//                .build();
        dynamicMcpToolProvider.setAgentMcpServerIds(List.of(1L));

        GiteeBot bot = AiServices.builder(GiteeBot.class)
                .chatModel(model)
                .toolProvider(dynamicMcpToolProvider)
                .build();

        // 测试获取仓库信息
        String response = bot.chat("请获取我的仓库列表，显示前5个仓库的基本信息，包括仓库名称、描述、星标数等");
        System.out.println("回复：" + response);
        System.out.println();
    }
}
