package com.wemirr.platform.ai.service.impl;

import com.wemirr.framework.commons.concurrent.AsyncExecutor;
import com.wemirr.platform.ai.core.provider.graph.GraphRagService;
import com.wemirr.platform.ai.core.provider.graph.GraphRagTransformerFactory;
import com.wemirr.platform.ai.core.provider.text.TextModelService;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.KnowledgeChunk;
import com.wemirr.platform.ai.domain.entity.KnowledgeItem;
import com.wemirr.platform.ai.domain.entity.ModelEntity;
import com.wemirr.platform.ai.service.GraphExtractionService;
import com.wemirr.platform.ai.service.KnowledgeChunkService;
import com.wemirr.platform.ai.service.ModelService;
import dev.langchain4j.community.data.document.transformer.graph.LLMGraphTransformer;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.model.chat.ChatModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 图谱提取服务实现
 *
 * @author xJh
 * @since 2025/12/28
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GraphExtractionServiceImpl implements GraphExtractionService {

    private final KnowledgeChunkService knowledgeChunkService;
    private final ModelService modelService;
    private final TextModelService textModelService;

    @Autowired(required = false)
    private GraphRagService graphRagService;

    @Override
    public GraphExtractionResult extractAndStore(KnowledgeItem item, KnowledgeBase kb) {
        if (graphRagService == null) {
            log.warn("图谱功能已启用但 GraphRagService 未注入，跳过图谱处理: itemId={}", item.getId());
            return GraphExtractionResult.failure("GraphRagService 未注入");
        }

        Long chatModelId = kb.getChatModelId();
        if (chatModelId == null) {
            log.warn("知识库未配置聊天模型，跳过图谱提取: kbId={}", kb.getId());
            return GraphExtractionResult.failure("知识库未配置聊天模型");
        }

        ModelEntity chatModelEntity = modelService.getById(chatModelId);
        if (chatModelEntity == null) {
            log.warn("聊天模型配置不存在，跳过图谱提取: chatModelId={}", chatModelId);
            return GraphExtractionResult.failure("聊天模型配置不存在");
        }

        List<KnowledgeChunk> chunks = knowledgeChunkService.listByItemId(item.getId());
        if (chunks.isEmpty()) {
            log.debug("知识条目没有分片，跳过图谱提取: itemId={}", item.getId());
            return GraphExtractionResult.success(0, 0);
        }

        try {
            return doExtractAndStore(item, kb, chatModelEntity, chunks);
        } catch (Exception e) {
            log.error("图谱提取失败: itemId={}", item.getId(), e);
            return GraphExtractionResult.failure(e.getMessage());
        }
    }

    /**
     * 执行图谱提取和存储
     */
    private GraphExtractionResult doExtractAndStore(KnowledgeItem item, KnowledgeBase kb,
                                                     ModelEntity chatModelEntity, List<KnowledgeChunk> chunks) {
        ChatModel chatModel = textModelService.model(chatModelEntity);
        LLMGraphTransformer graphTransformer = GraphRagTransformerFactory.create(chatModel);

        List<Document> documents = chunks.stream()
                .map(chunk -> Document.from(chunk.getContent()))
                .collect(Collectors.toList());

        String graphKbId = String.valueOf(kb.getId());
        GraphRagService.ProcessResult result = graphRagService.processDocuments(
                graphKbId, documents, graphTransformer, true);

        log.info("图谱提取完成: itemId={}, kbId={}, nodes={}, relationships={}",
                item.getId(), kb.getId(), result.nodesCreated(), result.relationshipsCreated());

        return GraphExtractionResult.success(result.nodesCreated(), result.relationshipsCreated());
    }

    @Override
    public void extractAndStoreAsync(KnowledgeItem item, KnowledgeBase kb) {
        log.info("开始异步图谱提取: itemId={}, kbId={}", item.getId(), kb.getId());
        // 使用虚拟线程异步执行
        AsyncExecutor.runAsync(() -> {
            GraphExtractionResult result = extractAndStore(item, kb);
            if (!result.success()) {
                log.warn("异步图谱提取失败: itemId={}, error={}", item.getId(), result.errorMessage());
            }
        });
    }

    @Override
    public boolean deleteGraphData(Long itemId, Long kbId) {
        if (graphRagService == null) {
            log.warn("GraphRagService 未注入，无法删除图谱数据");
            return false;
        }

        try {
            // TODO: 实现图谱数据删除逻辑
            log.info("删除图谱数据: itemId={}, kbId={}", itemId, kbId);
            return true;
        } catch (Exception e) {
            log.error("删除图谱数据失败: itemId={}, kbId={}", itemId, kbId, e);
            return false;
        }
    }
}
