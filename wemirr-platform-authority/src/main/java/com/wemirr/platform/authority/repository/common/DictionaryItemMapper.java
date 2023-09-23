package com.wemirr.platform.authority.repository.common;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.common.entity.DictionaryItem;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 字典项
 * </p>
 *
 * @author Levin
 * @since 2019-07-02
 */
@Repository
public interface DictionaryItemMapper extends SuperMapper<DictionaryItem> {

}
