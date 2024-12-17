package com.wemirr.platform.wms.basic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.basic.domain.entity.StorageArea;
import com.wemirr.platform.wms.basic.domain.req.StorageAreaSaveReq;
import com.wemirr.platform.wms.basic.mapper.StorageAreaMapper;
import com.wemirr.platform.wms.basic.service.StorageAreaService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库区表 服务实现类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Service
public class StorageAreaServiceImpl extends SuperServiceImpl<StorageAreaMapper, StorageArea> implements StorageAreaService {
    @Override
    public void create(StorageAreaSaveReq req) {
        Long count = this.baseMapper.selectCount(StorageArea::getCode, req.getCode());
        if (count != null && count > 0) {
            throw CheckedException.badRequest("该库区编号已存在");
        }
        var bean = BeanUtil.toBean(req, StorageArea.class);
        this.baseMapper.insert(bean);
    }


    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, StorageAreaSaveReq req) {
        Long count = this.baseMapper.selectCount(Wraps.<StorageArea>lbQ().ne(StorageArea::getId, id).eq(StorageArea::getCode, req.getCode()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("该库区编号已存在");
        }
        var bean = BeanUtilPlus.toBean(id, req, StorageArea.class);
        this.baseMapper.updateById(bean);
    }
}
