package com.wemirr.platform.authority.repository;

import com.wemirr.framework.db.mybatis.SuperMapper;
import com.wemirr.platform.authority.domain.entity.message.StationMessage;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface StationMessageMapper extends SuperMapper<StationMessage> {

}
