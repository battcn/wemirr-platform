package com.wemirr.platform.suite.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplatePageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplateSaveReq;
import com.wemirr.platform.suite.gen.domain.dto.resp.GenerateTemplateDetailResp;
import com.wemirr.platform.suite.gen.domain.dto.resp.GenerateTemplatePageResp;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTemplate;

import java.util.List;

/**
 * @author xiao1
 * @since 2024-12
 */
public interface GenerateTemplateService extends SuperService<GenerateTemplate> {
    /**
     * 创建模板
     *
     * @param req 模板保存请求参数
     */
    void create(GenerateTemplateSaveReq req);

    /**
     * 修改模板
     *
     * @param id  模板ID
     * @param req 模板保存请求参数
     */
    void modify(Long id, GenerateTemplateSaveReq req);

    /**
     * 获取模板详情
     *
     * @param id 模板ID
     * @return 模板详情响应
     */
    GenerateTemplateDetailResp detail(Long id);

    /**
     * 删除模板
     *
     * @param id 模板ID
     */
    void removeTemplate(Long id);

    /**
     * 分页查询模板列表
     *
     * @param req 模板分页查询请求参数
     * @return 模板分页结果
     */
    IPage<GenerateTemplatePageResp> pageList(GenerateTemplatePageReq req);

    /**
     * 根据模板组ID获取模板详情列表
     *
     * @param templateGroupId 模板组ID
     * @return 模板详情列表
     */
    List<GenerateTemplate> getTemplateDetailByGroupId(Long templateGroupId);
}
