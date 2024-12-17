package com.wemirr.platform.wms.matedata.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.matedata.domain.entity.Unit;
import com.wemirr.platform.wms.matedata.domain.req.UnitSaveReq;
import com.wemirr.platform.wms.matedata.mapper.UnitMapper;
import com.wemirr.platform.wms.matedata.service.UnitService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 基本计量单位 服务实现类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-24
 */
@Service
public class UnitServiceImpl extends SuperServiceImpl<UnitMapper, Unit> implements UnitService {

    @Override
    public void create(UnitSaveReq req) {
        Long count = this.baseMapper.selectCount(Unit::getSymbol, req.getSymbol());
        if (count != null && count > 0) {
            throw CheckedException.badRequest("计量单位符号已存在");
        }
        Unit bean = BeanUtil.toBean(req, Unit.class);
        this.baseMapper.insert(bean);
    }

    @Override
    public void modify(Long id, UnitSaveReq req) {
        Long count = this.baseMapper.selectCount(Wraps.<Unit>lbQ().ne(Unit::getId, id).eq(Unit::getSymbol, req.getSymbol()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("计量单位符号已存在");
        }
        Unit bean = BeanUtilPlus.toBean(id, req, Unit.class);
        this.baseMapper.updateById(bean);
    }
}
