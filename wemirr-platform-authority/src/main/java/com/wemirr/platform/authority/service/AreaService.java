package com.wemirr.platform.authority.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.common.entity.AreaEntity;

import java.util.List;

/**
 * @author Levin
 */
public interface AreaService extends SuperService<AreaEntity> {

    /**
     * 根据 parentId 查询数据集
     *
     * @param parentId parentId
     * @return 查询结果
     */
    List<AreaEntity> listArea(Integer parentId);

    /**
     * 保存或者修改地区
     *
     * @param area area
     */
    void saveOrUpdateArea(AreaEntity area);

}
