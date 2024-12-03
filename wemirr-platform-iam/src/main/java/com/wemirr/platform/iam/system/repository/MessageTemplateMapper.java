package com.wemirr.platform.iam.system.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.iam.system.domain.entity.MessageTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface MessageTemplateMapper extends SuperMapper<MessageTemplate> {
}
