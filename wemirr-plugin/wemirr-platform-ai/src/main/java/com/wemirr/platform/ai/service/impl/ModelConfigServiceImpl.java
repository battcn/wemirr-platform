package com.wemirr.platform.ai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.core.exception.ModelNotFoundException;
import com.wemirr.platform.ai.domain.dto.req.ModelPageReq;
import com.wemirr.platform.ai.domain.dto.req.ModelSaveReq;
import com.wemirr.platform.ai.domain.dto.resp.ModelDetailResp;
import com.wemirr.platform.ai.domain.dto.resp.ModelPageResp;
import com.wemirr.platform.ai.domain.entity.ModelEntity;
import com.wemirr.platform.ai.repository.ModelConfigMapper;
import com.wemirr.platform.ai.service.ModelConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 模型配置服务实现
 *
 * @author xJh
 * @since 2025/10/11
 */
@Slf4j
@Service
public class ModelConfigServiceImpl extends SuperServiceImpl<ModelConfigMapper, ModelEntity> implements ModelConfigService {

    @Override
    public IPage<ModelPageResp> pageList(ModelPageReq req) {
        log.debug("分页查询模型配置: provider={}, type={}, name={}",
                req.getProvider(), req.getType(), req.getName());
        
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<ModelEntity>lbQ()
                        .eq(ModelEntity::getProvider, req.getProvider())
                        .eq(ModelEntity::getType, req.getType())
                        .eq(ModelEntity::getName, req.getName()))
                .convert(entity -> BeanUtil.toBean(entity, ModelPageResp.class));
    }

    @Override
    public ModelDetailResp detail(Long id) {
        log.debug("查询模型配置详情: id={}", id);
        
        ModelEntity modelEntity = Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> new ModelNotFoundException(id));
        
        return BeanUtil.toBean(modelEntity, ModelDetailResp.class);
    }

    @Override
    public void create(ModelSaveReq req) {
        log.info("创建模型配置: provider={}, type={}, name={}",
                req.getProvider(), req.getType(), req.getName());
        
        ModelEntity modelEntity = BeanUtil.toBean(req, ModelEntity.class);
        this.baseMapper.insert(modelEntity);
        
        log.info("模型配置创建成功: id={}", modelEntity.getId());
    }

    @Override
    public void modify(Long id, ModelSaveReq req) {
        log.info("修改模型配置: id={}", id);
        
        // 验证模型是否存在
        Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> new ModelNotFoundException(id));
        
        ModelEntity modelEntity = BeanUtilPlus.toBean(id, req, ModelEntity.class);
        this.baseMapper.updateById(modelEntity);
        
        log.info("模型配置修改成功: id={}", id);
    }

    @Override
    public void remove(Long id) {
        log.info("删除模型配置: id={}", id);
        
        // 验证模型是否存在
        Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> new ModelNotFoundException(id));
        
        this.baseMapper.deleteById(id);
        
        log.info("模型配置删除成功: id={}", id);
    }
}
