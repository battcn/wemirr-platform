package com.wemirr.platform.authority.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.platform.authority.service.VerificationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.*;
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
    public CircleCaptcha create(String key, Integer width, Integer height) {
        if (StrUtil.isBlank(key)) {
            throw CheckedException.badRequest("验证码key不能为空");
        }
        final CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(width, height, 5, 4);
        captcha.setTextAlpha(0.6F);
        stringRedisTemplate.opsForValue().set(geyKey(key), captcha.getCode(), 3, TimeUnit.MINUTES);
        log.debug("验证码结果 - {}", captcha.getCode());
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
