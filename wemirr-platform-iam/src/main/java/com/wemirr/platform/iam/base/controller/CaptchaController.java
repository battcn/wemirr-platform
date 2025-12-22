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

package com.wemirr.platform.iam.base.controller;

import cloud.tianai.captcha.application.vo.ImageCaptchaVO;
import cloud.tianai.captcha.common.response.ApiResponse;
import cn.dev33.satoken.annotation.SaIgnore;
import com.wemirr.framework.commons.annotation.IgnoreGlobalResponse;
import com.wemirr.platform.iam.base.domain.dto.req.CaptchaReq;
import com.wemirr.platform.iam.base.domain.dto.resp.CaptchaResp;
import com.wemirr.platform.iam.system.service.CaptchaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Levin
 */
@RestController
@RequestMapping
@Tag(name = "验证码", description = "验证码")
@RequiredArgsConstructor
public class CaptchaController {

    private final CaptchaService captchaService;

//    @GetMapping("/captcha")
//    @Operation(summary = "验证码 - [DONE] - [Levin]", description = "验证码 - [DONE] - [Levin]")
//    public CaptchaResp create(@RequestParam(value = "key", required = false) String key,
//                              @RequestParam(defaultValue = "130", required = false) Integer width,
//                              @RequestParam(defaultValue = "34", required = false) Integer height) {
//        final String captchaId = StrUtil.blankToDefault(key, IdUtil.fastSimpleUUID());
//        final CircleCaptcha captcha = captchaService.create(captchaId, width, height);
//        return CaptchaResp.builder().captchaId(captchaId).code(captcha.getCode()).imageData(captcha.getImageBase64Data()).build();
//    }

    @SaIgnore
    @GetMapping("/captcha/slider")
    public ImageCaptchaVO captcha() {
        // 1.生成验证码(该数据返回给前端用于展示验证码数据)
        // 参数1为具体的验证码类型， 默认支持 SLIDER、ROTATE、WORD_IMAGE_CLICK、CONCAT 等验证码类型，详见： `CaptchaTypeConstant`类
        //前端需要特定格式返回。
        return captchaService.captcha();
    }

    @SaIgnore
    @PostMapping("/captcha/slider/check")
    public CaptchaResp check(@RequestBody CaptchaReq req) {
        return captchaService.check(req);
    }

//    @SaIgnore
//    @GetMapping("/tmpToken")
//    public CaptchaResp getTemToken(String principal) {
//        return captchaService.getCaptchaTmpToken(principal);
//    }

}
