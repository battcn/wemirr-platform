package com.wemirr.platform.suite.gen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.gen.domain.dto.rep.GenerateTemplateDetailRep;
import com.wemirr.platform.suite.gen.domain.dto.rep.GenerateTemplatePageRep;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplatePageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplateSaveReq;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTemplate;
import com.wemirr.platform.suite.gen.repository.GenerateTemplateMapper;
import com.wemirr.platform.suite.gen.repository.TemplateGroupRelationMapper;
import com.wemirr.platform.suite.gen.service.GenerateTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author xiao1
 * @since 2024-12
 */
@RequiredArgsConstructor
@Service
public class GenerateTemplateServiceImpl extends SuperServiceImpl<GenerateTemplateMapper, GenerateTemplate> implements GenerateTemplateService {


    private final TemplateGroupRelationMapper templateGroupRelationMapper;

    @Override
    public void create(GenerateTemplateSaveReq req) {
        GenerateTemplate generateTemplate = BeanUtil.toBean(req, GenerateTemplate.class);
        this.baseMapper.insert(generateTemplate);
    }

    @Override
    public void modify(Long id, GenerateTemplateSaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("模板不存在"));
        GenerateTemplate generateTemplate = BeanUtilPlus.toBean(id, req, GenerateTemplate.class);
        this.baseMapper.updateById(generateTemplate);
    }

    @Override
    public GenerateTemplateDetailRep detail(Long id) {
        GenerateTemplate generateTemplate = this.baseMapper.selectById(id);
        Optional.ofNullable(generateTemplate)
                .orElseThrow(() -> CheckedException.notFound("模板不存在"));
        return BeanUtil.toBean(generateTemplate, GenerateTemplateDetailRep.class);

    }

    @Transactional
    @Override
    public void removeTemplate(Long id) {
        //删除模板
        this.baseMapper.deleteById(id);
        // 删除模板关联关系
        templateGroupRelationMapper.deleteTemplateId(id);

    }

    @Override
    public IPage<GenerateTemplatePageRep> pageList(GenerateTemplatePageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<GenerateTemplate>lbQ()
                        .eq(GenerateTemplate::getName, req.getName()))
                .convert(x -> BeanUtil.toBean(x, GenerateTemplatePageRep.class));

    }

    @Override
    public List<GenerateTemplate> getTemplateDetailByGroupId(Long templateGroupId) {
        return this.baseMapper.getTemplateDetailByGroupId(templateGroupId);

    }
}
