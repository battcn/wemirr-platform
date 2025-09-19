

package com.wemirr.platform.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.remote.RemoteResult;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.mybatisplus.wrap.query.LbqWrapper;
import com.wemirr.platform.workflow.domain.dto.req.FlowCategoryPageReq;
import com.wemirr.platform.workflow.domain.dto.req.FlowCategorySaveReq;
import com.wemirr.platform.workflow.domain.dto.resp.FlowCategoryPageResp;
import com.wemirr.platform.workflow.domain.entity.FlowCategory;
import com.wemirr.platform.workflow.repository.FlowCategoryMapper;
import com.wemirr.platform.workflow.service.FlowCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.warm.flow.core.dto.Tree;
import org.dromara.warm.flow.ui.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author battcn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FlowCategoryServiceImpl extends SuperServiceImpl<FlowCategoryMapper, FlowCategory> implements FlowCategoryService, CategoryService {


    @Override
    @RemoteResult
    public IPage<FlowCategoryPageResp> pageList(FlowCategoryPageReq req) {
        final LbqWrapper<FlowCategory> wrapper = Wraps.<FlowCategory>lbQ()
                .eq(FlowCategory::getStatus, req.getStatus()).like(FlowCategory::getName, req.getName())
                .bt(FlowCategory::getCreateTime, req.getStartTime(), req.getEndTime())
                .orderByDesc(FlowCategory::getId);
        return baseMapper.selectPage(req.buildPage(), wrapper)
                .convert(x -> BeanUtil.toBean(x, FlowCategoryPageResp.class));
    }

    @Override
    public void create(FlowCategorySaveReq req) {
        Long count = baseMapper.selectCount(Wraps.<FlowCategory>lbQ()
                .eq(FlowCategory::getName, req.getName()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("流程分类名称已存在");
        }
        if (null == req.getStatus()) {
//            req.setStatus(ActiveStatus.ENABLED);
        }
        var bean = BeanUtil.toBean(req, FlowCategory.class);
        this.baseMapper.insert(bean);
    }

    @Override
    public void modify(String id, FlowCategorySaveReq req) {
        Long count = baseMapper.selectCount(Wraps.<FlowCategory>lbQ()
                .ne(FlowCategory::getId, id).eq(FlowCategory::getName, req.getName()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("流程分类名称已存在");
        }
        var bean = BeanUtilPlus.toBean(id, req, FlowCategory.class);
        this.baseMapper.updateById(bean);
    }

//    @Override
//    public void modifyStatus(String id, String activeStatus) {
//        var flowCategory = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("流程分类不存在"));
//        if (flowCategory.getStatus() == activeStatus) {
//            return;
//        }
//        this.baseMapper.updateById(FlowCategory.builder().id(id).status(activeStatus).build());
//    }

    @Override
    public List<Tree> queryCategory() {
        List<FlowCategory> flowCategories = baseMapper.selectList(FlowCategory::getStatus, true);
        if (CollUtil.isEmpty(flowCategories)) {
            return List.of();
        }
        return flowCategories.stream().map(x -> new Tree().setId(x.getId().toString()).setName(x.getName())).collect(Collectors.toList());
    }
}