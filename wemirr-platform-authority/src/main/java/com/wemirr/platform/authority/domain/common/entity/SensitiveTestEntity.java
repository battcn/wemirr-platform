package com.wemirr.platform.authority.domain.common.entity;

import com.wemirr.framework.boot.sensitive.DefaulSensitiveStrategy;
import com.wemirr.framework.boot.sensitive.Sensitive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveTestEntity {

    @Sensitive(type = Sensitive.Type.EMAIL)
    private String username;
    @Sensitive(type = Sensitive.Type.MOBILE_PHONE)
    private String mobile;

    @Sensitive(type = Sensitive.Type.STRATEGY,strategy = DefaulSensitiveStrategy.class)
    private String desc;

    private String none;
}
