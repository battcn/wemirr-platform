package com.wemirr.platform.authority.repository.tenant;

import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.tenant.entity.TenantDictionary;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 字典类型
 * </p>
 *
 * @author Levin
 * @date 2019-07-02
 */
@TenantDS
@Repository
public interface TenantDictionaryMapper extends SuperMapper<TenantDictionary> {

}
