package com.wemirr.platform.wms.matedata.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.wms.matedata.domain.entity.Material;
import com.wemirr.platform.wms.matedata.domain.entity.MaterialConfig;
import com.wemirr.platform.wms.matedata.domain.req.MaterialConfigSaveReq;
import com.wemirr.platform.wms.matedata.domain.req.MaterialSaveReq;
import com.wemirr.platform.wms.matedata.mapper.MaterialConfigMapper;
import com.wemirr.platform.wms.matedata.mapper.MaterialMapper;
import com.wemirr.platform.wms.matedata.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物料 服务实现类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Service
@RequiredArgsConstructor
public class MaterialServiceImpl extends SuperServiceImpl<MaterialMapper, Material> implements MaterialService {

    private final MaterialConfigMapper materialConfigMapper;

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void create(MaterialSaveReq req) {
        Long count = this.baseMapper.selectCount(Material::getName, req.getName());
        if (count != null && count > 0) {
            throw CheckedException.badRequest("物料已存在");
        }
        var bean = BeanUtil.toBean(req, Material.class);
        this.baseMapper.insert(bean);
        MaterialConfigSaveReq config = req.getConfig();
        if (ObjectUtils.isNotEmpty(config)) {
            MaterialConfig newMaterialConfig = BeanUtil.toBean(config, MaterialConfig.class);
            newMaterialConfig.setId(null);
            newMaterialConfig.setMaterialId(bean.getId());
            this.materialConfigMapper.insert(newMaterialConfig);
        }
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, MaterialSaveReq req) {
        Long count = this.baseMapper.selectCount(Wraps.<Material>lbQ().ne(Material::getId, id).eq(Material::getName, req.getName()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("容器编号已存在");
        }
        Material bean = BeanUtilPlus.toBean(id, req, Material.class);
        this.baseMapper.updateById(bean);
        if (ObjectUtils.isEmpty(req.getConfig())) {
            return;
        }
        MaterialConfig config = BeanUtil.toBean(req.getConfig(), MaterialConfig.class);
        config.setMaterialId(id);
        MaterialConfig materialConfig = this.materialConfigMapper.selectById(config.getId());
        if (ObjectUtils.isNotEmpty(materialConfig) && config.getId() != null) {
            this.materialConfigMapper.updateById(config);
        } else {
            this.materialConfigMapper.insert(config);
        }
    }
}
