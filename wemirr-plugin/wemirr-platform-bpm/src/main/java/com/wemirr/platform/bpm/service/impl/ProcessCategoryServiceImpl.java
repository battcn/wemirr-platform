

package com.wemirr.platform.bpm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.bpm.domain.entity.ProcessCategory;
import com.wemirr.platform.bpm.domain.req.ProcessCategorySaveReq;
import com.wemirr.platform.bpm.repository.ProcessCategoryMapper;
import com.wemirr.platform.bpm.service.ProcessCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 流程类别(ProcessCategory)业务层实现
 *
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessCategoryServiceImpl extends SuperServiceImpl<ProcessCategoryMapper, ProcessCategory> implements ProcessCategoryService {


    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void create(ProcessCategorySaveReq req) {
        final Long count = this.baseMapper.selectCount(Wraps.<ProcessCategory>lbQ()
                .eq(ProcessCategory::getCode, req.getCode()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("bpm.process-category.code-exists");
        }
        ProcessCategory record = BeanUtil.toBean(req, ProcessCategory.class);
        this.baseMapper.insert(record);
    }

    @Override
    @DSTransactional(rollbackFor = {Exception.class, Error.class})
    public void modify(Long id, ProcessCategorySaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.badRequest("bpm.process-category.not-exists"));
        ProcessCategory bean = BeanUtilPlus.toBean(id, req, ProcessCategory.class);
        this.baseMapper.updateById(bean);
    }
}
