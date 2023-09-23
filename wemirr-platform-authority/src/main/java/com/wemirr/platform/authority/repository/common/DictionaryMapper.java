package com.wemirr.platform.authority.repository.common;

import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.common.entity.Dictionary;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 字典类型
 * </p>
 *
 * @author Levin
 * @since 2019-07-02
 */
@TenantDS
@Repository
public interface DictionaryMapper extends SuperMapper<Dictionary> {

}
