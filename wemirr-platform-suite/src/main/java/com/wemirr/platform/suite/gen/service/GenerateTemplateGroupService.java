package com.wemirr.platform.suite.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.suite.gen.domain.dto.rep.GenerateTemplateGroupPageRep;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplateGroupPageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplateGroupSaveReq;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTemplateGroup;

/**
 * @author xiao1
 * @date 2024-12
 */
public interface GenerateTemplateGroupService extends SuperService<GenerateTemplateGroup> {


    IPage<GenerateTemplateGroupPageRep> pageList(GenerateTemplateGroupPageReq req);

    void create(GenerateTemplateGroupSaveReq req);

    void modify(Long id, GenerateTemplateGroupSaveReq req);

    void removeGroup(Long id);
}
