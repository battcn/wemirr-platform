package com.wemirr.platform.ai.core.provider.retrieval;

import com.wemirr.framework.ai.core.provider.embedding.EmbeddingModelRegistry;
import com.wemirr.platform.ai.core.assistant.service.RagAssistantParams;
import com.wemirr.platform.ai.core.provider.vector.VectorStoreFactory;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.service.KnowledgeBaseService;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 向量检索策略
 * <p>
 * 基于向量相似度的内容检索实现
 *
 * @author xJh
 * @since 2025/12/28
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class VectorContentRetrieverStrategy implements ContentRetrieverStrategy {

    private final VectorStoreFactory vectorStoreFactory;
    private final KnowledgeBaseService knowledgeBaseService;
    private final EmbeddingModelRegistry embeddingModelRegistry;

    @Override
    public String getType() {
        return "VECTOR";
    }

    @Override
    public String getDescription() {
        return "内部知识库（文档、手册、策略等非结构化内容）";
    }

    @Override
    public boolean supports(RagAssistantParams params) {
        return Boolean.TRUE.equals(params.getEnableVectorRetrieval())
                && params.getEmbeddingModelEntity() != null
                && params.getKbId() != null;
    }

    @Override
    public Optional<ContentRetriever> createRetriever(RagAssistantParams params, ChatModel chatModel) {
        try {
            KnowledgeBase knowledgeBase = knowledgeBaseService.getById(params.getKbId());
            if (knowledgeBase == null) {
                log.warn("知识库不存在: kbId={}", params.getKbId());
                return Optional.empty();
            }

            EmbeddingStore<TextSegment> embeddingStore = vectorStoreFactory.createForKnowledgeBase(
                    knowledgeBase, params.getEmbeddingModelEntity());
            EmbeddingModel embeddingModel = embeddingModelRegistry.getFactory(params.getEmbeddingModelEntity())
                    .createModel(params.getEmbeddingModelEntity());

            ContentRetriever retriever = EmbeddingStoreContentRetriever.builder()
                    .embeddingStore(embeddingStore)
                    .embeddingModel(embeddingModel)
                    .maxResults(params.getMaxResults())
                    .minScore(params.getMinScore())
                    .build();

            log.debug("向量检索器创建成功: kbId={}, maxResults={}, minScore={}",
                    params.getKbId(), params.getMaxResults(), params.getMinScore());

            return Optional.of(retriever);
        } catch (Exception e) {
            log.error("创建向量检索器失败: kbId={}", params.getKbId(), e);
            return Optional.empty();
        }
    }
}
