package com.wemirr.platform.ai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.core.enums.KnowledgeItemType;
import com.wemirr.platform.ai.domain.dto.rep.KnowledgeBaseResp;
import com.wemirr.platform.ai.domain.dto.req.KnowledgeBasePageReq;
import com.wemirr.platform.ai.domain.dto.req.KnowledgeBaseSaveReq;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;
import com.wemirr.platform.ai.domain.entity.KnowledgeItem;
import com.wemirr.platform.ai.repository.KnowledgeBaseMapper;
import com.wemirr.platform.ai.repository.KnowledgeItemMapper;
import com.wemirr.platform.ai.service.KnowledgeBaseService;
import com.wemirr.platform.ai.service.VectorStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 知识库服务实现类
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class KnowledgeBaseServiceImpl extends SuperServiceImpl<KnowledgeBaseMapper, KnowledgeBase> implements KnowledgeBaseService {


    private final VectorStoreService vectorStoreService;

    private final KnowledgeItemMapper knowledgeItemMapper;

    @Override
    public IPage<KnowledgeBaseResp> pageList(KnowledgeBasePageReq req) {
        return baseMapper.selectPage(req.buildPage(), 
                Wraps.<KnowledgeBase>lbQ()
                        .like(req.getName() != null, KnowledgeBase::getName, req.getName())
//                        .eq(req.getTenantId() != null, KnowledgeBase::getTenantId, req.getTenantId())
                        .orderByDesc(KnowledgeBase::getCreateTime))
                .convert(this::convertToResp);
    }

    @Override
    public KnowledgeBaseResp detail(Long id) {
        KnowledgeBase knowledgeBase = baseMapper.selectById(id);
        Optional.ofNullable(knowledgeBase)
                .orElseThrow(() -> CheckedException.notFound("知识库不存在"));
        return convertToResp(knowledgeBase);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(KnowledgeBaseSaveReq req) {
        KnowledgeBase knowledgeBase = BeanUtil.toBean(req, KnowledgeBase.class);
        knowledgeBase.setVersion(1);
        knowledgeBase.setDeleted(false);
        save(knowledgeBase);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(Long id, KnowledgeBaseSaveReq req) {
        KnowledgeBase knowledgeBase = baseMapper.selectById(id);
        Optional.ofNullable(knowledgeBase)
                .orElseThrow(() -> CheckedException.notFound("知识库不存在"));
        
        KnowledgeBase updateEntity = BeanUtil.toBean(req, KnowledgeBase.class);
        updateEntity.setId(id);
        updateEntity.setVersion(knowledgeBase.getVersion() + 1);
        baseMapper.updateById(updateEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        KnowledgeBase knowledgeBase = baseMapper.selectById(id);
        Optional.ofNullable(knowledgeBase)
                .orElseThrow(() -> CheckedException.notFound("知识库不存在"));
        
        // 删除知识库下的所有向量数据
        vectorStoreService.deleteByKbId(String.valueOf(id));
        
        // 删除知识库
        baseMapper.deleteById(id);
    }
    
    /**
     * 将实体转换为响应对象
     *
     * @param knowledgeBase 知识库实体
     * @return 知识库响应对象
     */
    private KnowledgeBaseResp convertToResp(KnowledgeBase knowledgeBase) {
        KnowledgeBaseResp resp = BeanUtil.toBean(knowledgeBase, KnowledgeBaseResp.class);
        
//        // 统计文档、FAQ、结构化数据数量
          String kbId = String.valueOf(knowledgeBase.getId());
          Long documentCount = knowledgeItemMapper.selectCount(Wraps.<KnowledgeItem>lbQ().eq(KnowledgeItem::getType, KnowledgeItemType.DOCUMENT)
                .eq(KnowledgeItem::getId, kbId));
//        Integer faqCount = faqMapper.countByKbId(kbId);
//        Integer structuredDataCount = structuredDataMapper.countByKbId(kbId);
//
          resp.setDocumentCount(Math.toIntExact(documentCount));
//        resp.setFaqCount(faqCount);
//        resp.setStructuredDataCount(structuredDataCount);
        
        return resp;
    }
}
