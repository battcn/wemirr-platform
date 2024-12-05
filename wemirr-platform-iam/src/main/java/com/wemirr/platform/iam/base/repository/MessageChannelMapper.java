package com.wemirr.platform.iam.base.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.iam.base.domain.entity.MessageChannel;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface MessageChannelMapper extends SuperMapper<MessageChannel> {
}
