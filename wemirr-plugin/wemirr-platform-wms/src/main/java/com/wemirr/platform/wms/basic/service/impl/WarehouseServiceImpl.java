package com.wemirr.platform.wms.basic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.basic.domain.entity.Warehouse;
import com.wemirr.platform.wms.basic.domain.req.WarehouseSaveReq;
import com.wemirr.platform.wms.basic.mapper.WarehouseMapper;
import com.wemirr.platform.wms.basic.service.WarehouseService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 仓库表 服务实现类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Service
public class WarehouseServiceImpl extends SuperServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {
    @Override
    public void create(WarehouseSaveReq req) {
        Long count = this.baseMapper.selectCount(Warehouse::getCode, req.getCode());
        if (count != null && count > 0) {
            throw CheckedException.badRequest("仓库编号已存在");
        }
        var bean = BeanUtil.toBean(req, Warehouse.class);
        this.baseMapper.insert(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, WarehouseSaveReq req) {
        Long count = this.baseMapper.selectCount(Wraps.<Warehouse>lbQ().ne(Warehouse::getId, id).eq(Warehouse::getCode, req.getCode()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("仓库编号已存在");
        }
        var bean = BeanUtilPlus.toBean(id, req, Warehouse.class);
        this.baseMapper.updateById(bean);
    }
}
