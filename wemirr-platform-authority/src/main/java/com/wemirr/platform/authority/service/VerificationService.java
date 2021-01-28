package com.wemirr.platform.authority.service;

import com.wemirr.framework.commons.entity.Result;
import com.wf.captcha.base.Captcha;

/**
 * @author Levin
 */
public interface VerificationService {

    /**
     * 创建验证码
     *
     * @param key key
     * @return 验证码结果
     */
    Captcha create(String key);

    /**
     * 验证图形验证码
     *
     * @param key   key
     * @param value val
     * @return 验证结果
     */
    Result<Boolean> valid(String key, String value);

}
