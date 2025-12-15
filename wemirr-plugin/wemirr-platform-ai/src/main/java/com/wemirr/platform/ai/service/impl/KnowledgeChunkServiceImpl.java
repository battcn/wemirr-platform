package com.wemirr.platform.ai.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.commons.util.DigestUtil;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.ai.core.enums.ChunkType;
import com.wemirr.platform.ai.core.enums.KnowledgeItemType;
import com.wemirr.platform.ai.core.processor.DocumentProcessor;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.KnowledgeChunk;
import com.wemirr.platform.ai.repository.KnowledgeChunkMapper;
import com.wemirr.platform.ai.service.KnowledgeChunkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 知识分片服务实现类
 * 负责知识分片的创建和管理，不直接处理向量化
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class KnowledgeChunkServiceImpl extends SuperServiceImpl<KnowledgeChunkMapper, KnowledgeChunk> implements KnowledgeChunkService {

    private final DocumentProcessor documentProcessor;

    @Override
    public List<KnowledgeChunk> listByItemId(Long itemId) {
        return baseMapper.selectByItemId(itemId);
    }

    @Override
    public List<KnowledgeChunk> listByItemIdAndType(Long itemId, ChunkType chunkType) {
        return baseMapper.selectByItemIdAndType(itemId, chunkType.getCode());
    }

    @Override
    public List<KnowledgeChunk> listByKbId(Long kbId) {
        return baseMapper.selectByKbId(kbId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createTextChunk(Long kbId, Long itemId, String text, ChunkType chunkType, Map<String, Object> metadata) {
        if (StrUtil.isBlank(text)) {
            return null;
        }
        
        String contentHash = DigestUtil.md5Hex(text);
        
        KnowledgeChunk chunk = KnowledgeChunk.builder()
                .kbId(kbId)
                .itemId(itemId)
                .chunkType(chunkType)
                .content(text)
                .contentHash(contentHash)
                .metadata(metadata)
                .deleted(false)
                .build();
        
        baseMapper.insert(chunk);
        return chunk.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> batchCreateTextChunks(Long kbId, Long itemId, List<String> texts, ChunkType chunkType, List<Map<String, Object>> metadataList) {
        if (CollUtil.isEmpty(texts)) {
            return Collections.emptyList();
        }
        
        // 创建知识分片
        List<KnowledgeChunk> chunks = new ArrayList<>();
        
        for (int i = 0; i < texts.size(); i++) {
            String text = texts.get(i);
            Map<String, Object> metadata = i < metadataList.size() ? metadataList.get(i) : null;
            
            if (StrUtil.isNotBlank(text)) {
                String contentHash = DigestUtil.md5Hex(text);
                
                KnowledgeChunk chunk = KnowledgeChunk.builder()
                        .kbId(kbId)
                        .itemId(itemId)
                        .chunkType(chunkType)
                        .content(text)
                        .contentHash(contentHash)
                        .chunkIndex(i)
                        .metadata(metadata)
                        .deleted(false)
                        .build();
                
                chunks.add(chunk);
            }
        }
        
        if (!chunks.isEmpty()) {
            baseMapper.insertBatch(chunks);
        }
        
        return chunks.stream()
                .map(KnowledgeChunk::getId)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> createDocumentChunks(KnowledgeBase knowledgeBase, Long itemId, String docId, String content) {
        if (StrUtil.isBlank(content)) {
            return Collections.emptyList();
        }
        List<String> chunks = new ArrayList<>();
        // 分片文档
        if (knowledgeBase.getIngestMaxLength() ==null || knowledgeBase.getIngestMaxOverlap() ==null){
            chunks  = documentProcessor.splitText(content);
        }else {
            chunks = documentProcessor.splitText(content,knowledgeBase.getIngestMaxLength(),knowledgeBase.getIngestMaxOverlap());
        }

        // 准备元数据
        List<Map<String, Object>> metadataList = new ArrayList<>();
        for (int i = 0; i < chunks.size(); i++) {
            metadataList.add(Map.of(
                    "docId", docId,
                    "chunkIndex", i,
                    "chunksType",ChunkType.TEXT.getCode(),
                    "ItemType", KnowledgeItemType.DOCUMENT.getCode()
            ));
        }
        
        // 批量创建分片（强制校验：DOCUMENT 只能生成 TEXT 分片）
        return batchCreateTextChunks(knowledgeBase.getId(), itemId, chunks, ChunkType.TEXT, metadataList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> createQAPairChunks(Long kbId, Long itemId, String question, String answer) {
        List<Long> chunkIds = new ArrayList<>();
        
        // 创建问题分片（用于检索）
        if (StrUtil.isNotBlank(question)) {
            Long questionChunkId = createTextChunk(kbId, itemId, question, ChunkType.QUESTION, Map.of(
                    "itemId", itemId,
                    "hasAnswer", true
            ));
            if (questionChunkId != null) {
                chunkIds.add(questionChunkId);
            }
        }
        
        // 创建完整问答对分片（用于生成答案）
        if (StrUtil.isNotBlank(question) && StrUtil.isNotBlank(answer)) {
            String fullQA = "Q: " + question + "\nA: " + answer;
            Long fullQAChunkId = createTextChunk(kbId, itemId, fullQA, ChunkType.FULL_QA, Map.of(
                    "itemId", itemId,
                    "question", question,
                    "answer", answer
            ));
            if (fullQAChunkId != null) {
                chunkIds.add(fullQAChunkId);
            }
        }
        
        return chunkIds;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> createStructuredDataChunks(Long kbId, Long itemId, String title, Map<String, Object> structuredData) {
        // 将结构化数据转换为文本表示
        String textRepresentation = title + "\n" + convertStructuredDataToText(structuredData);
        
        // 创建文本分片（结构化数据转为文本，使用 TEXT 类型）
        Long chunkId = createTextChunk(kbId, itemId, textRepresentation, ChunkType.TEXT, Map.of(
                "itemId", itemId,
                "title", title,
                "structuredData", true
        ));
        
        return chunkId != null ? Collections.singletonList(chunkId) : Collections.emptyList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByItemId(Long itemId) {
        baseMapper.deleteByItemId(itemId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByKbId(Long kbId) {
        baseMapper.deleteByKbId(kbId);
    }

    /**
     * 将结构化数据转换为文本
     *
     * @param data 结构化数据
     * @return 文本表示
     */
    private String convertStructuredDataToText(Map<String, Object> data) {
        if (data == null || data.isEmpty()) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            sb.append(entry.getKey()).append(": ");
            
            Object value = entry.getValue();
            if (value instanceof Map) {
                sb.append("\n").append(convertStructuredDataToText((Map<String, Object>) value).indent(2));
            } else if (value instanceof List) {
                List<?> list = (List<?>) value;
                for (Object item : list) {
                    if (item instanceof Map) {
                        sb.append("\n").append(convertStructuredDataToText((Map<String, Object>) item).indent(2));
                    } else {
                        sb.append(item).append(", ");
                    }
                }
                if (!list.isEmpty()) {
                    // 删除最后的逗号和空格
                    sb.setLength(sb.length() - 2);
                }
            } else {
                sb.append(value);
            }
            
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
