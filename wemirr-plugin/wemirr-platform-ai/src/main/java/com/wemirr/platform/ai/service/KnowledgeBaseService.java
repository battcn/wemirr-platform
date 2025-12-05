package com.wemirr.platform.ai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.ai.domain.dto.rep.KnowledgeBaseResp;
import com.wemirr.platform.ai.domain.dto.req.KnowledgeBasePageReq;
import com.wemirr.platform.ai.domain.dto.req.KnowledgeBaseSaveReq;
import com.wemirr.platform.ai.domain.entity.KnowledgeBase;

/**
 * 知识库服务接口
 *
 * @author xJh
 * @since 2025/10/20
 **/
public interface KnowledgeBaseService extends SuperService<KnowledgeBase> {

    /**
     * 分页查询知识库
     *
     * @param req 分页查询请求
     * @return 知识库分页结果
     */
    IPage<KnowledgeBaseResp> pageList(KnowledgeBasePageReq req);

    /**
     * 查询知识库详情
     *
     * @param id 知识库ID
     * @return 知识库详情
     */
    KnowledgeBaseResp detail(Long id);

    /**
     * 创建知识库
     *
     * @param req 知识库创建请求
     * @return 知识库ID
     */
    void create(KnowledgeBaseSaveReq req);

    /**
     * 更新知识库
     *
     * @param id 知识库ID
     * @param req 知识库更新请求
     */
    void modify(Long id, KnowledgeBaseSaveReq req);

    /**
     * 删除知识库
     *
     * @param id 知识库ID
     */
    void delete(Long id);
}
