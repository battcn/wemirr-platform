package com.wemirr.platform.ai.core.provider.retrieval;

import com.wemirr.platform.ai.core.assistant.service.RagAssistantParams;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;

import java.util.Optional;

/**
 * 内容检索策略接口
 * <p>
 * 定义不同类型检索器的创建策略，支持向量检索、图谱检索等
 *
 * @author xJh
 * @since 2025/12/28
 */
public interface ContentRetrieverStrategy {

    /**
     * 获取策略类型
     *
     * @return 策略类型标识
     */
    String getType();

    /**
     * 获取检索器描述
     *
     * @return 检索器描述
     */
    String getDescription();

    /**
     * 判断是否支持当前参数配置
     *
     * @param params RAG 参数
     * @return 是否支持
     */
    boolean supports(RagAssistantParams params);

    /**
     * 创建内容检索器
     *
     * @param params    RAG 参数
     * @param chatModel 聊天模型（部分检索器可能需要）
     * @return 内容检索器，如果创建失败返回空
     */
    Optional<ContentRetriever> createRetriever(RagAssistantParams params, ChatModel chatModel);
}
