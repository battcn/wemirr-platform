package com.wemirr.platform.wms.matedata.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.matedata.domain.entity.Unit;
import com.wemirr.platform.wms.matedata.domain.entity.UnitConv;
import com.wemirr.platform.wms.matedata.domain.req.UnitConvSaveReq;
import com.wemirr.platform.wms.matedata.mapper.UnitConvMapper;
import com.wemirr.platform.wms.matedata.mapper.UnitMapper;
import com.wemirr.platform.wms.matedata.service.UnitConvService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 基本计量单位转换 服务实现类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-24
 */
@Service
@RequiredArgsConstructor
public class UnitConvServiceImpl extends SuperServiceImpl<UnitConvMapper, UnitConv> implements UnitConvService {

    private final UnitMapper unitMapper;

    @Override
    public void create(UnitConvSaveReq req) {
        Long count = this.baseMapper.selectCount(Wraps.<UnitConv>lbQ().eq(UnitConv::getUnitId, req.getUnitId())
                .eq(UnitConv::getConvUnitId, req.getConvUnitId()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("基本计量单位转换已存在");
        }
        Unit unit = Optional.ofNullable(this.unitMapper.selectById(req.getUnitId())).orElseThrow(() -> CheckedException.notFound("计量单位不存在"));
        Unit convUnit = Optional.ofNullable(this.unitMapper.selectById(req.getConvUnitId())).orElseThrow(() -> CheckedException.notFound("转换的计量单位不存在"));
        UnitConv bean = BeanUtil.toBean(req, UnitConv.class);
        bean.setUnit(unit.getName());
        bean.setConvUnit(convUnit.getName());
        this.baseMapper.insert(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, UnitConvSaveReq req) {
        Long count = this.baseMapper.selectCount(Wraps.<UnitConv>lbQ().ne(UnitConv::getId, id)
                .eq(UnitConv::getUnitId, req.getUnitId()).eq(UnitConv::getConvUnitId, req.getConvUnitId()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("基本计量单位转换已存在");
        }
        Unit unit = Optional.ofNullable(this.unitMapper.selectById(req.getUnitId())).orElseThrow(() -> CheckedException.notFound("计量单位不存在"));
        Unit convUnit = Optional.ofNullable(this.unitMapper.selectById(req.getConvUnitId())).orElseThrow(() -> CheckedException.notFound("转换的计量单位不存在"));
        UnitConv bean = BeanUtilPlus.toBean(id, req, UnitConv.class);
        bean.setUnit(unit.getName());
        bean.setConvUnit(convUnit.getName());
        this.baseMapper.updateById(bean);
    }
}
