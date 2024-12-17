package com.wemirr.platform.wms.basic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.basic.domain.entity.Aisle;
import com.wemirr.platform.wms.basic.domain.req.AisleSaveReq;
import com.wemirr.platform.wms.basic.mapper.AisleMapper;
import com.wemirr.platform.wms.basic.service.AisleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 巷道表 服务实现类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Service
public class AisleServiceImpl extends SuperServiceImpl<AisleMapper, Aisle> implements AisleService {


    @Override
    public void create(AisleSaveReq req) {
        Long count = this.baseMapper.selectCount(Aisle::getCode, req.getCode());
        if (count != null && count > 0) {
            throw CheckedException.badRequest("该巷道编号已存在");
        }
        var bean = BeanUtil.toBean(req, Aisle.class);
        this.baseMapper.insert(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, AisleSaveReq req) {
        Long count = this.baseMapper.selectCount(Wraps.<Aisle>lbQ().ne(Aisle::getId, id).eq(Aisle::getCode, req.getCode()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("该巷道编号已存在");
        }
        var bean = BeanUtilPlus.toBean(id, req, Aisle.class);
        this.baseMapper.updateById(bean);
    }
}
