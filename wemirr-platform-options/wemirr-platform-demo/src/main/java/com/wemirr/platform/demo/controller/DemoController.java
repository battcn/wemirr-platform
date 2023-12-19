package com.wemirr.platform.demo.controller;

import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.i18n.annotation.I18nMethod;
import com.wemirr.framework.redis.plus.anontation.RedisLock;
import com.wemirr.framework.security.configuration.client.annotation.IgnoreAuthorize;
import com.wemirr.framework.security.utils.SecurityUtils;
import com.wemirr.platform.demo.domain.enums.I18nEnum;
import com.wemirr.platform.demo.domain.resp.I18nDemoResp;
import com.wemirr.platform.demo.service.DemoService;
import com.wemirr.platform.demo.service.client.DemoTestFeignClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/demos")
@RequiredArgsConstructor
@Tag(name = "Demo测试", description = "Demo测试")
public class DemoController {

    private final DemoService demoService;
    private final DemoTestFeignClient demoTestFeignClient;

    @IgnoreAuthorize
    @GetMapping("/ignore")
    @Operation(summary = "忽略权限查询")
    public Result<?> ignore() {
        log.info("输出内容 - {}", demoService.sayHello());
        return Result.success();
    }

    @GetMapping("/say")
    @Operation(summary = "带权限查询")
    public Result<?> say() {

        log.info("输出内容 - {}", demoService.sayHello());
        return Result.success(SecurityUtils.getAuthInfo());
    }


    @IgnoreAuthorize
    @GetMapping("/feign")
    @Operation(summary = "自动生成Token查询", description = "需要配置登录信息才可以")
    public List<?> feign() {
        return demoTestFeignClient.query();
    }


    @GetMapping("/feign_language")
    @IgnoreAuthorize
    public void language() {
        demoTestFeignClient.feignLanguage();
    }


    @GetMapping("/i18n")
    @IgnoreAuthorize
    @I18nMethod
    public I18nDemoResp i18n() {
        return I18nDemoResp.builder().type("type").build();
    }

    @GetMapping("/i18n_list")
    @IgnoreAuthorize
    @I18nMethod
    public List<I18nDemoResp> i18nList() {
        return List.of(
                I18nDemoResp.builder().type("i18n.demo.type").name("i18n.test").build(),
                I18nDemoResp.builder().type("type").i18nEnum(I18nEnum.MI).build(),
                I18nDemoResp.builder().dbCode("i18n.demo").i18nEnum(I18nEnum.MI).build(),
                I18nDemoResp.builder().type("type").i18nEnum(I18nEnum.APPLE).build()
        );
    }

    @SneakyThrows
    @GetMapping("/lock")
    @IgnoreAuthorize
    @RedisLock(prefix = "lock", waitTime = 2)
    public void lock() {
        for (int i = 0; i < 90; i++) {
            TimeUnit.SECONDS.sleep(1);
            log.info("lock sleep {}", i);
        }
    }

    @SneakyThrows
    @GetMapping("/lock2")
    @IgnoreAuthorize
    @RedisLock(prefix = "lock", waitTime = 2)
    public void lock2() {
        for (int i = 0; i < 80; i++) {
            TimeUnit.SECONDS.sleep(1);
            log.info("lock2 sleep {}", i);
        }
    }


}
