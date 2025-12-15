package com.wemirr.platform.ai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.core.enums.KnowledgeItemStatus;
import com.wemirr.platform.ai.core.enums.KnowledgeItemType;
import com.wemirr.platform.ai.core.processor.DocumentProcessor;
import com.wemirr.platform.ai.domain.dto.rep.KnowledgeItemResp;
import com.wemirr.platform.ai.domain.dto.rep.PreviewChunkResp;
import com.wemirr.platform.ai.domain.dto.req.DocumentSaveReq;
import com.wemirr.platform.ai.domain.dto.req.KnowledgeItemPageReq;
import com.wemirr.platform.ai.domain.dto.req.KnowledgeItemSaveReq;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.KnowledgeChunk;
import com.wemirr.platform.ai.domain.entity.KnowledgeItem;
import com.wemirr.platform.ai.repository.KnowledgeChunkMapper;
import com.wemirr.platform.ai.repository.KnowledgeItemMapper;
import com.wemirr.platform.ai.service.KnowledgeBaseService;
import com.wemirr.platform.ai.service.KnowledgeChunkService;
import com.wemirr.platform.ai.service.KnowledgeItemService;
import com.wemirr.platform.suite.feign.OssFileFeign;
import com.wemirr.platform.suite.feign.domain.resp.OssFileResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 知识条目服务实现类
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class KnowledgeItemServiceImpl extends SuperServiceImpl<KnowledgeItemMapper, KnowledgeItem> implements KnowledgeItemService {

    private final KnowledgeChunkMapper knowledgeChunkMapper;

    private final KnowledgeChunkService knowledgeChunkService;

    private final DocumentProcessor documentProcessor;

    private final KnowledgeBaseService knowledgeBaseService;

    private final OssFileFeign ossFileFeign;

    @Override
    public IPage<KnowledgeItemResp> pageList(KnowledgeItemPageReq req) {
        return baseMapper.selectPage(req.buildPage(),
                        Wraps.<KnowledgeItem>lbQ()
                                .eq(req.getKbId() != null, KnowledgeItem::getKbId, req.getKbId())
                                .eq(req.getType() != null, KnowledgeItem::getType, req.getType())
                                .eq(req.getStatus() != null, KnowledgeItem::getStatus, req.getStatus())
                                .orderByDesc(KnowledgeItem::getCreateName))
                .convert(item -> {
                    KnowledgeItemResp resp = BeanUtil.toBean(item, KnowledgeItemResp.class);
                    // 获取分片数量
                    Integer chunkCount = knowledgeChunkMapper.countByItemId(item.getId());
                    resp.setChunkCount(chunkCount);
                    return resp;
                });
    }

    @Override
    public KnowledgeItemResp detail(Long id) {
        KnowledgeItem item = baseMapper.selectById(id);
        Optional.ofNullable(item)
                .orElseThrow(() -> CheckedException.notFound("知识条目不存在"));
        
        KnowledgeItemResp resp = BeanUtil.toBean(item, KnowledgeItemResp.class);
        // 获取分片数量
        Integer chunkCount = knowledgeChunkMapper.countByItemId(id);
        resp.setChunkCount(chunkCount);
        
        return resp;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(KnowledgeItemSaveReq req) {
        KnowledgeItem item = BeanUtil.toBean(req, KnowledgeItem.class);
        item.setVersion(1);
        // 初始状态为待处理
        item.setStatus(KnowledgeItemStatus.PENDING);
        baseMapper.insert(item);
        return item.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> batchCreate(List<KnowledgeItemSaveReq> reqs) {
        if (CollUtil.isEmpty(reqs)) {
            return Collections.emptyList();
        }
        
        List<KnowledgeItem> items = reqs.stream()
                .map(req -> {
                    KnowledgeItem item = BeanUtil.toBean(req, KnowledgeItem.class);
                    item.setVersion(1);
                    item.setStatus(KnowledgeItemStatus.PENDING);
                    return item;
                })
                .collect(Collectors.toList());
        
        insertBatch(items);
        
        return items.stream()
                .map(KnowledgeItem::getId)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, KnowledgeItemSaveReq req) {
        KnowledgeItem item = baseMapper.selectById(id);
        Optional.ofNullable(item)
                .orElseThrow(() -> CheckedException.notFound("知识条目不存在"));
        
        KnowledgeItem updateEntity = BeanUtil.toBean(req, KnowledgeItem.class);
        updateEntity.setId(id);
        updateEntity.setVersion(item.getVersion() + 1);
        baseMapper.updateById(updateEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        KnowledgeItem item = baseMapper.selectById(id);
        Optional.ofNullable(item)
                .orElseThrow(() -> CheckedException.notFound("知识条目不存在"));
        
        // 删除知识分片
        knowledgeChunkService.deleteByItemId(id);
        
        // 删除知识条目
        baseMapper.deleteById(id);
    }

    @Override
    public List<KnowledgeItemResp> listByKbId(Long kbId) {
        List<KnowledgeItem> items = baseMapper.selectByKbId(kbId);
        return items.stream()
                .map(item -> {
                    KnowledgeItemResp resp = BeanUtil.toBean(item, KnowledgeItemResp.class);
                    // 获取分片数量
                    Integer chunkCount = knowledgeChunkMapper.countByItemId(item.getId());
                    resp.setChunkCount(chunkCount);
                    return resp;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<KnowledgeItemResp> listByKbIdAndType(Long kbId, KnowledgeItemType type) {
        List<KnowledgeItem> items = baseMapper.selectByKbIdAndType(kbId, type.getCode());
        return items.stream()
                .map(item -> {
                    KnowledgeItemResp resp = BeanUtil.toBean(item, KnowledgeItemResp.class);
                    // 获取分片数量
                    Integer chunkCount = knowledgeChunkMapper.countByItemId(item.getId());
                    resp.setChunkCount(chunkCount);
                    return resp;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> countByKbIdGroupByType(Long kbId) {
        List<KnowledgeItemMapper.KnowledgeItemTypeCount> counts = baseMapper.countByKbIdGroupByType(kbId);
        Map<String, Integer> result = new HashMap<>();
        
        for (KnowledgeItemMapper.KnowledgeItemTypeCount count : counts) {
            result.put(count.getType(), count.getCount());
        }
        
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createFAQ(Long kbId, String question, String answer, Map<String, Object> metadata) {
        // 创建FAQ知识条目（强类型字段）
        KnowledgeItem item = KnowledgeItem.builder()
                .kbId(kbId)
                .type(KnowledgeItemType.QA_PAIR)
                .title(question)
                .question(question)
                .answer(answer)
                .status(KnowledgeItemStatus.PROCESSED)
                .vectorized(false)
                .version(1)
                .metadata(metadata)
                .build();
        
        baseMapper.insert(item);
        
        return item.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createStructuredData(Long kbId, String title, Map<String, Object> structuredData, Map<String, Object> metadata) {
        // 创建结构化数据知识条目（结构化属性进metadata，content用于嵌入可为空或拼接）
        if (metadata == null) {
            metadata = new HashMap<>();
        }
        metadata.put("data", structuredData);
        KnowledgeItem item = KnowledgeItem.builder()
                .kbId(kbId)
                .type(KnowledgeItemType.STRUCTURED)
                .title(title)
                .status(KnowledgeItemStatus.PROCESSED)
                .vectorized(false)
                .version(1)
                .metadata(metadata)
                .build();
        
        baseMapper.insert(item);
        
        return item.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDocumentItem(Long kbId, String docId, Map<String, Object> metadata) {
        // 创建文档知识条目（兼容旧签名：docId 写入 metadata）
        KnowledgeItem item = KnowledgeItem.builder()
                .kbId(kbId)
                .type(KnowledgeItemType.DOCUMENT)
                .status(KnowledgeItemStatus.PROCESSED)
                .vectorized(false)
                .version(1)
                .metadata(metadata == null ? Map.of("doc_id", docId) : new HashMap<>(metadata))
                .build();
        if (item.getMetadata() instanceof HashMap) {
            ((HashMap<String, Object>) item.getMetadata()).put("doc_id", docId);
        }
        
        baseMapper.insert(item);
        return item.getId();
    }

    @Override
    public KnowledgeItem selectBySourceId(Long docId) {
        return baseMapper.selectById(docId);
    }

    @Override
    public String preview(Long itemId) {
        return baseMapper.selectById(itemId).getContent();
    }

    @Override
    public List<PreviewChunkResp> previewChunk(Long itemId) {
        return knowledgeChunkService.list(Wraps.<KnowledgeChunk>lbQ().eq(KnowledgeChunk::getItemId, itemId)).stream()
                .map(chunk -> PreviewChunkResp.builder()
                        .id(chunk.getId())
                        .content(chunk.getContent())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void bulkUpdateChunks(List<Long> chunkIds) {
        //TODO 如果文档已经向量化，不允许执行

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDocument(DocumentSaveReq req) {
        KnowledgeItem item = KnowledgeItem.builder()
                .kbId(req.getKbId())
                .type(KnowledgeItemType.DOCUMENT)
                .title(req.getTitle())
                .content(req.getContent())
                .contentType(req.getContentType())
                .filePath(req.getFilePath())
                .fileSize(req.getFileSize())
                .status(KnowledgeItemStatus.PENDING)
                .vectorized(false)
                .version(1)
                .metadata(req.getMetadata())
                .deleted(false)
                .build();
        baseMapper.insert(item);
        KnowledgeBase knb = knowledgeBaseService.getById(req.getKbId());
        if (req.getContent() != null && !req.getContent().isEmpty()) {
            knowledgeChunkService.createDocumentChunks(knb, item.getId(), String.valueOf(item.getId()), req.getContent());
        }
        return item.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDocument(Long id, String title, String content, String contentType, String filePath, Long fileSize, Map<String, Object> metadata) {
        KnowledgeItem item = baseMapper.selectById(id);
        Optional.ofNullable(item).orElseThrow(() -> CheckedException.notFound("知识条目不存在"));
        KnowledgeItem update = new KnowledgeItem();
        update.setId(id);
        update.setVersion(item.getVersion() + 1);
        update.setTitle(title);
        update.setContent(content);
        update.setContentType(contentType);
        update.setFilePath(filePath);
        update.setFileSize(fileSize);
        update.setMetadata(metadata);
        baseMapper.updateById(update);
        KnowledgeBase kbs = knowledgeBaseService.getById(item.getKbId());
        if (content != null && !content.equals(item.getContent())) {
            knowledgeChunkService.deleteByItemId(id);
            knowledgeChunkService.createDocumentChunks(kbs, id, String.valueOf(id), content);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long uploadAndProcess(Long kbId, MultipartFile file) throws IOException {
        OssFileResp uploadResp = ossFileFeign.upload(file);
        DocumentSaveReq req = DocumentSaveReq.builder()
                .filePath(uploadResp.getPath())
                .fileSize(uploadResp.getSize())
                .contentType(uploadResp.getExt())
                .kbId(kbId)
                .title(uploadResp.getOriginalFilename())
                .build();
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        File tempFile = File.createTempFile("upload_", "_" + originalFilename);
        try {
            file.transferTo(tempFile);
            String content = documentProcessor.extractText(tempFile, contentType);
            req.setContent(content);
            return createDocument(req);
        } finally {
//            FileUtil.del(tempFile);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reprocess(Long id) throws IOException {
        KnowledgeItem item = baseMapper.selectById(id);
        Optional.ofNullable(item).orElseThrow(() -> CheckedException.notFound("知识条目不存在"));
        if (item.getType() != KnowledgeItemType.DOCUMENT) {
            throw new CheckedException("仅支持对文档类型进行重处理");
        }
        knowledgeChunkService.deleteByItemId(id);
        KnowledgeBase knowledgeBase = knowledgeBaseService.getById(item.getKbId());
        if (item.getContent() != null && !item.getContent().isEmpty()) {
            knowledgeChunkService.createDocumentChunks(knowledgeBase, id, String.valueOf(id), item.getContent());
        }
    }
}
