package com.wemirr.platform.ai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.domain.dto.rep.ModelConfigDetailRep;
import com.wemirr.platform.ai.domain.dto.rep.ModelConfigPageRep;
import com.wemirr.platform.ai.domain.dto.req.ModelConfigPageReq;
import com.wemirr.platform.ai.domain.dto.req.ModelConfigSaveReq;
import com.wemirr.platform.ai.domain.entity.ModelConfig;
import com.wemirr.platform.ai.repository.ModelConfigMapper;
import com.wemirr.platform.ai.service.ModelConfigService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author xJh
 * @since 2025/10/11
 **/
@Service
public class ModelConfigServiceImpl extends SuperServiceImpl<ModelConfigMapper, ModelConfig> implements ModelConfigService {

    @Override
    public IPage<ModelConfigPageRep> pageList(ModelConfigPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<ModelConfig>lbQ()
                        .eq(ModelConfig::getProvider, req.getProvider())
                        .eq(ModelConfig::getModelType, req.getModelType())
                        .eq(ModelConfig::getModelName, req.getModelName()))
                .convert(x -> BeanUtil.toBean(x, ModelConfigPageRep.class));
    }

    @Override
    public ModelConfigDetailRep detail(Long id) {
        ModelConfig modelConfig = Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("模型配置不存在"));
        return BeanUtil.toBean(modelConfig, ModelConfigDetailRep.class);
    }

    @Override
    public void create(ModelConfigSaveReq req) {
        ModelConfig modelConfig = BeanUtil.toBean(req, ModelConfig.class);
        this.baseMapper.insert(modelConfig);
    }

    @Override
    public void modify(Long id, ModelConfigSaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("模型配置不存在"));
        ModelConfig modelConfig = BeanUtilPlus.toBean(id, req, ModelConfig.class);
        this.baseMapper.updateById(modelConfig);
    }

    @Override
    public void remove(Long id) {
        this.baseMapper.deleteById(id);
    }
}
