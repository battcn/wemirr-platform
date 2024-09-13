package com.wemirr.platform.authority.controller;

import com.alibaba.fastjson2.JSONObject;
import com.wemirr.framework.security.configuration.client.annotation.IgnoreAuthorize;
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
@Tag(name = "测试专用", description = "测试专用")
public class DemoController {


    @IgnoreAuthorize(global = false)
    @GetMapping("/test1")
    public JSONObject test1() {
        JSONObject result = new JSONObject();
        result.put("key1", "val1");
        return result;
    }

}
