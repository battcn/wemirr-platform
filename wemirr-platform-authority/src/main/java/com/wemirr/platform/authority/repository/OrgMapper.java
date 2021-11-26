package com.wemirr.platform.authority.repository;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.wemirr.framework.db.configuration.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatis.SuperMapper;
import com.wemirr.platform.authority.domain.entity.baseinfo.Org;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@TenantDS
@Repository
public interface OrgMapper extends SuperMapper<Org> {


    /**
     * 根据父级ID的获取全部父级
     *
     * @param parentId parentId
     * @return 查询结果
     */
    @InterceptorIgnore(tenantLine = "true")
    String getTreePathByParentId(Long parentId);

    /**
     * 根据ID的获取子集
     *
     * @param id id
     * @return 查询结果
     */
    List<Org> findChildrenById(Long id);

}
