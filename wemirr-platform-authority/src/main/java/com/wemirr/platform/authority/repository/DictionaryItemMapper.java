package com.wemirr.platform.authority.repository;

import com.wemirr.framework.db.mybatis.SuperMapper;
import com.wemirr.platform.authority.domain.entity.common.DictionaryItem;
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
public interface DictionaryItemMapper extends SuperMapper<DictionaryItem> {

}
