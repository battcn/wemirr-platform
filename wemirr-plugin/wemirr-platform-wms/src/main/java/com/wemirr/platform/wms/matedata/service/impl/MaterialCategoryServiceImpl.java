package com.wemirr.platform.wms.matedata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.redis.plus.sequence.RedisSequenceHelper;
import com.wemirr.platform.wms.inbound.domain.enums.WmsSequence;
import com.wemirr.platform.wms.matedata.domain.entity.MaterialCategory;
import com.wemirr.platform.wms.matedata.domain.req.MaterialCategorySaveReq;
import com.wemirr.platform.wms.matedata.mapper.MaterialTypeMapper;
import com.wemirr.platform.wms.matedata.service.MaterialCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 物料类型 服务实现类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Service
@RequiredArgsConstructor
public class MaterialCategoryServiceImpl extends SuperServiceImpl<MaterialTypeMapper, MaterialCategory> implements MaterialCategoryService {

    private final RedisSequenceHelper sequenceHelper;
    private final AuthenticationContext context;

    @Override
    public void create(MaterialCategorySaveReq req) {
        Long count = this.baseMapper.selectCount(Wraps.<MaterialCategory>lbQ().eq(MaterialCategory::getName, req.getName()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("物料类别已存在");
        }
        var bean = BeanUtilPlus.toBean(req, MaterialCategory.class);
        String code = sequenceHelper.generate(WmsSequence.MATERIAL_CATEGORY_NO, context.tenantId());
        bean.setCode(code);
        this.baseMapper.insert(bean);
    }

    @Override
    public void modify(Long id, MaterialCategorySaveReq req) {
        Long count = this.baseMapper.selectCount(Wraps.<MaterialCategory>lbQ().ne(MaterialCategory::getId, id)
                .eq(MaterialCategory::getName, req.getName()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("物料类别已存在");
        }
        var bean = BeanUtilPlus.toBean(id, req, MaterialCategory.class);
        this.baseMapper.updateById(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void cascadeDelete(Long id) {
        deleteNodeAndChildren(id);
    }


    public void deleteNodeAndChildren(Long id) {
        // 1. 找到所有子节点
        List<Long> childIds = findChildIds(id);
        // 2. 递归删除子节点
        for (Long childId : childIds) {
            deleteNodeAndChildren(childId);
        }
        this.baseMapper.deleteById(id);
    }

    private List<Long> findChildIds(Long parentId) {
        // 查询所有 parentId 为给定 id 的子节点
        return this.baseMapper.selectList(MaterialCategory::getParentId, parentId)
                .stream().map(Entity::getId).toList();
    }
}
