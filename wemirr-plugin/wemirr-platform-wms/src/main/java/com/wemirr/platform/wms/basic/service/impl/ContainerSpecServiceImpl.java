package com.wemirr.platform.wms.basic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.basic.domain.entity.ContainerSpec;
import com.wemirr.platform.wms.basic.domain.req.ContainerSpecSaveReq;
import com.wemirr.platform.wms.basic.mapper.ContainerSpecMapper;
import com.wemirr.platform.wms.basic.service.ContainerSpecService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 容器规格表 服务实现类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Service
public class ContainerSpecServiceImpl extends SuperServiceImpl<ContainerSpecMapper, ContainerSpec> implements ContainerSpecService {


    @Override
    public void create(ContainerSpecSaveReq req) {
        Long count = this.baseMapper.selectCount(ContainerSpec::getName, req.getName());
        if (count != null && count > 0) {
            throw CheckedException.badRequest("该容器规格已存在");
        }
        var bean = BeanUtil.toBean(req, ContainerSpec.class);
        this.baseMapper.insert(bean);

    }

    @Override
    public void modify(Long id, ContainerSpecSaveReq req) {
        Long count = this.baseMapper.selectCount(Wraps.<ContainerSpec>lbQ().ne(ContainerSpec::getId, id).eq(ContainerSpec::getName, req.getName()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("该容器规格已存在");
        }
        var bean = BeanUtilPlus.toBean(id, req, ContainerSpec.class);
        this.baseMapper.updateById(bean);
    }
}
