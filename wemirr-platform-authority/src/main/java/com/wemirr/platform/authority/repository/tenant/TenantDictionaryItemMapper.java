package com.wemirr.platform.authority.repository.tenant;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.tenant.entity.TenantDictionaryItem;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 字典项
 * </p>
 *
 * @author Levin
 * @date 2019-07-02
 */
@Repository
public interface TenantDictionaryItemMapper extends SuperMapper<TenantDictionaryItem> {

}
