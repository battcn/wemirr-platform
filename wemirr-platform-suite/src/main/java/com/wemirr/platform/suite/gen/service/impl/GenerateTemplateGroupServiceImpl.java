package com.wemirr.platform.suite.gen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplateGroupPageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplateGroupSaveReq;
import com.wemirr.platform.suite.gen.domain.dto.resp.GenerateTemplateGroupPageResp;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTable;
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

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class GenerateTemplateGroupServiceImpl extends SuperServiceImpl<GenerateTemplateGroupMapper, GenerateTemplateGroup> implements GenerateTemplateGroupService {

    private final TemplateGroupRelationMapper templateGroupRelationMapper;
    private final GenerateTableMapper generateTableMapper;

    @Override
    public IPage<GenerateTemplateGroupPageResp> pageList(GenerateTemplateGroupPageReq req) {
        return this.baseMapper.selectPageList(req.buildPage(), req);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(GenerateTemplateGroupSaveReq req) {
        GenerateTemplateGroup entity = BeanUtil.toBean(req, GenerateTemplateGroup.class);
        if (req.getIsDefault()) {
            entity.setIsDefault(false);
        }
        this.baseMapper.insert(entity);
        saveTemplateGroupRelations(entity.getId(), req.getTemplateIds());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(Long id, GenerateTemplateGroupSaveReq req) {
        GenerateTemplateGroup entity = BeanUtil.toBean(req, GenerateTemplateGroup.class);
        entity.setId(id);
        this.baseMapper.updateById(entity);
        this.templateGroupRelationMapper.delete(Wraps.<TemplateGroupRelation>lbQ().eq(TemplateGroupRelation::getGroupId, id));
        saveTemplateGroupRelations(id, req.getTemplateIds());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeGroup(Long id) {
        //检查是否有模板组在使用中
        long i = generateTableMapper.selectCount(GenerateTable::getTemplateGroupId, id);
        if (i > 0) {
            throw new CheckedException("该模板组下有生成配置在使用中，无法删除");
        }
        this.baseMapper.deleteById(id);
        this.templateGroupRelationMapper.delete(Wraps.<TemplateGroupRelation>lbQ().eq(TemplateGroupRelation::getGroupId, id));

    }


    private void saveTemplateGroupRelations(Long groupId, List<Long> templateIds) {
        List<TemplateGroupRelation> relations = templateIds.stream()
                .map(templateId -> TemplateGroupRelation.builder().templateId(templateId).groupId(groupId).build())
                .collect(Collectors.toList());
        this.templateGroupRelationMapper.insertBatch(relations);
    }
}