package com.wemirr.platform.demo.service.client;

import com.wemirr.framework.feign.plugin.token.AutoRefreshTokenProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

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
    List<Object> query();

}
