package com.wemirr.platform.authority.controller.common;

import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.IdUtil;
import com.wemirr.platform.authority.domain.common.resp.CaptchaResp;
import com.wemirr.platform.authority.service.VerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 */
@RestController
@RequestMapping
@Tag(name = "验证码", description = "验证码")
@RequiredArgsConstructor
public class CaptchaController {

    private final VerificationService verificationService;

    @SneakyThrows
    @GetMapping(value = "/captcha2", produces = "image/png")
    @Operation(summary = "验证码 - [DONE] - [Levin]", description = "验证码 - [DONE] - [Levin]")
    public void create(@RequestParam(value = "key") String key,
                       @RequestParam(defaultValue = "200", required = false) Integer width,
                       @RequestParam(defaultValue = "50", required = false) Integer height,
                       HttpServletResponse response) {
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
        final CircleCaptcha captcha = verificationService.create(key, width, height);
        response.getOutputStream().write(captcha.getImageBytes());
    }

    @GetMapping("/captcha")
    @Operation(summary = "验证码 - [DONE] - [Levin]", description = "验证码 - [DONE] - [Levin]")
    public CaptchaResp getCaptcha(@RequestParam(value = "key", required = false) String key,
                                  @RequestParam(defaultValue = "130", required = false) Integer width,
                                  @RequestParam(defaultValue = "34", required = false) Integer height) {
        if (key == null) {
            key = IdUtil.fastSimpleUUID();
        }
        final CircleCaptcha captcha = verificationService.create(key, width, height);
        return CaptchaResp.builder().captchaId(key).code(captcha.getCode()).imageData(captcha.getImageBase64Data()).build();
    }


}
