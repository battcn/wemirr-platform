package com.wemirr.platform.wms.basic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.basic.domain.entity.Dock;
import com.wemirr.platform.wms.basic.domain.req.DockSaveReq;
import com.wemirr.platform.wms.basic.mapper.DockMapper;
import com.wemirr.platform.wms.basic.service.DockService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 月台管理表 服务实现类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Service
public class DockServiceImpl extends SuperServiceImpl<DockMapper, Dock> implements DockService {

    @Override
    public void create(DockSaveReq req) {
        Long count = this.baseMapper.selectCount(Dock::getCode, req.getCode());
        if (count != null && count > 0) {
            throw CheckedException.badRequest("该月台编号已存在");
        }
        var bean = BeanUtil.toBean(req, Dock.class);
        this.baseMapper.insert(bean);
    }

    @Override
    public void modify(Long id, DockSaveReq req) {
        Long count = this.baseMapper.selectCount(Wraps.<Dock>lbQ().ne(Dock::getId, id).eq(Dock::getCode, req.getCode()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("该月台编号已存在");
        }
        var bean = BeanUtilPlus.toBean(id, req, Dock.class);
        this.baseMapper.updateById(bean);
    }
}
