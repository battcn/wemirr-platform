package com.battcn.bookstore.consumer.controller.authentication;

import com.battcn.bookstore.consumer.enums.AuthorizedEnum;
import com.battcn.framework.commons.lang.RandomUtils;
import com.battcn.framework.commons.utils.NewVerifyCodeUtils;
import com.battcn.framework.security.annotation.IgnoreAuthenticate;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * @author Levin
 * @since 2018/4/3 0003
 */
@Slf4j
@RestController
@RequestMapping(value = "/v0.1/captcha", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "授权管理", description = "验证码管理", tags = "1.0")
public class CaptchaController {

    private final RedisTemplate<String, Serializable> redisCacheTemplate;

    @Autowired
    public CaptchaController(RedisTemplate<String, Serializable> redisCacheTemplate) {
        this.redisCacheTemplate = redisCacheTemplate;
    }

    @IgnoreAuthenticate
    @ApiOperation(value = "获取客户端ID")
    @GetMapping
    public Map<String, String> getClientId() {
        final String clientId = RandomUtils.generate();
        redisCacheTemplate.opsForHash().put(AuthorizedEnum.REDIS_CAPTCHA_HASH.getKey(), clientId, null);
        Map<String, String> result = Maps.newHashMap();
        result.put("clientId", clientId);
        return result;
    }

    @IgnoreAuthenticate
    @ApiOperation(value = "获取图片验证码")
    @GetMapping("/{client_id}")
    public void genCaptcha(@PathVariable("client_id") String clientId, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        ServletOutputStream outputStream = response.getOutputStream();
        //1、将图片输出
        final String imageCode = NewVerifyCodeUtils.outputNumberVerifyImage(outputStream);
        //2、输出验证码
        log.info("[验证码] - [{}]", imageCode);
        //3、将验证码存入 Redis 中
        redisCacheTemplate.opsForHash().put(AuthorizedEnum.REDIS_CAPTCHA_HASH.getKey(), clientId, imageCode);
        outputStream.flush();
        outputStream.close();
    }

}
