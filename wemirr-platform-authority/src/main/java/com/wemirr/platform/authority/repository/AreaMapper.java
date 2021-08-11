package com.wemirr.platform.authority.repository;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wemirr.framework.boot.SuperMapper;
import com.wemirr.platform.authority.domain.entity.common.AreaEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@DS("#header.tenant_code")
@Repository
public interface AreaMapper extends SuperMapper<AreaEntity> {

    /**
     * 根据 parentId 查询数据集
     *
     * @param parentId parentId
     * @return 查询结果
     */
    List<AreaEntity> listArea(@Param("parentId") Integer parentId);
}
