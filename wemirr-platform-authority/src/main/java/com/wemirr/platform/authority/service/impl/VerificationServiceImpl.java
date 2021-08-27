package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.platform.authority.service.VerificationService;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

    private final StringRedisTemplate stringRedisTemplate;
    private static final String CAPTCHA_KEY_PREFIX = "captcha:prefix:%s";


    @SneakyThrows
    @Override
    public Captcha create(String key) {
        if (StrUtil.isBlank(key)) {
            throw CheckedException.badRequest("验证码key不能为空");
        }
        Captcha captcha = new ArithmeticCaptcha(115, 42);
        captcha.setCharType(2);
        stringRedisTemplate.opsForValue().set(geyKey(key), captcha.text(), 3, TimeUnit.MINUTES);
        log.debug("验证码结果 - {}", captcha.text());
        return captcha;
    }

    @Override
    public Result<Boolean> valid(String key, String value) {
        if (StringUtils.isBlank(value)) {
            return Result.fail("请输入验证码");
        }
        String code = stringRedisTemplate.opsForValue().get(geyKey(key));
        if (StrUtil.isEmpty(code)) {
            return Result.fail("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(value, code)) {
            return Result.fail("验证码不正确");
        }
        stringRedisTemplate.delete(geyKey(key));
        return Result.success();
    }

    private String geyKey(String key) {
        return String.format(CAPTCHA_KEY_PREFIX, key);
    }
}
