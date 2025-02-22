package com.wemirr.platform.suite.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.suite.gen.domain.dto.rep.GenerateTemplateDetailRep;
import com.wemirr.platform.suite.gen.domain.dto.rep.GenerateTemplatePageRep;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplatePageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplateSaveReq;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTemplate;

import java.util.List;

/**
 * @author xiao1
 * @date 2024-12
 */
public interface GenerateTemplateService extends SuperService<GenerateTemplate> {
    void create(GenerateTemplateSaveReq req);

    void modify(Long id, GenerateTemplateSaveReq req);

    GenerateTemplateDetailRep detail(Long id);

    void removeTemplate(Long id);

    IPage<GenerateTemplatePageRep> pageList(GenerateTemplatePageReq req);

    List<GenerateTemplate> getTemplateDetailByGroupId(Long templateGroupId);
}
