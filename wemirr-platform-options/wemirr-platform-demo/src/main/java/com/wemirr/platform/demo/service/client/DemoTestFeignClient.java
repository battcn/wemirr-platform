package com.wemirr.platform.demo.service.client;

import com.alibaba.fastjson2.JSONObject;
import com.wemirr.framework.feign.plugin.token.AutoRefreshTokenProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Levin
 */
@FeignClient(name = "wemirr-platform-authority", dismiss404 = true)
public interface DemoTestFeignClient {

    /**
     * 测试自动注入token查询
     *
     * @return 响应内容
     */
    @GetMapping(value = "/sensitives/list", headers = {AutoRefreshTokenProperties.X_AUTO_TOKEN})
    List<Object> query(@RequestParam("key") String key);

    /**
     * 测试 feign 国际化问题
     */
    @GetMapping(value = "/sensitives/feign_language")
    void feignLanguage();

    @GetMapping(value = "/demos/test1")
    JSONObject demoTest1();
}
