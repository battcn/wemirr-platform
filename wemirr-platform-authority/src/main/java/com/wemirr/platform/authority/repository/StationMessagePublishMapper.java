package com.wemirr.platform.authority.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.entity.message.SiteNotify;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface StationMessagePublishMapper extends SuperMapper<SiteNotify> {
}
