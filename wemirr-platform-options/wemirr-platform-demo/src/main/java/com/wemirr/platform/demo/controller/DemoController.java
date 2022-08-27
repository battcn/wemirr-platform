package com.wemirr.platform.demo.controller;

import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.security.client.annotation.IgnoreAuthorize;
import com.wemirr.platform.demo.service.DemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @IgnoreAuthorize
    @GetMapping("/ignore")
    @Operation(summary = "查询")
    public Result<?> ignore() {
        log.info("输出内容 - {}", demoService.sayHello());
        return Result.success();
    }

    @GetMapping("/say")
    @Operation(summary = "查询")
    public String say() {
        log.info("输出内容 - {}", demoService.sayHello());
        return "say...";
    }

}