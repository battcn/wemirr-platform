package com.wemirr.platform.suite.gen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.suite.gen.domain.dto.rep.GenerateTemplateGroupPageRep;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplateGroupPageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplateGroupSaveReq;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTemplateGroup;
import com.wemirr.platform.suite.gen.domain.entity.TemplateGroupRelation;
import com.wemirr.platform.suite.gen.repository.GenerateTableMapper;
import com.wemirr.platform.suite.gen.repository.GenerateTemplateGroupMapper;
import com.wemirr.platform.suite.gen.repository.TemplateGroupRelationMapper;
import com.wemirr.platform.suite.gen.service.GenerateTemplateGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class GenerateTemplateGroupServiceImpl extends SuperServiceImpl<GenerateTemplateGroupMapper, GenerateTemplateGroup> implements GenerateTemplateGroupService {

    private final TemplateGroupRelationMapper templateGroupRelationMapper;

    private final GenerateTableMapper generateTableMapper;

    @Override
    public IPage<GenerateTemplateGroupPageRep> pageList(GenerateTemplateGroupPageReq req) {
        return this.baseMapper.selectPageWithTemplateIds(req.buildPage(), req);
    }

    @Override
    public void create(GenerateTemplateGroupSaveReq req) {
        GenerateTemplateGroup generateTemplateGroup = BeanUtil.toBean(req, GenerateTemplateGroup.class);
        this.baseMapper.insert(generateTemplateGroup);
        if (req.getIsDefault()) {
            this.baseMapper.updateDefaultGroup();
        }
        saveTemplateGroupRelations(generateTemplateGroup.getId(), req.getTemplateIds());
    }

    @Override
    public void modify(Long id, GenerateTemplateGroupSaveReq req) {
        GenerateTemplateGroup generateTemplateGroup = BeanUtil.toBean(req, GenerateTemplateGroup.class);
        generateTemplateGroup.setId(id);
        if (req.getIsDefault()) {
            updateDefaultGroup(id);
        }
        this.baseMapper.updateById(generateTemplateGroup);
        templateGroupRelationMapper.deleteByGroupId(id);
        saveTemplateGroupRelations(id, req.getTemplateIds());
    }

    @Override
    public void removeGroup(Long id) {
        //检查是否有模板组在使用中
        int i = generateTableMapper.countByGroupId(id);
        if (i > 0) {
            throw new CheckedException("该模板组下有生成配置在使用中，无法删除");
        }
        this.baseMapper.deleteById(id);
        templateGroupRelationMapper.deleteByGroupId(id);
    }

    public void updateDefaultGroup(Long id) {
        this.baseMapper.updateDefaultGroup();
        this.baseMapper.updateDefaultGroupById(id);
    }

    private void saveTemplateGroupRelations(Long groupId, List<Long> templateIds) {
        List<TemplateGroupRelation> relations = templateIds.stream().map(templateId -> {
            TemplateGroupRelation relation = new TemplateGroupRelation();
            relation.setTemplateId(templateId);
            relation.setGroupId(groupId);
            return relation;
        }).collect(Collectors.toList());
        templateGroupRelationMapper.insertBatch(relations);
    }
}