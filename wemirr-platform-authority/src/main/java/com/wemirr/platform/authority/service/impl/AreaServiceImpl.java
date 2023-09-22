package com.wemirr.platform.authority.service.impl;

import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.entity.common.AreaEntity;
import com.wemirr.platform.authority.repository.AreaMapper;
import com.wemirr.platform.authority.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class AreaServiceImpl extends SuperServiceImpl<AreaMapper, AreaEntity> implements AreaService {

    @Override
    public List<AreaEntity> listArea(Integer parentId) {
        return baseMapper.listArea(parentId);
    }

    @Override
    public void saveOrUpdateArea(AreaEntity area) {
        final long count = count(Wraps.<AreaEntity>lbQ().eq(AreaEntity::getId, area.getId()));
        if (count == 0) {
            baseMapper.insert(area);
        } else {
            baseMapper.updateById(area);
        }
    }


}
