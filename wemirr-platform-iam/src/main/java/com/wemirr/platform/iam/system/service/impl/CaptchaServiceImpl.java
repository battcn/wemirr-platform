/*
 * Copyright (c) 2023 WEMIRR-PLATFORM Authors. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wemirr.platform.iam.system.service.impl;

import cloud.tianai.captcha.application.ImageCaptchaApplication;
import cloud.tianai.captcha.application.vo.ImageCaptchaVO;
import cloud.tianai.captcha.common.constant.CaptchaTypeConstant;
import cloud.tianai.captcha.common.response.ApiResponse;
import cloud.tianai.captcha.resource.CrudResourceStore;
import cloud.tianai.captcha.resource.ResourceStore;
import cloud.tianai.captcha.resource.common.model.dto.Resource;
import cn.dev33.satoken.temp.SaTempUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.platform.iam.base.domain.dto.req.CaptchaReq;
import com.wemirr.platform.iam.base.domain.dto.resp.CaptchaResp;
import com.wemirr.platform.iam.system.service.CaptchaService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {

    private static final String CAPTCHA_KEY_PREFIX = "captcha:prefix:%s";
    private final StringRedisTemplate stringRedisTemplate;
    private final ImageCaptchaApplication ica;

    private final ResourceStore resourceStore;

    @PostConstruct
    public void init() {
        CrudResourceStore resourceStore = (CrudResourceStore) this.resourceStore;
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bg-images/a.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bg-images/b.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bg-images/c.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bg-images/d.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bg-images/e.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bg-images/g.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bg-images/h.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bg-images/i.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bg-images/j.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.ROTATE, new Resource("classpath", "bg-images/48.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.CONCAT, new Resource("classpath", "bg-images/48.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bg-images/c.jpg", "default"));
    }


    @Override
    public ImageCaptchaVO captcha() {
        return ica.generateCaptcha(CaptchaTypeConstant.SLIDER).getData();
    }

    @Override
    public CaptchaResp check(CaptchaReq req) {
        ApiResponse<?> response = ica.matching(req.getId(), req.getCaptchaTrack());
        if (!response.isSuccess()) {
            throw CheckedException.notFound("验证失败");
        }
        return getCaptchaTmpToken(req.getPrincipal());
    }

    //    @Override
    public CaptchaResp getCaptchaTmpToken(String principal) {
        var now = Instant.now();
        var period = 10 * 60;
        String tmpToken = SaTempUtil.createToken(principal, period);
        return CaptchaResp.builder().tmpToken(tmpToken).period(period)
                .issueTime(now).expireTime(now.plusSeconds(period)).build();
    }


    @SneakyThrows
    @Override
    public CircleCaptcha create(String key, Integer width, Integer height) {
        if (StrUtil.isBlank(key)) {
            throw CheckedException.badRequest("验证码key不能为空");
        }
        final CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(width, height, 4, 4);
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
