package com.wemirr.platform.ai.core.provider.retrieval;

import com.wemirr.platform.ai.core.assistant.service.RagAssistantParams;
import com.wemirr.platform.ai.core.provider.graph.GraphContentRetriever;
import com.wemirr.platform.ai.core.provider.graph.GraphRagService;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 图谱检索策略
 * <p>
 * 基于知识图谱的内容检索实现
 *
 * @author xJh
 * @since 2025/12/28
 */
@Slf4j
@Component
public class GraphContentRetrieverStrategy implements ContentRetrieverStrategy {

    /**
     * GraphRAG 服务（可选，仅在启用图谱功能时注入）
     */
    @Autowired(required = false)
    private GraphRagService graphRagService;

    @Override
    public String getType() {
        return "GRAPH";
    }

    @Override
    public String getDescription() {
        return "知识图谱（实体关系、结构化数据）";
    }

    @Override
    public boolean supports(RagAssistantParams params) {
        return Boolean.TRUE.equals(params.getEnableGraphRetrieval())
                && graphRagService != null
                && params.getEffectiveGraphKbId() != null;
    }

    @Override
    public Optional<ContentRetriever> createRetriever(RagAssistantParams params, ChatModel chatModel) {
        if (graphRagService == null) {
            log.warn("GraphRagService 未注入，无法创建图谱检索器");
            return Optional.empty();
        }

        try {
            String graphKbId = params.getEffectiveGraphKbId();
            if (graphKbId == null) {
                log.warn("图谱知识库ID为空");
                return Optional.empty();
            }

            ContentRetriever retriever = GraphContentRetriever.builder()
                    .graphRagService(graphRagService)
                    .chatModel(chatModel)
                    .knowledgeBaseId(graphKbId)
                    .maxResults(params.getGraphMaxResults())
                    .silentOnEmpty(true)
                    .build();

            log.debug("图谱检索器创建成功: graphKbId={}, maxResults={}",
                    graphKbId, params.getGraphMaxResults());

            return Optional.of(retriever);
        } catch (Exception e) {
            log.error("创建图谱检索器失败: graphKbId={}", params.getEffectiveGraphKbId(), e);
            return Optional.empty();
        }
    }
}
