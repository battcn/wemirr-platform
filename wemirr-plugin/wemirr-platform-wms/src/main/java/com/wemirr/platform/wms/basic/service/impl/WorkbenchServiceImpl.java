package com.wemirr.platform.wms.basic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.basic.domain.entity.Workbench;
import com.wemirr.platform.wms.basic.domain.req.WorkbenchSaveReq;
import com.wemirr.platform.wms.basic.mapper.WorkbenchMapper;
import com.wemirr.platform.wms.basic.service.WorkbenchService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 工作台表 服务实现类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Service
public class WorkbenchServiceImpl extends SuperServiceImpl<WorkbenchMapper, Workbench> implements WorkbenchService {

    @Override
    public void create(WorkbenchSaveReq req) {
        Long count = this.baseMapper.selectCount(Workbench::getCode, req.getCode());
        if (count != null && count > 0) {
            throw CheckedException.badRequest("工作台编号已存在");
        }
        var bean = BeanUtil.toBean(req, Workbench.class);
        this.baseMapper.insert(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, WorkbenchSaveReq req) {
        Long count = this.baseMapper.selectCount(Wraps.<Workbench>lbQ().ne(Workbench::getId, id).eq(Workbench::getCode, req.getCode()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("工作台编号已存在");
        }
        var bean = BeanUtilPlus.toBean(id, req, Workbench.class);
        this.baseMapper.updateById(bean);
    }
}
