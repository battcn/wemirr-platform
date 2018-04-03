package com.battcn.bookstore.consumer.controller.authentication;

import com.battcn.bookstore.consumer.enums.AuthorizedEnum;
import com.battcn.framework.commons.lang.RandomUtils;
import com.battcn.framework.commons.utils.NewVerifyCodeUtils;
import com.battcn.framework.exception.CustomException;
import com.battcn.framework.redis.sequence.SequenceGenerator;
import com.battcn.framework.redis.sequence.SequenceType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
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

/**
 * @author Levin
 * @since 2018/4/3 0003
 */
@Slf4j
@RestController
@RequestMapping(value = "/v0.1/captcha", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "授权管理", description = "验证码管理", tags = "1.0")
public class CaptchaController {

    private final SequenceGenerator sequenceGenerator;
    private final RedisTemplate<String, Serializable> redisCacheTemplate;

    @Autowired
    public CaptchaController(SequenceGenerator sequenceGenerator, RedisTemplate<String, Serializable> redisCacheTemplate) {
        this.redisCacheTemplate = redisCacheTemplate;
        this.sequenceGenerator = sequenceGenerator;
    }

    /**
     * 生成图片验证码
     */
    @ApiOperation(value = "获取图片验证码")
    @GetMapping
    public void genCaptcha(HttpServletResponse response) throws IOException {
        final String token = RandomUtils.generate();
        response.setHeader("token", token);
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
        redisCacheTemplate.opsForHash().put(AuthorizedEnum.REDIS_CAPTCHA_HASH.getKey(), token, imageCode);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 查询图片验证码
     */
    @ApiOperation(value = "验证验证码是否正确")
    @GetMapping(value = "/{token}/{code}")
    public void findCaptcha(@PathVariable("token") String token, @PathVariable("code") String code, HttpServletResponse response) {
        final HashOperations<String, String, String> opsForHash = redisCacheTemplate.opsForHash();
        final String cacheCode = opsForHash.get(AuthorizedEnum.REDIS_CAPTCHA_HASH.getKey(), token);
        if (!StringUtils.equals(code, cacheCode)) {
            throw CustomException.badRequest("验证码错误");
        }
        // 验证成功删除临时Token
        opsForHash.delete(AuthorizedEnum.REDIS_CAPTCHA_HASH.getKey(), code);
        final String memberNo = sequenceGenerator.generateSequence(SequenceType.MR);
        //3、将 会员号 存入 Redis 中
        redisCacheTemplate.opsForHash().put(AuthorizedEnum.REDIS_MEMBER_NO_HASH.getKey(), memberNo, memberNo);
        response.setHeader("member_no", memberNo);
    }

}
