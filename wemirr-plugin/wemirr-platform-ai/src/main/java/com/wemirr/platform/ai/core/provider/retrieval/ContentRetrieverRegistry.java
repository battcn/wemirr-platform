package com.wemirr.platform.ai.core.provider.retrieval;

import com.wemirr.platform.ai.core.assistant.service.RagAssistantParams;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 内容检索器注册中心
 * <p>
 * 管理所有检索策略，根据参数配置创建对应的检索器
 *
 * @author xJh
 * @since 2025/12/28
 */
@Slf4j
@Component
public class ContentRetrieverRegistry {

    private final List<ContentRetrieverStrategy> strategies;

    public ContentRetrieverRegistry(List<ContentRetrieverStrategy> strategies) {
        this.strategies = strategies;
        log.info("注册了 {} 个内容检索策略: {}", strategies.size(),
                strategies.stream().map(ContentRetrieverStrategy::getType).toList());
    }

    /**
     * 根据参数创建所有支持的检索器
     *
     * @param params    RAG 参数
     * @param chatModel 聊天模型
     * @return 检索器及其描述的映射
     */
    public Map<ContentRetriever, String> createRetrievers(RagAssistantParams params, ChatModel chatModel) {
        Map<ContentRetriever, String> retrieverMap = new LinkedHashMap<>();

        for (ContentRetrieverStrategy strategy : strategies) {
            if (strategy.supports(params)) {
                strategy.createRetriever(params, chatModel)
                        .ifPresent(retriever -> {
                            retrieverMap.put(retriever, strategy.getDescription());
                            log.debug("启用检索策略: type={}, description={}",
                                    strategy.getType(), strategy.getDescription());
                        });
            }
        }

        if (retrieverMap.isEmpty()) {
            log.warn("没有可用的检索器，请检查参数配置");
        } else {
            log.info("创建了 {} 个检索器", retrieverMap.size());
        }

        return retrieverMap;
    }

    /**
     * 获取所有已注册的策略类型
     *
     * @return 策略类型列表
     */
    public List<String> getRegisteredTypes() {
        return strategies.stream()
                .map(ContentRetrieverStrategy::getType)
                .toList();
    }

    /**
     * 根据类型获取策略
     *
     * @param type 策略类型
     * @return 策略实例
     */
    public Optional<ContentRetrieverStrategy> getStrategy(String type) {
        return strategies.stream()
                .filter(s -> s.getType().equals(type))
                .findFirst();
    }
}
