package com.wemirr.platform.authority.controller.common;

import cn.hutool.captcha.CircleCaptcha;
import com.wemirr.framework.security.configuration.client.annotation.IgnoreAuthorize;
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
    @IgnoreAuthorize
    @GetMapping(value = "/captcha", produces = "image/png")
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


}
