package com.wemirr.platform.ai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.domain.dto.rep.ModelDetailRep;
import com.wemirr.platform.ai.domain.dto.rep.ModelPageRep;
import com.wemirr.platform.ai.domain.dto.req.ModelPageReq;
import com.wemirr.platform.ai.domain.dto.req.ModelSaveReq;
import com.wemirr.platform.ai.domain.entity.ModelEntity;
import com.wemirr.platform.ai.repository.ModelConfigMapper;
import com.wemirr.platform.ai.service.ModelConfigService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author xJh
 * @since 2025/10/11
 **/
@Service
public class ModelConfigServiceImpl extends SuperServiceImpl<ModelConfigMapper, ModelEntity> implements ModelConfigService {

    @Override
    public IPage<ModelPageRep> pageList(ModelPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<ModelEntity>lbQ()
                        .eq(ModelEntity::getProvider, req.getProvider())
                        .eq(ModelEntity::getType, req.getType())
                        .eq(ModelEntity::getName, req.getName()))
                .convert(x -> BeanUtil.toBean(x, ModelPageRep.class));
    }

    @Override
    public ModelDetailRep detail(Long id) {
        ModelEntity modelEntity = Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("模型配置不存在"));
        return BeanUtil.toBean(modelEntity, ModelDetailRep.class);
    }

    @Override
    public void create(ModelSaveReq req) {
        ModelEntity modelEntity = BeanUtil.toBean(req, ModelEntity.class);
        this.baseMapper.insert(modelEntity);
    }

    @Override
    public void modify(Long id, ModelSaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("模型配置不存在"));
        ModelEntity modelEntity = BeanUtilPlus.toBean(id, req, ModelEntity.class);
        this.baseMapper.updateById(modelEntity);
    }

    @Override
    public void remove(Long id) {
        this.baseMapper.deleteById(id);
    }
}
