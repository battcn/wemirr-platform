package com.wemirr.platform.iam.system.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.iam.system.domain.entity.UserThirdAccount;
import org.springframework.stereotype.Repository;

/**
 * @author levin
 */
@Repository
public interface ThirdAccountMapper extends SuperMapper<UserThirdAccount> {
}
