package com.wemirr.platform.iam.tenant.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.iam.tenant.domain.entity.TenantSetting;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface TenantSettingMapper extends SuperMapper<TenantSetting> {
}
