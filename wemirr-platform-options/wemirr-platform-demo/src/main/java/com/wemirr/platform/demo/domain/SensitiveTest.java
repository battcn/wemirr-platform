package com.wemirr.platform.demo.domain;

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
public class SensitiveTest {

    @Sensitive(type = Sensitive.Type.EMAIL)
    private String username;

    @Sensitive(type = Sensitive.Type.MOBILE_PHONE)
    private String mobile;

    /**
     * 不指定的话则默认策略 随机打码
     */
    @Sensitive(type = Sensitive.Type.STRATEGY)
    private String desc;

    private String none;

}
